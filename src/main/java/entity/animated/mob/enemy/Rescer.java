package entity.animated.mob.enemy;

import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Rescer extends Enemy {

  public Rescer(int x, int y) {
    super(x, y);
  }

  @Override
  public Image getImage() {
    if (alive) {
      return Sprite.rescer[direction % 2 * 3 + (int) (moving * Board.frame / 20)];
    } else {
      return Sprite.rescer_dead;
    }
  }

  @Override
  protected void move() {

  }

  @Override
  public void calculateMove() {

  }
}
