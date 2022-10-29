package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
  protected int x;
  protected int y;

  public final void draw(GraphicsContext gc) {
    gc.drawImage(getImage(), x, y);
  }

  /**
   * xử lý 2 Entity va chạm
   */
  public boolean isCollidedWith(Entity other) {
    //
    return false;
  }

  protected Entity(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public abstract Image getImage();
}
