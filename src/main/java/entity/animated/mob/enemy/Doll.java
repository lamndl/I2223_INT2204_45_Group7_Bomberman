package entity.animated.mob.enemy;

import entity.animated.Bomb;
import entity.tile.Brick;
import entity.tile.Tile;
import entity.tile.Wall;
import java.util.Random;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

// like Balloom random speed
public class Doll extends Enemy {

  private boolean[] canMove = new boolean[]{true, true, true, true};

  public Doll(int x, int y) {
    super(x, y);
    randomMove();
  }


  @Override
  public Image getImage() {
    if (alive) {
      return Sprite.doll[direction % 2 * 3 + (int) (moving * Board.frame / 20)];
    } else {
      return Sprite.doll_dead;
    }
  }

  @Override
  public void calculateMove() {
    move();
  }


  protected void randomMove() {
    Random random = new Random();
    int ran = random.nextInt(0, 4);
    checkCanMove();
    while (!canMove[ran]) {
      ran = random.nextInt(0, 4);
    }

    speed = random.nextInt(1, 5);

    velocityX = 0;
    velocityY = 0;
    switch (ran) {
      case 0:
        velocityX = -speed;
        break;
      case 1:
        velocityX = speed;
        break;
      case 2:
        velocityY = -speed;
        break;
      case 3:
        velocityY = speed;
        break;
      default:
        break;
    }
  }

  protected void checkCanMove() {
    for (int i = 0; i < 4; i++) {
      canMove[i] = true;
    }
    for (Tile t : Board.getTileList()) {
      if (t instanceof Wall || t instanceof Brick) {
        if (t.getX() == x - 32 && t.getY() == y) {
          canMove[0] = false;
        }
        if (t.getX() == x + 32 && t.getY() == y) {
          canMove[1] = false;
        }
        if (t.getX() == x && t.getY() == y - 32) {
          canMove[2] = false;
        }
        if (t.getX() == x && t.getY() == y + 32) {
          canMove[3] = false;
        }
      }
    }
  }

  @Override
  protected void move() {
    moving = 1;
    x += velocityX;
    y += velocityY;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
        x -= velocityX;
        y -= velocityY;
        velocityX = -velocityX;
        velocityY = -velocityY;
        randomMove();
        break;
      }
    }
    for (Bomb j : Board.getBombList()) {
      if (isCollidedWith(j)) {
        x -= velocityX;
        y -= velocityY;
        velocityX = -velocityX;
        velocityY = -velocityY;
        randomMove();
        break;
      }
    }

    if (velocityX > 0) {
      direction = 1;
    } else {
      direction = 0;
    }
  }
}
