package entity.animated.mob;

import entity.animated.AnimatedEntity;
import javafx.geometry.BoundingBox;
import mainClass.Board;

public abstract class Mob extends AnimatedEntity {
  protected int hp;
  protected int velocityX;
  protected int velocityY;

  protected int direction = 1;
  protected boolean alive = true;
  protected int moving = 0;

  public void die() {
    Board.removeEntity(this);
  }

  protected void checkHit() {
    for (AnimatedEntity i : Board.getFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        die();
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
