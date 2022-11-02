package entity.animated.mob;

import entity.animated.Flame;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Oneal extends Enemy {
  private int health = 2; //set 2 for testing purpose

  public Oneal(int x, int y) {
    super(x, y);
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
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
    checkHit();
    calculateMove();
    move();
  }

  @Override
  public void checkHit(){
    for (Flame i : Board.getFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        if(getHealth()- (int)(Board.getBomber().getDamage()*Board.getBomber().getDamageMultiplier())<=0){
          setHealth(getHealth()- (int)(Board.getBomber().getDamage()*Board.getBomber().getDamageMultiplier()));
          die();
        }else{
          setHealth(getHealth()- (int)(Board.getBomber().getDamage()*Board.getBomber().getDamageMultiplier()));
          System.out.println("Current health: " + getHealth());
          Board.removeEntity(i);
        }
      }
    }
  }
}
