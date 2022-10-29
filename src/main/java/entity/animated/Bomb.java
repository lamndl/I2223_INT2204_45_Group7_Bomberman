package entity.animated;

import sprite.Sprite;

public class Bomb extends AnimatedEntity {
  public void explode() {
    //
  }

  public Bomb(int x, int y) {
    super(x, y, Sprite.bomb.getFxImage());
  }
}
