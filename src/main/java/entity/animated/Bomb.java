package entity.animated;

import entity.animated.mob.Bomber;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Bomb extends AnimatedEntity {
  private int timer = 90;

  public void explode() {
    Board.explode(x,y);
    Board.addEntity(new Flame(0, x, y));
    Board.addEntity(new Flame(2, x + 32, y));
    Board.addEntity(new Flame(1, x - 32, y));
    Board.addEntity(new Flame(4, x, y + 32));
    Board.addEntity(new Flame(3, x, y - 32));
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
    if (timer == 0) {
      Board.getBomber().increaseBomb();
      explode();
    }
  }
}
