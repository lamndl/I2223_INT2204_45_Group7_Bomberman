package entity.tile;

import entity.Entity;
import sprite.Sprite;

public class Grass extends Tile {

  public Grass() {}

  public Grass(int x, int y) {
    super(x, y, Sprite.grass.getFxImage());
  }


  /**
   * grass cho tất cả đi qua
   */
  @Override
  public boolean isCollidedWith(Entity other) {
    return true;
  }
}
