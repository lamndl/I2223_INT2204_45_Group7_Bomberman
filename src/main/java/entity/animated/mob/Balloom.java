package entity.animated.mob;

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
    return Sprite.balloom[direction * 3 + (int) (moving * Board.frame / 20)];
  }


  @Override
  public void calculateMove() {}


  @Override
  protected void move() {
    moving = 1;
    x += velocityX;
    for (Tile i : Board.tileList) {
      if (isCollidedWith(i)) {
        x -= velocityX;
        velocityX = -velocityX;
        break;
      }
    }
    y += velocityY;
    for (Tile i : Board.tileList) {
      if (isCollidedWith(i)) {
        y -= velocityY;
        break;
      }
    }

  }

  public void update() {
    calculateMove();
    move();

  }
}
