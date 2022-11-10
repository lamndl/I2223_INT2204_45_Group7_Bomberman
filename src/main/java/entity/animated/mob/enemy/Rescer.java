package entity.animated.mob.enemy;

import ai.AStar;
import ai.Node;
import entity.Entity;
import entity.animated.Bomb;
import entity.animated.mob.Bomber;
import entity.tile.Brick;
import entity.tile.Overlay;
import entity.tile.Tile;
import entity.tile.Wall;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Rescer extends Enemy {

  private boolean follow = false;
  public List<Node> path = new ArrayList<>();

  public Rescer(int x, int y) {
    super(x, y);
    velocityX = 1;
  }

  @Override
  public Image getImage() {
    if (alive) {
      return Sprite.rescer[direction % 2 * 3 + (int) (moving * Board.frame / 20)];
    } else {
      return Sprite.rescer_dead;
    }
  }

  @Override
  protected void move() {
    x += velocityX;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
        x -= velocityX;
        velocityX = -velocityX;
        break;
      }
    }

    y += velocityY;
    for (Tile i : Board.getTileList()) {
      if (isCollidedWith(i)) {
        y -= velocityY;
        velocityY = -velocityY;
        break;
      }
    }

    for (Bomb j : Board.getBombList()) {
      if (isCollidedWith(j)) {
        x -= velocityX;
        y -= velocityY;
        velocityX = -velocityX;
        velocityY = -velocityY;
        break;
      }
    }
  }

  @Override
  public void calculateMove() {
    Bomber bomber = Board.getBomber();
    if (bomber.isAlive()) {
      if (calculateDistance(bomber) < 300) {
        follow = true;
        path = findPath(bomber);
      } else {
        follow = false;
        path = null;
      }
    }
    if (follow == true && path != null && !path.isEmpty()) {
      Node nearestNode = path.get(1);
      int xx = x - nearestNode.getCol() * 32;
      int yy = y - nearestNode.getRow() * 32;
      if (xx < 0) {
        direction = 1;
        velocityX = 2;
        moving = 1;
      } else if (xx > 0) {
        direction = 0;
        velocityX = -2;
        moving = 1;
      }
      if (yy > 0) {
        direction = 2;
        velocityY = -2;
        moving = 1;
      } else if (yy < 0) {
        direction = 3;
        velocityY = 2;
        moving = 1;
      }
    }

    move();
  }



  public List<Node> findPath(Entity entity) {
    AStar aStar =
        new AStar(Board.getHeight() / 32, Board.getWidth() / 32, getNode(), entity.getNode());
    for (Tile t : Board.getTileList()) {
      if (t instanceof Wall || t instanceof Brick) {
        aStar.setBlock(t.getY() / 32, t.getX() / 32);
      }
    }
    for (Bomb t : Board.getBombList()) {
      aStar.setBlock(t.getY() / 32, t.getX() / 32);
    }
    List<Node> path = aStar.findPath();
    return path;
  }
}
