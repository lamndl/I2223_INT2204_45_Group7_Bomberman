package entity.animated.mob;

import entity.Entity;
import javafx.scene.image.Image;

public class Bomber extends Entity {
  private int maximumBombCount;
  private int currentBombCount;
  private int flameLength;
  private double speedMultiplier;

  public Bomber(int x, int y, Image img) {
    super( x, y, img);
  }

  @Override
  public void update() {

  }
  public void placeBomb() {
    //
  }
}
