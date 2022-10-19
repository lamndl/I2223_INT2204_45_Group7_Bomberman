package entity.animated.mob;

import entity.animated.AnimatedEntity;

public abstract class Mob extends AnimatedEntity {
  protected int hp;
  protected int velocityX;
  protected int velocityY;

  public void die() {
    //
  }
}
