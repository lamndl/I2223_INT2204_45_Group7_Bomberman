package entity.animated.mob;

import javafx.scene.input.KeyEvent;
import sprite.Sprite;

public class Bomber extends Mob {
  private int maximumBombCount;
  private int currentBombCount;
  private int flameLength;
  private double speedMultiplier;

  public void placeBomb() {
    //
  }

  public Bomber() {
    x = 1;
    y = 1;
    image = Sprite.player_right.getFxImage();
  }

  public void update(KeyEvent keyEvent) {
    switch (keyEvent.getCode()) {
      case UP:
        y -= 2;
        break;
      case DOWN:
        y += 2;
        break;
      case LEFT:
        x -= 2;
        break;
      case RIGHT:
        x += 2;
        break;
    }
  }

}
