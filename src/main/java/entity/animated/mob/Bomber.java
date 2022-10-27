package entity.animated.mob;

import sprite.Sprite;

public class Bomber extends Mob {
  private int maximumBombCount;
  private int currentBombCount;
  private int flameLength;
  private double speedMultiplier;

  public void placeBomb() {
    //
  }

  public Bomber() {
    _x = 1;
    _y = 1;
    image = Sprite.player_right.getFxImage();
  }
}
