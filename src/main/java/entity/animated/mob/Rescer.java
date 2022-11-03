package entity.animated.mob;

import entity.tile.Brick;
import entity.tile.Tile;
import entity.tile.Wall;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Rescer extends Enemy{
  /**
   * last enemy. Thinking...
   */
  private int health = 3; //buff health
  private long lastestFrame;
  public Rescer(int x, int y) {
    super(x, y);
    velocityX = 1; //buff speed
    setLastestFrame(Board.getUnresetFrame());
  }

  public void setLastestFrame(long frame){
    lastestFrame=frame;
  }

  @Override
  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  @Override
  public Image getImage() {
    return Sprite.rescer[direction * 3 + (int) (moving * Board.frame / 20)];
  }

  @Override
  public void calculateMove() {}

  @Override
  protected void move() {
    moving = 1;
    x += velocityX;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)&& (i instanceof Wall || i instanceof Brick)) {
        x -= velocityX;
        velocityX = -velocityX;
        break;
      }
    }
    y += velocityY;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)&& (i instanceof Wall|| i instanceof Brick)) {
        y -= velocityY;
        break;
      }
    }

  }

  public void update() {
    checkHit();
    calculateMove();
    move();

  }

  public void rotate(){
    velocityX = -velocityX;
  }

  @Override
  public void checkHit(){
    for (int i = 0; i< Board.getFlameList().size();i++) {
      if (getBoundingBox().intersects(Board.getFlameList().get(i).getBoundingBox())) {
        if(getHealth()- (int)(Board.getBomber().getDamage()*Board.getBomber().getDamageMultiplier())<=0){
          setHealth(getHealth()- (int)(Board.getBomber().getDamage()*Board.getBomber().getDamageMultiplier()));
          die();
        }else{
          setHealth(getHealth()- (int)(Board.getBomber().getDamage()*Board.getBomber().getDamageMultiplier()));
          System.out.println("Current health: " + getHealth());
          if(Board.getUnresetFrame()-lastestFrame>=90){
            rotate();
            lastestFrame=Board.getUnresetFrame();
          }
          Board.removeEntity(Board.getFlameList().get(i));
        }
      }
    }
  }
}
