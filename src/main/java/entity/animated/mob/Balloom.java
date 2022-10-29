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
    Image sprite;
    switch (direction) {
      case 3:
        sprite = Sprite.balloom_left1;
        if (moving) {
          sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2,
              Sprite.balloom_left3, (int) Board.frame, 120);
        }
        break;
      default:
        sprite = Sprite.balloom_right1;
        if (moving) {
          sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2,
              Sprite.balloom_right3, (int) Board.frame, 120);
        }
        break;
    }
    return sprite;
  }

  @Override
  protected void move(double xa, double ya) {
    //
  }
}
