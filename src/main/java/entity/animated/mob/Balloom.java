package entity.animated.mob;

import entity.animated.Bomb;
import entity.tile.Tile;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Balloom extends Enemy {

  public Balloom(int x, int y) {
    super(x, y);
    velocityX = 1;
  }


  @Override
  public Image getImage() {
    if (alive == true) {
      return Sprite.balloom[direction * 3 + (int) (moving * Board.frame / 20)];
    } else {
      return Sprite.balloom_dead;
    }
  }


  @Override
  public void calculateMove() {
    move();
  }


  @Override
  protected void move() {
    moving = 1;
    x += velocityX;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
        x -= velocityX;
        velocityX = -velocityX;
        break;
      }

      for (Bomb j : Board.getBombList()) {
        if (isCollidedWith(j)) {
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
        break;
      }
    }

    if (velocityX > 0) {
      direction = 1;
    } else {
      direction = 0;
    }
  }

  public void update() {
    if (alive) {
      checkHit();
      calculateMove();
    } else {
      die();
    }

  }
}
