package entity.animated.mob;

import static mainClass.App.KB;

import entity.animated.Bomb;
import entity.tile.Tile;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import mainClass.Board;
import sprite.Sprite;

public class Bomber extends Mob {
  private int bombCount = 1;
  private int flameLength;
  private double speedMultiplier;

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
    for (KeyCode i : Board.input) {
      if (i == KB.getMoveUp()) {
        direction = 2;
        velocityY = -1;
        moving = 1;
      } else if (i == KB.getMoveDown()) {
        direction = 3;
        velocityY = 1;
        moving = 1;
      } else if (i == KB.getMoveLeft()) {
        direction = 0;
        velocityX = -1;
        moving = 1;
      } else if (i == KB.getMoveRight()) {
        direction = 1;
        velocityX = 1;
        moving = 1;
      } else if (i == KB.getBombPlacement()) {
        placeBomb();
      } else if (i == KB.getInGameMenu()) {
        // To do: tao in game menu
        // respawn
        x = 32;
        y = 32;
      }
    }
  }

  public void update() {
    checkHit();
    velocityX = 0;
    velocityY = 0;
    moving = 0;
    calculateMove();
    move();

  }

  @Override
  protected void checkHit() {
    super.checkHit();
    for (Enemy i :Board.getEnemyList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        die();
        return;
      }
    }
  }

  @Override
  public Image getImage() {
    return Sprite.player[(int) (direction * 3 + moving * (Board.frame / 20))];
  }

  @Override
  protected void move() {
    y += velocityY;
    x += velocityX;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
        x -= velocityX;
        y -= velocityY;
        return;
      }
    }
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 4, y + 4, 17, 24);
  }

  public void increaseBomb() {
    bombCount++;
  }
}
