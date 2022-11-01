package entity.tile;

import entity.animated.AnimatedEntity;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Brick extends Tile {

  public Brick(int x, int y) {
    super(x, y);
  }

  protected void checkHit() {
    for (AnimatedEntity i : Board.getFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        Board.removeEntity(this);
        Board.addEntity(new Grass(x, y));
        return;
      }
    }
  }


  @Override
  public Image getImage() {
    return Sprite.brick;
  }

  @Override
  public void update() {
    checkHit();
  }
}
