package entity.tile;

import sprite.Sprite;

public class Brick extends Tile {

  public Brick(int x, int y) {
    super(x, y, Sprite.brick.getFxImage());
  }
}
