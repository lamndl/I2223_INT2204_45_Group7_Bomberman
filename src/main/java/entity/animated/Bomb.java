package entity.animated;

import entity.animated.mob.Bomber;
import entity.tile.Brick;
import entity.tile.Tile;
import entity.tile.Wall;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import mainClass.Board;
import mainClass.Sound;
import sprite.Sprite;

public class Bomb extends AnimatedEntity {
  private int timer = 90;

  private int checkWall(Flame flame) {
    for (Tile t : Board.getTileList()) {
      if (t instanceof Wall && t.isCollidedWith(flame)) {
        return 1;
      }
      if (t instanceof Brick && t.isCollidedWith(flame)) {
        return 2;
      }
    }
    return 0;
  }

  public void explode() {
    Board.getBomber().increaseBomb();
    Board.addEntity(new Flame(0, x, y));
    Sound.playInGameSound(7);
    int length = Board.getBomber().getFlameLength();

    for (int i = 1; i <= length; i++) {
      int pos = 5;
      if (i == length) {
        pos = 2;
      }
      Flame flame = new Flame(pos, x + 32 * i, y);
      int check = checkWall(flame);
      if (check == 1) {
        break;
      } else {
        Board.addEntity(flame);
        if (check == 2) {
          break;
        }
      }
    }
    for (int i = 1; i <= length; i++) {
      int pos = 5;
      if (i == length) {
        pos = 1;
      }
      Flame flame = new Flame(pos, x - 32 * i, y);
      int check = checkWall(flame);
      if (check == 1) {
        break;
      } else {
        Board.addEntity(flame);
        if (check == 2) {
          break;
        }
      }
    }
    for (int i = 1; i <= length; i++) {
      int pos = 6;
      if (i == length) {
        pos = 4;
      }
      Flame flame = new Flame(pos, x, y + 32 * i);
      int check = checkWall(flame);
      if (check == 1) {
        break;
      } else {
        Board.addEntity(flame);
        if (check == 2) {
          break;
        }
      }
    }
    for (int i = 1; i <= length; i++) {
      int pos = 6;
      if (i == length) {
        pos = 3;
      }
      Flame flame = new Flame(pos, x, y - 32 * i);
      int check = checkWall(flame);
      if (check == 1) {
        break;
      } else {
        Board.addEntity(flame);
        if (check == 2) {
          break;
        }
      }
    }
    Board.removeEntity(this);
    Bomber.allowThroughBomb = true;
  }

  public Bomb(int x, int y) {
    super(x, y);
  }

  @Override
  public Image getImage() {
    return Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, (int) Board.frame, 120);
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x, y, 32, 32);
  }

  @Override
  public void update() {
    timer--;
    for (AnimatedEntity i : Board.getFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        explode();
        return;
      }
    }
    if (timer == 0) {
      explode();
    }
  }
}
