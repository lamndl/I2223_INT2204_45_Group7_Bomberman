package entity.tile;

import entity.Entity;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import sprite.Sprite;

public class Grass extends Tile {

  public Grass(int x, int y) {
    super(x, y);
  }


  /**
   * grass cho tất cả đi qua .
   */
  @Override
  public boolean isCollidedWith(Entity other) {
    return true;
  }


  @Override
  public Image getImage() {
    return Sprite.grass;
  }


  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(0, 0, 0, 0);
  }


  @Override
  public void update() {
    //
  }
}
