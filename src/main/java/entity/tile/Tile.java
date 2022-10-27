package entity.tile;

import entity.Entity;
import javafx.scene.image.Image;

public abstract class Tile extends Entity {

  protected Tile(int x, int y, Image image) {
    super(x, y, image);
  }

  protected Tile() {}
  
}
