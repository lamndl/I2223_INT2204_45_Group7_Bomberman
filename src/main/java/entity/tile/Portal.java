package entity.tile;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
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

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 4, y + 4, 24 , 24);
  }
}
