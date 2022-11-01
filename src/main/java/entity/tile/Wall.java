package entity.tile;

import javafx.scene.image.Image;
import sprite.Sprite;

public class Wall extends Tile {

  public Wall(int x, int y) {
    super(x, y);
  }

  @Override
  public Image getImage() {
    return Sprite.wall;
  }

  @Override
  public void update() {
    // TODO Auto-generated method stub
    
  }

}
