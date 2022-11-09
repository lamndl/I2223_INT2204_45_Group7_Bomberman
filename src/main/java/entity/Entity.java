package entity;

import ai.Node;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
  protected int x;
  protected int y;

  public final void draw(GraphicsContext gc) {
    gc.drawImage(getImage(), x, y);
  }

  public boolean isCollidedWith(Entity other) {
    return getBoundingBox().intersects(other.getBoundingBox());
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

  public Node getNode() {
    return new Node((y+13) / 32, (x+15) / 32);
  }

  public abstract void update();

  public abstract Image getImage();

  public abstract BoundingBox getBoundingBox();
}
