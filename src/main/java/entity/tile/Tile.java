package entity.tile;

import entity.Entity;
import javafx.geometry.BoundingBox;

public abstract class Tile extends Entity {

  protected Tile(int x, int y) {
    super(x, y);
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x, y, 32, 32);
  }
}
