package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sprite.Sprite;

public abstract class Entity {
  protected int x;
  protected int y;

  public void draw() {
    //
  }
  protected Image img;
  public Entity(){}

  //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
  public Entity( int xUnit, int yUnit, Image img) {
    this.x = xUnit * Sprite.SCALED_SIZE;
    this.y = yUnit * Sprite.SCALED_SIZE;
    this.img = img;
  }

  public void render(GraphicsContext gc) {
    gc.drawImage(img, x, y);
  }
  public abstract void update();

  public boolean isCollidedWith(Entity other) {
    //
    return false;
  }
}
