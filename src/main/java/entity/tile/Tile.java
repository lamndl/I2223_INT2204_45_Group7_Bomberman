package entity.tile;

import entity.Entity;

public abstract class Tile extends Entity {

  protected Tile(int x, int y) {
    super(x, y);
  }

  /**
   * Mặc định không cho bất cứ một đối tượng nào đi qua
   */
  @Override
  public boolean isCollidedWith(Entity other) {
    return false;
  }
}
