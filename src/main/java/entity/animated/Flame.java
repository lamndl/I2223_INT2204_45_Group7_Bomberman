package entity.animated;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Flame extends AnimatedEntity {
  private int timer = 30;
  int pos;

  protected Flame(int pos, int x, int y) {
    super(x, y);
    this.pos = pos;
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
    if (timer > 20) {
      return Sprite.flame[pos * 3];
    }
    if (timer > 10) {
      return Sprite.flame[pos * 3 + 1];
    }
    return Sprite.flame[pos * 3 + 2];
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 2, y + 2, 28, 28);
  }
  
}
