package entity.animated.mob;

import entity.animated.AnimatedEntity;
import javafx.scene.image.Image;

public abstract class Mob extends AnimatedEntity {
  protected int hp;
  protected int velocityX;
  protected int velocityY;

  public void die() {
    //
  }

  protected Mob(int x, int y, Image image, int hp) {
    super(x, y, image);
    this.hp = hp;
  }

  protected Mob(int x, int y, Image image) {
    super(x, y, image);
  }
  
}
