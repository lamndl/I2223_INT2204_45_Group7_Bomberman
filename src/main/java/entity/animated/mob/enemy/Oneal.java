package entity.animated.mob.enemy;

import entity.animated.Bomb;
import entity.tile.Tile;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Oneal extends Enemy {

  private boolean follow = false;

  private long timeFollow = 0; // khi chạm bom sẽ tự né

  public Oneal(int x, int y) {
    super(x, y);
    velocityX = 1;
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
    if (Math.pow(enemyX - bomberX, 2) + Math.pow(enemyY - bomberY, 2) < 10) {
      timeFollow--;
      if (timeFollow < 0) {
        follow = true;
      }
      if (timeFollow < -7500) {
        timeFollow = -1;
      }
    } else {
      follow = false;
      timeFollow = 0;
    }

    if (follow) {
      if (enemyX > bomberX) {
        direction = 0;
        velocityX = -2;
      } else if (enemyX < bomberX) {
        direction = 1;
        velocityX = 2;
      }
      if (enemyY > bomberY) {
        direction = 2;
        velocityY = -2;
      } else if (enemyY < bomberY) {
        direction = 3;
        velocityY = 2;
      }
    } else {
      if (Math.abs(velocityX) > 1) {
        velocityX /= Math.abs(velocityX);
      }
      if (Math.abs(velocityY) > 1) {
        velocityY /= Math.abs(velocityY);
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


}
