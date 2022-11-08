package entity.tile.powerup;

import entity.animated.mob.Bomber;
import javafx.scene.image.Image;
import sprite.Sprite;

public class PowerUpFlame extends PowerUp {

  public PowerUpFlame(int x, int y) {
    super(x, y);
  }

  public Image getImage() {
    return Sprite.powerup_flames;
  }

  @Override
  protected void effect(Bomber bomber) {
    bomber.increaseFlamLength();
  }

}
