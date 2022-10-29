package entity.animated.mob;

import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Balloom extends Enemy {

  public Balloom(int x, int y) {
    super(x, y);
  }

  @Override
  protected boolean canMove(double x, double y) {
    return false;
  }

  @Override
  public Image getImage() {
    return Sprite.balloom[direction * 3 + (int) (moving * Board.frame / 40)];
  }

  @Override
  protected void move(double xa, double ya) {
    //
  }
}
