package entity.animated.mob.enemy;

import entity.animated.Bomb;
import entity.tile.Tile;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

//like Oneal but follow with random speed
public class Minvo extends Enemy {

  private boolean follow = false;
  private long timeFollow = 0;  // khi chạm bom sẽ tự né

  public Minvo(int x, int y) {
    super(x, y);
    velocityX = speed;
    velocityY = speed;
  }

  @Override
  public Image getImage() {
    if (alive) {
      return Sprite.minvo[direction % 2 * 3 + (int) (moving * Board.frame / 20)];
    } else {
      return Sprite.minvo_dead;
    }
  }

  @Override
  public void calculateMove() {

    int enemyX = (x + 16) / 32;
    int enemyY = (y + 16) / 32;
    int bomberX = (Board.getBomber().getX() + 12) / 32;
    int bomberY = (Board.getBomber().getY() + 16) / 32;
    follow = false;
    speed = 1;

    // khoảng cách gần thì sẽ đuổi, nếu không thì đi random
    if (Math.pow(enemyX - bomberX, 2) + Math.pow(enemyY - bomberY, 2) < 25) {
      timeFollow--;
      if (timeFollow < 0) {
        setFollow(true);
      }
      if (timeFollow < -7500) {
        timeFollow = -1;
      }
    } else {
      follow = false;
      speed = 1;
    }

    if (follow) {
      if (enemyX > bomberX) {
        direction = 0;
        velocityX = -speed;
      } else if (enemyX < bomberX) {
        direction = 1;
        velocityX = speed;
      }
      if (enemyY > bomberY) {
        direction = 2;
        velocityY = -speed;
      } else if (enemyY < bomberY) {
        direction = 3;
        velocityY = speed;
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
          setFollow(false);
          x -= velocityX;
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
          setFollow(false);
          y -= velocityY;
          velocityY = -velocityY;
          break;
        }
      }
    }
  }

  public void setFollow(boolean b) {
    if (!b) {
      follow = false;
      speed = 1;
      timeFollow = 70;
    } else {
      follow = true;
      speed = 2;
    }
  }

}
