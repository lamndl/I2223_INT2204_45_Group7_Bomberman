package entity.animated.mob;

import entity.animated.AnimatedEntity;
import entity.animated.Flame;
import entity.tile.Tile;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Balloom extends Enemy {
  private int health = 1;

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public Balloom(int x, int y) {
    super(x, y);
    velocityX = 1;
  }


  @Override
  public Image getImage() {
    return Sprite.balloom[direction * 3 + (int) (moving * Board.frame / 20)];
  }


  @Override
  public void calculateMove() {}


  @Override
  protected void move() {
    moving = 1;
    x += velocityX;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
        x -= velocityX;
        velocityX = -velocityX;
        break;
      }
    }
    y += velocityY;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
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

  @Override
  public void checkHit(){
    for (Flame i : Board.getFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        if(getHealth()- (int)(Board.getBomber().getDamage()*Board.getBomber().getDamageMultiplier())<=0){
          die();
        }else{
          setHealth(getHealth()- (int)(Board.getBomber().getDamage()*Board.getBomber().getDamageMultiplier()));
          System.out.println("Current health: " + getHealth());
        }
      }
    }
  }
}
