package entity.animated.mob;

public abstract class Enemy extends Mob {
  //private int health;

  public abstract int getHealth();

  protected Enemy(int x, int y) {
    super(x, y);
    moving = 1; //aesthetic purpose only
  }

}
