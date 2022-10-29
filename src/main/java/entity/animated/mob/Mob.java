package entity.animated.mob;

import entity.animated.AnimatedEntity;

public abstract class Mob extends AnimatedEntity {
  protected int hp;
  protected int velocityX;
  protected int velocityY;

  protected int direction = 1;
  protected boolean alive = true;
  protected int moving = 0;

  public void die() {
    //
  }

  protected Mob(int x, int y) {
    super(x, y);
  }

  // kiểm tra xem Mob có thể move được không
  protected abstract boolean canMove(double x, double y);

  // tính toán hướng đi
  // protected abstract void calculateMove();

  protected abstract void move(double xa, double ya);

  public void setMoving(int moving) {
    this.moving = moving;
  }
}
