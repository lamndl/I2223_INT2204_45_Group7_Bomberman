package entity.animated;

import javafx.scene.image.Image;
import sprite.Sprite;

public class Bomb extends AnimatedEntity {
  public void explode() {
    //
  }

  public Bomb(int x, int y, Image image) {
    super(x, y, Sprite.bomb.getFxImage());
  }
}
