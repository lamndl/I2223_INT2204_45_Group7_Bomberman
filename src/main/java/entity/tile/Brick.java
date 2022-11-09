package entity.tile;

import entity.animated.AnimatedEntity;
import javafx.scene.image.Image;
import mainClass.App;
import mainClass.Board;
import sprite.Sprite;

public class Brick extends Tile {

  private int timer = 60;
  private boolean destroy = false;

  public Brick(int x, int y) {
    super(x, y);
  }

  protected void checkHit() {
    for (AnimatedEntity i : Board.getFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        destroy = true;
        App.currentPlayer.setBlocksBroke(App.currentPlayer.getBlocksBroke()+1);
        Board.removeEntity(i);
        return;
      }
    }
  }

  @Override
  public Image getImage() {
    if (!destroy) {
      return Sprite.brick;
    }
    if (timer > 40) {
      return Sprite.brick_exploded;
    }
    if (timer > 20) {
      return Sprite.brick_exploded1;
    }
    return Sprite.brick_exploded2;
  }


  @Override
  public void update() {
    if (!destroy) {
      checkHit();
    } else {
      timer--;
      if (timer == 0) {
        Board.removeEntity(this);
      }
    }
  }
}
