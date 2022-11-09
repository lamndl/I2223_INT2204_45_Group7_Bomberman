package entity.animated.mob.enemy;

import entity.animated.mob.Mob;

public abstract class Enemy extends Mob {

  protected int speed = 1;  //default

  protected Enemy(int x, int y) {
    super(x, y);
    moving = 1; //aesthetic purpose only
  }
  public void update() {
    if (alive) {
      checkHit();
      calculateMove();
    } else {
      die();
    }

  }

}
