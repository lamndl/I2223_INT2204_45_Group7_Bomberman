package entity.animated;

import entity.Entity;
import javafx.scene.image.Image;
import sprite.Sprite;

public class AnimatedEntity extends Entity {
  protected int _animate = 0;
  protected final int MAX_ANIMATE = 7500;

  protected void animate() {
    if(_animate < MAX_ANIMATE) _animate++; else _animate = 0;
  }

  protected AnimatedEntity(int x, int y, Image image) {
    super(x, y, image);
  }

  public AnimatedEntity(int x, int y, Sprite _sprite) {
    super(x, y, _sprite);
  }

  public void update() {
    //
  }



}
