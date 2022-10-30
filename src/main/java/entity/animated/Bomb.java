package entity.animated;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Bomb extends AnimatedEntity {
  public void explode() {
    //
  }

  public Bomb(int x, int y) {
    super(x, y);
  }

  @Override
  public Image getImage() {
    return Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, (int) Board.frame, 120);
  }

  @Override
  protected BoundingBox getBoundingBox() {
    return new BoundingBox(x, y, 32, 32);
  }

  @Override
  public void update() {
    // TODO Auto-generated method stub
    
  }
}
