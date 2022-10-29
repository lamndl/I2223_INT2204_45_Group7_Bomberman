package entity.animated.mob;

import javafx.scene.image.Image;

public class Oneal extends Enemy {

  public Oneal(int x, int y) {
    super(x, y);
  }

  @Override
  protected boolean canMove(double x, double y) {
    return false;
  }

  @Override
  protected void move(double xa, double ya) {
    //
  }

  @Override
  public Image getImage() {
    // TODO Auto-generated method stub
    return null;
  }
}
