package entity.animated.mob;

import entity.Entity;
import entity.animated.Bomb;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import mainClass.Board;
import mainClass.Keyboard;
import sprite.Sprite;

public class Bomber extends Mob {

  protected Keyboard input;
  private int maximumBombCount;
  private int currentBombCount;
  private int flameLength;
  private double speedMultiplier;

  public void placeBomb() {
    Board.addEntity(new Bomb(x / 32 * 32, y / 32 * 32));
  }

  public Bomber(int x, int y) {
    super(x, y);
  }

  public void update(KeyEvent keyEvent) {
    calculalteMove(keyEvent);
  }

  @Override
  public boolean isCollidedWith(Entity e) {
    // TODO: xử lý va chạm với Flame
    // TODO: xử lý va chạm với Enemy
    // if (e instanceof Flame)
    // {
    // kill();
    // }
    //
    // if (e instanceof Enemy)
    // {
    // kill();
    // }
    return true;
  }

  @Override
  protected boolean canMove(double _x, double _y) {
    // TODO: kiểm tra có đối tượng tại vị trí chuẩn bị di chuyển đến và có thể di chuyển tới đó hay
    // không
    for (int c = 0; c < 4; c++) {
      double xt = (x + _x) + c % 2 * 23; // *23: thu hẹp chiều rộng
      double yt = (y + _y) + c / 2 * 30; // c%2: check 4 góc bomber
      Entity a = Board.getEntity(xt, yt, this);
      if (!a.isCollidedWith(this)) {
        return false;
      }

      yt = (y + _y) + 15; // check ở giữa bomber
      a = Board.getEntity(xt, yt, this);
      if (!a.isCollidedWith(this)) {
        return false;
      }
    }
    return true;
  }

  /**
   * nhận key event, tính toán hướng đi .
   */

  public void calculalteMove(KeyEvent keyEvent) {
    int x = 0;
    int y = 0;
    switch (keyEvent.getCode()) {
      case UP:
        y -= 2;
        break;
      case DOWN:
        y += 2;
        break;
      case LEFT:
        x -= 2;
        break;
      case RIGHT:
        x += 2;
        break;
      case SPACE:
        placeBomb();
        break;
      default:
        break;
    }

    if (x != 0 || y != 0) {
      move(x, y);
      moving = 1;
    } else {
      moving = 0;
    }
  }

  @Override
  protected void move(double xa, double ya) {
    // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không và
    // thực hiện thay đổi tọa độ x, y
    // TODO: cập nhật giá trị _direction sau khi di chuyển
    if (xa < 0) {
      direction = 0;
    }
    if (xa > 0) {
      direction = 1;
    }
    if (ya < 0) {
      direction = 2;
    }
    if (ya > 0) {
      direction = 3;
    }

    if (canMove(0, ya)) {
      y += ya;
    }
    if (canMove(xa, 0)) {
      x += xa;
    }
  }

  @Override
  public Image getImage() {
    return Sprite.player[(int) (direction * 3 + Board.frame * moving / 40)];
  }

}
