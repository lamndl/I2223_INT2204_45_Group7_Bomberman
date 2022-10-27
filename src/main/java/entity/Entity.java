package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
  protected int x;
  protected int y;

  protected Image image;

  public void draw(GraphicsContext gc) {
    gc.drawImage(image, x, y);
  }

  public boolean isCollidedWith(Entity other) {
    //
    return false;
  }
  
}
