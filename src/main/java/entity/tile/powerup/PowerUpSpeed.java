package entity.tile.powerup;

import entity.animated.mob.Bomber;
import entity.tile.PowerUp;
import javafx.scene.image.Image;
import sprite.Sprite;

public class PowerUpSpeed extends PowerUp {
  public PowerUpSpeed(int x, int y) {
    super(x, y);
  }

  public Image getImage() {
    return Sprite.powerup_speed;
  }

  @Override
  protected void effect(Bomber bomber) {
    bomber.increaseSpeed();
  }
}
