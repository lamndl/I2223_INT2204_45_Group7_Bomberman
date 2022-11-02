package entity.animated;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Flame extends AnimatedEntity {
  private int timer = 30;

  protected Flame(int x, int y) {
    super(x, y);
  }

  @Override
  public void update() {
    timer--;
    if (timer == 0) {
      Board.removeEntity(this);
    }
  }

  @Override
  public Image getImage() {
    return Sprite.bomb_exploded2;
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 2, y + 2, 28, 28);
  }
  
}
