package entity.tile;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Portal extends Tile {
  public Portal(int x, int y) {
    super(x, y);
  }

  @Override
  public Image getImage() {
    return Sprite.portal;
  }

  @Override
  public void update() {

  }

}
