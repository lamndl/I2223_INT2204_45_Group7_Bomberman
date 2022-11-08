package entity.animated.mob;

import static mainClass.App.KB;
import java.util.Iterator;
import entity.animated.Bomb;
import entity.tile.Portal;
import entity.tile.Tile;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import mainClass.Board;
import mainClass.Sound;
import sprite.Sprite;

public class Bomber extends Mob {

  private int bombCount = 1;
  private int flameLength = 1;
  private double speedMultiplier = 1;
  protected int timer = 120;

  public void placeBomb() {
    if (bombCount > 0) {
      bombCount--;
      Board.addEntity(new Bomb((x + 13) / 32 * 32, (y + 15) / 32 * 32));
    }
  }

  public Bomber(int x, int y) {
    super(x, y);
  }

  public void calculateMove() {
    Iterator<KeyCode> iterator = Board.input.iterator();
    while (iterator.hasNext()) {
      KeyCode i = iterator.next();
      if (i == KB.getMoveUp()) {
        direction = 2;
        velocityY = -2;
        moving = 1;
        Sound.playInGameSound(3);
      } else if (i == KB.getMoveDown()) {
        direction = 3;
        velocityY = 2;
        moving = 1;
        Sound.playInGameSound(3);
      } else if (i == KB.getMoveLeft()) {
        direction = 0;
        velocityX = -2;
        moving = 1;
        Sound.playInGameSound(3);
      } else if (i == KB.getMoveRight()) {
        direction = 1;
        velocityX = 2;
        moving = 1;
        Sound.playInGameSound(3);
      } else if (i == KB.getBombPlacement()) {
        placeBomb();
        Sound.playInGameSound(4);
        iterator.remove();

      } else if (i == KB.getInGameMenu()) {
        // To do: tao in game menu
        // respawn
        x = 32;
        y = 32;
      }
    }


    if (allowThroughBomb) {
      boolean fl = false;
      for (Bomb i : Board.getBombList()) {
        if (isCollidedWith(i)) {
          fl = true;
          break;
        }
      }
      if (!fl && !Board.getBombList().isEmpty()) {
        allowThroughBomb = false;
      }
    }
  }

  public void update() {
    if (alive) {
      checkHit();
      velocityX = 0;
      velocityY = 0;
      moving = 0;
      calculateMove();
      move();
    } else {
      die();
    }

  }

  @Override
  protected void checkHit() {
    super.checkHit();
    for (Enemy i : Board.getEnemyList()) {
      if (isCollidedWith(i)) {
        alive = false;
        return;
      }
    }
  }

  @Override
  public void die() {
    timer--;
    if (timer == 0) {
      Sound.playInGameSound(2);
      Board.removeEntity(this);

      // go to scene end game and replay
      // Board.goEndGame();
    }

  }

  @Override
  public Image getImage() {
    if (alive) {
      return Sprite.player[(int) (direction * 3 + moving * (Board.frame / 20))];
    } else {
      if (timer > 80) {
        return Sprite.player_dead1;
      }
      if (timer > 40) {
        return Sprite.player_dead2;
      }
      return Sprite.player_dead3;
    }
  }

  @Override
  protected void move() {
    y += velocityY;
    x += velocityX;

    checkWin();

    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
        x -= velocityX;
        y -= velocityY;
        return;
      }
    }

    if (!allowThroughBomb) {
      for (Bomb i : Board.getBombList()) {
        if (isCollidedWith(i)) {
          x -= velocityX;
          y -= velocityY;
          return;
        }
      }
    }

  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 4, y + 5, 17, 24);
  }

  public void increaseBomb() {
    bombCount++;
  }

  public void increaseFlamLength() {
    flameLength++;
  }

  public void increaseSpeed() {
    speedMultiplier += 0.2;
  }

  public void checkWin() {
    if (!Board.getEnemyList().isEmpty()) {
      return;
    }
    for (Tile i : Board.getTileList()) {
      if ((i instanceof Portal) && this.isCollidedWith(i)) {
        // to next level
        Board.goEndGame();
        return;
      }
    }
  }

}
