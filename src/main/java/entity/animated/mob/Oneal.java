package entity.animated.mob;

import sprite.Sprite;

public class Oneal extends Enemy {

  public Oneal(int x, int y) {
    super(x, y, Sprite.oneal_right1.getFxImage());
  }

  @Override
  protected boolean canMove(double x, double y) {
    return false;
  }

  @Override
  protected void calculateMove() {

  }

  @Override
  protected void move(double xa, double ya) {

  }
}
