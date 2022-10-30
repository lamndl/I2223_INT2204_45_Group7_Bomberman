package entity.animated.mob;

import static mainClass.App.KB;
import entity.animated.Bomb;
import entity.tile.Tile;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import mainClass.Board;
import sprite.Sprite;

public class Bomber extends Mob {

  // protected Keyboard input;
  private int maximumBombCount;
  private int currentBombCount;
  private int flameLength;
  private double speedMultiplier;

  public void placeBomb() {
    Board.addEntity(new Bomb((x + 13) / 32 * 32, (y + 15) / 32 * 32));
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
      }
    }
  }

  public void update() {
    velocityX = 0;
    velocityY = 0;
    moving = 0;
    calculateMove();
    move();

  }

  @Override
  public Image getImage() {
    return Sprite.player[(int) (direction * 3 + Board.frame * moving / 30)];
  }

  @Override
  protected void move() {
    y += velocityY;
    x += velocityX;
    for (Tile i : Board.tileList) {
      if (isCollidedWith(i)) {
        moving = 0;
        x -= velocityX;
        y -= velocityY;
        return;
      }
    }
  }


}
        