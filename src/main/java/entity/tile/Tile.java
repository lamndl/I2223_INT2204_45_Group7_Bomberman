package entity.tile;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Tile extends Entity {

  protected Tile(int x, int y, Image image) {
    super(x, y, image);
  }

  protected Tile() {}

  @Override
  public void draw(GraphicsContext gc) {
    gc.drawImage(image, x, y);
  }

  /**
   * Mặc định không cho bất cứ một đối tượng nào đi qua
   */
  @Override
  public boolean isCollidedWith(Entity other) {
    return false;
  }
}
