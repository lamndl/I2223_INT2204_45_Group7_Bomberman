package entity.tile;

import sprite.Sprite;

public class Grass extends Tile {

  public Grass(int x, int y) {
    super(x, y, Sprite.grass.getFxImage());
  }

}
