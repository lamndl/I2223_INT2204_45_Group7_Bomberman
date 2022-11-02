package entity.animated.mob;

import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Oneal extends Enemy {

  public Oneal(int x, int y) {
    super(x, y);
  }


  @Override
  public Image getImage() {
    if (alive) {
      return Sprite.oneal[direction * 3 + (int) (moving * Board.frame / 20)];
    } else {
      return Sprite.oneal_dead;
    }
  }


  @Override
  public void calculateMove() {
    // TODO Auto-generated method stub

  }


  @Override
  protected void move() {
    // TODO Auto-generated method stub

  }


  @Override
  public void update() {
    if (alive) {
      checkHit();
      calculateMove();
    } else {
      die();
    }

  }
}
