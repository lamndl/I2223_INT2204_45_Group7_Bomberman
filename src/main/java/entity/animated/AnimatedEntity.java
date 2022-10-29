package entity.animated;

import entity.Entity;
import javafx.scene.image.Image;
import sprite.Sprite;

public class AnimatedEntity extends Entity {
  protected int animateIndex = 0;
  protected static final int MAX_ANIMATE = 7500;

  protected void animate() {
    if (animateIndex < MAX_ANIMATE) {
      animateIndex++;
    } else {
      animateIndex = 0;
    }
  }

  protected AnimatedEntity(int x, int y, Image image) {
    super(x, y, image);
  }

  public AnimatedEntity(int x, int y, Sprite sprite) {
    super(x, y, sprite);
  }

  public void update() {
    //
  }



}
