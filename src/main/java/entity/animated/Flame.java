package entity.animated;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Flame extends AnimatedEntity {
  private int timer = 90;

  protected Flame(int x, int y) {
    super(x, y);
  }

  @Override
  public void update() {
    timer--;
    if (getBoundingBox().intersects(Board.bomber.getBoundingBox())) {
      Board.removeEntity(Board.bomber);
    }
    if (timer == 0) {
      Board.removeEntity(this);
    }
  }

  @Override
  public Image getImage() {
    return Sprite.bomb_exploded2;
  }

  @Override
  protected BoundingBox getBoundingBox() {
    return new BoundingBox(x, y, 32, 32);
  }
  
}
