package entity.animated.mob;

import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Oneal extends Enemy {

  public Oneal(int x, int y) {
    super(x, y);
  }


  @Override
  public Image getImage() {
    return Sprite.oneal[direction * 3 + (int) (moving * Board.frame / 20)];
  }


  @Override
  public void calculateMove() {
    // TODO Auto-generated method stub
    
  }


  @Override
  protected void move() {
    // TODO Auto-generated method stub
    
  }


  @Override
  public void update() {
    // TODO Auto-generated method stub
    
  }
}
