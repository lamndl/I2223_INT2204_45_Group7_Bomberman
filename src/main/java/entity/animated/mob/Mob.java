package entity.animated.mob;

import entity.animated.AnimatedEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sprite.Sprite;

public abstract class Mob extends AnimatedEntity {
  protected int hp;
  protected int velocityX;
  protected int velocityY;

  protected int direction = -1;
  protected boolean alive = true;
  protected boolean moving = false;

  public void die() {
    //
  }

  protected Mob(int x, int y, Image image, int hp) {
    super(x, y, image);
    this.hp = hp;
  }

  @Override
  public void draw(GraphicsContext gc) {
    image = sprite.getFxImage();
    gc.drawImage(image, x, y);
  }

  protected Mob(int x, int y, Image image) {
    super(x, y, image);
  }

  protected Mob(int x, int y, Sprite sprite) {
    super(x, y, sprite);
  }

  // kiểm tra xem Mob có thể move được không
  protected abstract boolean canMove(double x, double y);

  // tính toán hướng đi
  protected abstract void calculateMove();

  protected abstract void move(double xa, double ya);
}
