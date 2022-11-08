package entity.tile.powerup;

import entity.animated.mob.Bomber;
import javafx.scene.image.Image;
import sprite.Sprite;

public class PowerUpBomb extends PowerUp {
  public PowerUpBomb(int x, int y) {
    super(x, y);
  }

  public Image getImage() {
    return Sprite.powerup_bombs;
  }

  @Override
  protected void effect(Bomber bomber) {
    bomber.increaseBomb();
  }

}
