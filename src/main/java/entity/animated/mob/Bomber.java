package entity.animated.mob;

import entity.Entity;
import entity.animated.Bomb;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import mainClass.Board;
//import mainClass.Keyboard;
import static mainClass.App.KB;
import sprite.Sprite;

public class Bomber extends Mob {

  //protected Keyboard input;
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

  public void update() {
    velocityX = 0;
    velocityY = 0;
    moving = 0;
    calculalteMove();
    move(velocityX, velocityY);

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

  public void calculalteMove() {
    for (KeyCode i : Board.input) {
      if(i==KB.getMoveUp()){
        direction = 2;
        velocityY = -1;
        moving = 1;
      }
      if(i==KB.getMoveDown()){
        direction = 3;
        velocityY = 1;
        moving = 1;
      }
      if(i==KB.getMoveLeft()){
        direction = 0;
        velocityX = -1;
        moving = 1;
      }
      if(i==KB.getMoveRight()){
        direction = 1;
        velocityX = 1;
        moving = 1;
      }
      if(i==KB.getBombPlacement()){
        placeBomb();
      }
      if(i==KB.getInGameMenu()){
        //To do: tao in game menu
      }

    }
  }

  @Override
  protected void move(double xa, double ya) {
    if (canMove(0, ya)) {
      y += ya;
    }
    if (canMove(xa, 0)) {
      x += xa;
    }
  }

  @Override
  public Image getImage() {
    return Sprite.player[(int) (direction * 3 + Board.frame * moving / 30)];
  }

}
