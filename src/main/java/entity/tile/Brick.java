package entity.tile;

import javafx.scene.image.Image;
import sprite.Sprite;

public class Brick extends Tile {

  public Brick(int x, int y) {
    super(x, y);
  }

  @Override
  public Image getImage() {
    return Sprite.brick;
  }
}
