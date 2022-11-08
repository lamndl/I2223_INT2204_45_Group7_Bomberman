package entity.animated.mob;

import entity.animated.AnimatedEntity;
import javafx.geometry.BoundingBox;
import mainClass.Board;
import mainClass.Sound;

public abstract class Mob extends AnimatedEntity {
  protected int hp;
  protected int velocityX;
  protected int velocityY;

  protected int direction = 1;
  protected boolean alive = true;
  protected int moving = 0;
  public static boolean allowThroughBomb = true;
  protected int timer = 60;

  public void die() {
    timer--;
    if (timer == 0) {
      Board.removeEntity(this);
    }
    if(this instanceof Enemy){
      Sound.playInGameSound(0);
    }


  }

  protected void checkHit() {
    for (AnimatedEntity i : Board.getFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        alive = false;
        return;
      }
    }
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 4, y + 4, 24, 24);
  }

  protected Mob(int x, int y) {
    super(x, y);
  }

  protected abstract void move();

  public abstract void calculateMove();

  public void setMoving(int moving) {
    this.moving = moving;
  }

}
