package entity.animated.mob;

import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Doll extends Enemy{

  protected Doll(int x, int y) {
    super(x, y);
    velocityX = 1;
  }

  @Override
  public void update() {

  }

  @Override
  public Image getImage() {
    return Sprite.doll[direction * 3 + (int) (moving * Board.frame / 20)];
  }

  @Override
  protected void move() {

  }

  @Override
  public void calculateMove() {

  }
}
