package entity.animated.mob;

import static mainClass.App.KB;

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
import mainClass.Board;
import mainClass.Sound;
import sprite.Sprite;

public class Bomber extends Mob {

  private int bombCount = 1;
  private int flameRadius = 1; // increase +1 if receive flame item
  private double speedMultiplier = 1;
  private double damageMultiplier = 1;
  private int flameLength;
  protected int timer = 120;
  private double damage = 1;

  /**
   * I separate damage and damage multiplier so other characters can have different strengths Of
   * course only if we have time Todo: (MEDIUM PRIORITY) custom strengths for different characters.
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

  public double getDamageMultiplier() {
    return damageMultiplier;
  }

  public void setDamageMultiplier(double d) {
    damageMultiplier = d;
  }

  public double getSpeedMultiplier() {
    return speedMultiplier;
  }

  public void setSpeedMultiplier(double speedMultiplier) {
    this.speedMultiplier = speedMultiplier;
  }

  public int getFlameRadius() {
    return flameRadius;
  }

  public void setFlameRadius(int radius) {
    flameRadius = radius;
  }

  public void explode(int x, int y) {
    Board.addEntity(new Flame(x, y));
    boolean north = true;
    boolean south = true;
    boolean east = true;
    boolean west = true;
    for (int i = 1; i <= getFlameRadius(); i++) {
      if (Board.getTileEntityAt(x, y + 32 * i) instanceof Wall) {
        south = false;
      }
      if (Board.getTileEntityAt(x, y - 32 * i) instanceof Wall) {
        north = false;
      }
      if (Board.getTileEntityAt(x + 32 * i, y) instanceof Wall) {
        east = false;
      }
      if (Board.getTileEntityAt(x - 32 * i, y) instanceof Wall) {
        west = false;
      }
      if (east) {
        Board.addEntity(new Flame(x + 32 * i, y));
      }
      if (west) {
        Board.addEntity(new Flame(x - 32 * i, y));
      }
      if (south) {
        Board.addEntity(new Flame(x, y + 32 * i));
      }
      if (north) {
        Board.addEntity(new Flame(x, y - 32 * i));
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
        velocityY = -1 * (int) (speedMultiplier);
        moving = 1;
      } else if (i == KB.getMoveDown()) {
        direction = 3;
        velocityY = 1 * (int) (speedMultiplier);
        moving = 1;
      } else if (i == KB.getMoveLeft()) {
        direction = 0;
        velocityX = -1 * (int) (speedMultiplier);
        moving = 1;
      } else if (i == KB.getMoveRight()) {
        direction = 1;
        velocityX = 1 * (int) (speedMultiplier);
        moving = 1;
      } else if (i == KB.getBombPlacement()) {
        placeBomb();
      } else if (i == KB.getInGameMenu()) {
        // To do: tao in game menu
        Board.goInGamePane(2);
        // respawn
        // x = 32;
        // y = 32;
      }
    }


    if (allowThroughBomb) {
      boolean fl = false;
      for (Bomb i : Board.getBombList()) {
        if (isCollidedWith(i)) {
          fl = true;
          break;
        }
      }
      if (!fl && !Board.getBombList().isEmpty()) {
        allowThroughBomb = false;
      }
    }
  }

  public void update() {
    checkHit();
    checkFlameReceived();
    checkSpeedReceived();
    checkBombPowerUpReceived();
    checkIfGoToPortal();
    checkAllEnemiesGone();
    velocityX = 0;
    velocityY = 0;
    moving = 0;
    calculateMove();
    move();
    // if (alive) {
    // checkHit();
    // velocityX = 0;
    // velocityY = 0;
    // moving = 0;
    // calculateMove();
    // move();
    // } else {
    // die();
    // }

  }

  @Override
  protected void checkHit() {
    super.checkHit();
    for (Enemy i : Board.getEnemyList()) {
      if (isCollidedWith(i)) {
        alive = false;
        return;
      }
    }
    for (AnimatedEntity i : Board.getEnemyList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        die();
        return;
      }
    }


  }

  public void checkAllEnemiesGone() {
    if (Board.getEnemyList().isEmpty()) {
      Board.goInGamePane(0);
    }
  }

  public void checkIfGoToPortal() {
    for (Tile i : Board.getTileList()) {
      if (getBoundingBox().intersects(i.getBoundingBox()) && i instanceof Portal) {
        ((Portal) i).whenReceived();
        System.out.println("Received");
        return;
      }
    }
  }

  public void checkFlameReceived() {
    for (PowerUpFlame i : Board.getPowerUpFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        this.flameRadius++;
        Sound.playInGameSound(0);
        Board.removeEntity(i);
        System.out.println("Increased power up flame");
        return;
      }
    }
  }

  public void checkSpeedReceived() {
    // todo: CHECK IF CONFLICT WITH VVA CODE.
    for (PowerUpSpeed i : Board.getPowerUpSpeedList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        this.speedMultiplier++;
        Sound.playInGameSound(0);
        Board.removeEntity(i);
        System.out.println("Increased power up speed");
        return;
      }
    }
  }

  public void checkBombPowerUpReceived() {
    for (PowerUpBomb i : Board.getPowerUpBombList()) {
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
  public void die() {
    timer--;
    if (timer == 0) {
      Board.removeEntity(this);

      // go to scene end game and replay
      Board.goEndGame();
    }

  }

  @Override
  public Image getImage() {
    /**
     * It's weird because of my error. Don't change anything in here.
     */
    // if(Board.getPlayerNumber()==0){
    // return Sprite.player[(int) (direction * 3 + moving * (Board.frame / 20))];
    // } else if(Board.getPlayerNumber()==2){
    // return Sprite.player1[(int) (direction * 3 + moving * (Board.frame / 20))];
    // }else if(Board.getPlayerNumber()==1){
    // return Sprite.player2[(int) (direction * 3 + moving * (Board.frame / 20))];
    // }else{
    // return Sprite.player3[(int) (direction * 3 + moving * (Board.frame / 20))];
    if (alive) {
      return Sprite.player[(int) (direction * 3 + moving * (Board.frame / 20))];
    } else {
      if (timer > 80) {
        return Sprite.player_dead1;
      }
      if (timer > 40) {
        return Sprite.player_dead2;
      }
      return Sprite.player_dead3;
    }
  }

  @Override
  protected void move() {
    y += velocityY;
    x += velocityX;

    checkWin();

    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i) && i instanceof Wall) { // specify only the wall
        x -= velocityX;
        y -= velocityY;
        return;
      }
    }

    if (!allowThroughBomb) {
      for (Bomb i : Board.getBombList()) {
        if (isCollidedWith(i)) {
          x -= velocityX;
          y -= velocityY;
          return;
        }
      }
    }

  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 4, y + 5, 17, 24);
  }

  public void increaseBomb() {
    bombCount++;
  }

  public void checkWin() {
    if (!Board.getEnemyList().isEmpty()) {
      return;
    }
    for (Tile i : Board.getTileList()) {
      if ((i instanceof Portal) && this.isCollidedWith(i)) {
        // to next level
        Board.goEndGame();
        return;
      }
    }
  }

}
