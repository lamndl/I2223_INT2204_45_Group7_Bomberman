package entity.animated.mob;

import entity.tile.Brick;
import java.util.ArrayList;
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

  public Node getNode() {
    return new Node((y + 10) / 32, (x + 10) / 32);
  }

  public List<Node> findPath(Entity entity) {
    AStar aStar = new AStar(Board.getHeight() / 32, Board.getWidth() / 32, getNode(),
        ((Mob) entity).getNode());
    for (Tile t : Board.getTileList()) {
      if (t instanceof Wall || t instanceof Brick) {
        aStar.setBlock(t.getY() / 32, t.getX() / 32);
      }
    }
    for (Bomb t : Board.getBombList()) {
      aStar.setBlock(t.getY() / 32, t.getX() / 32);
    }
    List<Node> path = aStar.findPath();
    for (Node i : path) {
      Board.addEntity(new Overlay(i.getCol() * 32, i.getRow() * 32));
      // System.out.println(i);
    }
    return path;
  }
}
