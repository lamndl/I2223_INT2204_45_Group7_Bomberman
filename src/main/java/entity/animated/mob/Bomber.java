package entity.animated.mob;

import static mainClass.App.KB;
import static mainClass.App.currentPlayer;
import static mainClass.Sound.*;
import entity.animated.AnimatedEntity;
import entity.animated.Bomb;
import entity.animated.Flame;
import entity.tile.Portal;
import entity.tile.PowerUpBomb;
import entity.tile.PowerUpFlame;
import entity.tile.PowerUpSpeed;
import entity.tile.Tile;
import entity.tile.Wall;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import mainClass.App;
import mainClass.Board;
import mainClass.Sound;
import sprite.Sprite;

public class Bomber extends Mob {
  private int bombCount = 1;
  private int flameRadius = 1; //increase +1 if receive flame item
  private double speedMultiplier = 1;
  private double damageMultiplier = 1;

  private double damage  =1;

  /**
   * I separate damage and damage multiplier so other characters can have different strengths
   * Of course only if we have time
   * Todo: (MEDIUM PRIORITY) custom strengths for different characters.
   */

  public int getBombCount() {
    return bombCount;
  }

  public void setBombCount(int bombCount) {
    this.bombCount = bombCount;
  }

  public double getDamage() {
    return damage;
  }

  public void setDamage(double damage) {
    this.damage = damage;
  }

  public double getDamageMultiplier(){
    return damageMultiplier;
  }

  public void setDamageMultiplier(double d){
    damageMultiplier=d;
  }
  public double getSpeedMultiplier(){
    return speedMultiplier;
  }

  public void setSpeedMultiplier(double speedMultiplier){
    this.speedMultiplier=speedMultiplier;
  }
  public int getFlameRadius(){
    return flameRadius;
  }

  public void setFlameRadius(int radius){
    flameRadius= radius;
  }

  public void explode(int x, int y){
    Board.addEntity(new Flame(x, y));
    boolean north = true;
    boolean south = true;
    boolean east = true;
    boolean west = true;
    for(int i = 1; i<=getFlameRadius(); i++){
      if(Board.getTileEntityAt(x,y+32*i) instanceof Wall){
        south = false;
      }
      if(Board.getTileEntityAt(x,y-32*i) instanceof Wall){
        north = false;
      }
      if(Board.getTileEntityAt(x+32*i,y) instanceof Wall){
        east = false;
      }
      if(Board.getTileEntityAt(x-32*i,y) instanceof Wall){
        west = false;
      }
      if(east){
        Board.addEntity(new Flame(x + 32*i, y));
      }
      if(west){
        Board.addEntity(new Flame(x - 32*i, y));
      }
      if(south){
        Board.addEntity(new Flame(x, y + 32*i));
      }
      if(north){
        Board.addEntity(new Flame(x, y - 32*i));
      }
    }
  }

  public void placeBomb() {
    if (bombCount > 0) {
      bombCount--;
      Board.addEntity(new Bomb((x + 13) / 32 * 32, (y + 15) / 32 * 32));
    }
  }

  public Bomber(int x, int y) {
    super(x, y);
  }


  public void calculateMove() {
    for (KeyCode i : Board.input) {
      if (i == KB.getMoveUp()) {
        direction = 2;
        velocityY = -1*(int)(speedMultiplier);
        moving = 1;
      } else if (i == KB.getMoveDown()) {
        direction = 3;
        velocityY = 1*(int)(speedMultiplier);
        moving = 1;
      } else if (i == KB.getMoveLeft()) {
        direction = 0;
        velocityX = -1*(int)(speedMultiplier);
        moving = 1;
      } else if (i == KB.getMoveRight()) {
        direction = 1;
        velocityX = 1*(int)(speedMultiplier);
        moving = 1;
      } else if (i == KB.getBombPlacement()) {
        placeBomb();
      } else if (i == KB.getInGameMenu()) {
        // To do: tao in game menu
        Board.goInGamePane(2);
        // respawn
//        x = 32;
//        y = 32;
      }
    }
  }

  public void update() {
    checkHit();
    checkFlameReceived();
    checkSpeedReceived();
    checkBombPowerUpReceived();
    checkAllEnemiesGone();
    velocityX = 0;
    velocityY = 0;
    moving = 0;
    calculateMove();
    move();

  }

  @Override
  protected void checkHit() {
    super.checkHit();
    for (Flame i : Board.getFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        die();
        return;
      }
    }
    for (AnimatedEntity i : Board.getEnemyList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        die();
        return;
      }
    }
    for(Tile i: Board.getTileList()){
      if (getBoundingBox().intersects(i.getBoundingBox())&& i instanceof Portal) {
        ((Portal) i).whenReceived();
        return;
      }
    }

  }

  public void checkAllEnemiesGone(){
    if(Board.getEnemyList().isEmpty()){
      Board.goInGamePane(0);
    }
  }

  public void checkFlameReceived(){
    for (PowerUpFlame i :Board.getPowerUpFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        this.flameRadius++;
        Sound.playInGameSound(0);
        Board.removeEntity(i);
        System.out.println("Increased power up flame");
        return;
      }
    }
  }

  public void checkSpeedReceived(){
    //todo: CHECK IF CONFLICT WITH VVA CODE.
    for (PowerUpSpeed i :Board.getPowerUpSpeedList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        this.speedMultiplier++;
        Sound.playInGameSound(0);
        Board.removeEntity(i);
        System.out.println("Increased power up speed");
        return;
      }
    }
  }

  public void checkBombPowerUpReceived(){
    for (PowerUpBomb i :Board.getPowerUpBombList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        this.damageMultiplier++;
        Sound.playInGameSound(0);
        Board.removeEntity(i);
        System.out.println("Increased power up bomb");
        return;
      }
    }
  }

  @Override
  public Image getImage() {
    /**
     * It's weird because of my error. Don't change anything in here.
     */
    if(Board.getPlayerNumber()==0){
      return Sprite.player[(int) (direction * 3 + moving * (Board.frame / 20))];
    } else if(Board.getPlayerNumber()==2){
      return Sprite.player1[(int) (direction * 3 + moving * (Board.frame / 20))];
    }else if(Board.getPlayerNumber()==1){
      return Sprite.player2[(int) (direction * 3 + moving * (Board.frame / 20))];
    }else{
      return Sprite.player3[(int) (direction * 3 + moving * (Board.frame / 20))];
    }
  }

  @Override
  protected void move() {
    y += velocityY;
    x += velocityX;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)&& i instanceof Wall) { //specify only the wall
        x -= velocityX;
        y -= velocityY;
        return;
      }
    }
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 4, y + 4, 17, 24);
  }

  public void increaseBomb() {
    bombCount++;
  }
}
