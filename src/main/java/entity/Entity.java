package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sprite.Sprite;

public abstract class Entity {
  protected int x;
  protected int y;

  protected Image image;
  protected Sprite _sprite;

  public void draw(GraphicsContext gc) {
  }

  /**
   * xử lý 2 Entity va chạm
   */
  public boolean isCollidedWith(Entity other) {
    //
    return false;
  }

  protected Entity() {

  }

  protected Entity(int x, int y, Image image) {
    this.x = x;
    this.y = y;
    this.image = image;
  }

  protected Entity(int x, int y, Sprite _sprite) {
    this.x = x;
    this.y = y;
    this._sprite = _sprite;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Sprite get_sprite() {
    return _sprite;
  }
}
