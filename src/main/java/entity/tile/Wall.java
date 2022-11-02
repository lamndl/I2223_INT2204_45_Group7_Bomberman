package entity.tile;

import entity.animated.AnimatedEntity;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Wall extends Tile {

  public Wall(int x, int y) {
    super(x, y);
  }

  protected void checkHit() {
    for (AnimatedEntity i : Board.getFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        Board.removeEntity(i);
        return;
      }
    }
  }

  @Override
  public Image getImage() {
    return Sprite.wall;
  }

  @Override
  public void update() {
    checkHit();
  }

}
