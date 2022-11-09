package entity.animated.mob;

import entity.animated.mob.enemy.Enemy;
import entity.tile.Brick;
import java.util.List;
import ai.AStar;
import ai.Node;
import entity.Entity;
import entity.animated.AnimatedEntity;
import entity.animated.Bomb;
import entity.tile.Overlay;
import entity.tile.Tile;
import entity.tile.Wall;
import javafx.geometry.BoundingBox;
import mainClass.App;
import mainClass.Board;
import mainClass.Sound;

public abstract class Mob extends AnimatedEntity {

  protected int hp;
  protected int velocityX;
  protected int velocityY;

  protected int direction = 1;
  protected boolean alive = true;
  protected int moving = 0;
  public static boolean allowThroughBomb = true;
  protected int timer = 60;

  public void die() {
    timer--;
    if (timer == 0) {
      Board.removeEntity(this);
      if(this instanceof Enemy){
        App.currentPlayer.setLastestScore(App.currentPlayer.getLastestScore()+50);
        App.currentPlayer.setEnemiesKilled(App.currentPlayer.getEnemiesKilled()+1);
      }

    }
  }

  protected void checkHit() {
    for (AnimatedEntity i : Board.getFlameList()) {
      if (getBoundingBox().intersects(i.getBoundingBox())) {
        alive = false;
        if (this instanceof Enemy) {
          Sound.playInGameSound(0);
        } else {
          Sound.playInGameSound(2);
        }
        return;
      }
    }
  }

  public int getMoving() {
    return moving;
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 4, y + 4, 24, 24);
  }

  protected Mob(int x, int y) {
    super(x, y);
  }

  protected abstract void move();

  public abstract void calculateMove();

  public void setMoving(int moving) {
    this.moving = moving;
  }

  /** Hiện giờ đang chỉ để bomber dùng. */
  public List<Node> findPath(Entity entity) {
    AStar aStar =
        new AStar(Board.getHeight() / 32, Board.getWidth() / 32, getNode(), entity.getNode());
    for (Tile t : Board.getTileList()) {
      if (t instanceof Wall) {
        aStar.setBlock(t.getY() / 32, t.getX() / 32);
      }
      if (t instanceof Brick) {
        aStar.setBrick(t.getY() / 32, t.getX() / 32);
      }
    }
    for (Enemy e : Board.getEnemyList()) {
      aStar.setBrick((e.getY() + 13) / 32, (e.getX() + 15) / 32);
    }
    List<Node> path = aStar.findPath();
    for (Node i : path) {
      Board.addEntity(new Overlay(i.getCol() * 32, i.getRow() * 32));
      // System.out.println(i);
    }
    return path;
  }

  public int calculateDistance(Entity entity) {
    return Math.abs(x - entity.getX()) + Math.abs(y - entity.getY());
  }
}
