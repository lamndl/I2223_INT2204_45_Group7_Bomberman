package entity.tile;

import sprite.Sprite;

public class Wall extends Tile {

  public Wall(int x, int y) {
    super(x, y, Sprite.wall.getFxImage());
  }

}
