package entity.tile;

import entity.animated.AnimatedEntity;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Brick extends Tile {

  private int timer = 90;
  private boolean destroy = false;

  public Brick(int x, int y) {
    super(x, y);
  }

  protected void checkHit() {
    for (AnimatedEntity i : Board.getFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        destroy = true;
        Board.removeEntity(i);
        return;
      }
    }
  }

  @Override
  public Image getImage() {
    if (destroy == false) {
      return Sprite.brick;
    }
    if (timer > 60) {
      return Sprite.brick_exploded;
    }
    if (timer > 30) {
      return Sprite.brick_exploded1;
    }
    return Sprite.brick_exploded2;
  }


  @Override
  public void update() {
    if (destroy == false) {
      checkHit();
    } else {
      timer--;
      if (timer == 0) {
        Board.removeEntity(this);
      }
    }
  }
}
