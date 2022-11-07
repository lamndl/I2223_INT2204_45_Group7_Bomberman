package entity.animated.mob;

import entity.animated.Flame;
import entity.animated.Bomb;
import entity.tile.Tile;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Oneal extends Enemy {
  private int health = 1; //set 2 for testing purpose

  private boolean follow = false;

  private long timeFollow = 0;  // khi chạm bom sẽ tự né

  public Oneal(int x, int y) {
    super(x, y);
    velocityX = 1;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  @Override
  public Image getImage() {
    if (alive) {
      return Sprite.oneal[direction % 2 * 3 + (int) (moving * Board.frame / 20)];
    } else {
      return Sprite.oneal_dead;
    }
  }

  @Override
  public void calculateMove() {

    int enemyX = (x + 16) / 32;
    int enemyY = (y + 16) / 32;
    int bomberX = (Board.getBomber().getX() + 12) / 32;
    int bomberY = (Board.getBomber().getY() + 16) / 32;

    // khoảng cách gần thì sẽ đuổi, nếu không thì đi random (đang cho đi ngang)
    if (Math.pow(enemyX - bomberX, 2) + Math.pow(enemyY - bomberY, 2) < 50) {
      timeFollow--;
      if (timeFollow < 0) {
        follow = true;
      }
      if (timeFollow < -7500) {
        timeFollow = -1;
      }
    } else {
      follow = false;
    }

    if (follow) {
      if (enemyX > bomberX) {
        direction = 0;
        velocityX = -1;
      } else if (enemyX < bomberX) {
        direction = 1;
        velocityX = 1;
      }
      if (enemyY > bomberY) {
        direction = 2;
        velocityY = -1;
      } else if (enemyY < bomberY) {
        direction = 3;
        velocityY = 1;
      }
    }

    move();
  }


  @Override
  protected void move() {
    moving = 1;
    x += velocityX;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
        x -= velocityX;
        if (!follow) {
          velocityX = -velocityX;
        }
        break;
      }

      for (Bomb j : Board.getBombList()) {
        if (isCollidedWith(j)) {
          x -= velocityX;
          setFollow(false);
          velocityX = -velocityX;
          break;
        }
      }

    }

    y += velocityY;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
        y -= velocityY;
        if (!follow) {
          velocityY = -velocityY;
        }
        break;
      }

      for (Bomb j : Board.getBombList()) {
        if (isCollidedWith(j)) {
          y -= velocityY;
          setFollow(false);
          velocityY = -velocityY;
          break;
        }
      }
    }
  }

  public void setFollow(boolean b) {
    if (!b) {
      follow = false;
      timeFollow = 70;
    } else {
      follow = true;
    }
  }

  @Override
  public void update() {
    if (alive) {
      checkHit();
      calculateMove();
    } else {
      die();
    }
  }

  @Override
  public void checkHit(){
    for (int i = 0; i< Board.getFlameList().size();i++) {
      if (getBoundingBox().intersects(Board.getFlameList().get(i).getBoundingBox())) {
        if(getHealth()- (int)(Board.getBomber().getDamage()*Board.getBomber().getDamageMultiplier())<=0){
          setHealth(getHealth()- (int)(Board.getBomber().getDamage()*Board.getBomber().getDamageMultiplier()));
          die();
        }else{
          setHealth(getHealth()- (int)(Board.getBomber().getDamage()*Board.getBomber().getDamageMultiplier()));
          System.out.println("Current health: " + getHealth());
          Board.removeEntity(Board.getFlameList().get(i));
        }
      }
    }
  }

}
