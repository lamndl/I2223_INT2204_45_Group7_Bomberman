package mainClass;

import entity.Entity;
import entity.animated.Bomb;
import entity.animated.mob.Bomber;
import entity.animated.mob.Enemy;
import entity.tile.Tile;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class Board {
  private Canvas canvas;
  private GraphicsContext graphicsContext;
  private int height;
  private int width;
  private List<Tile> tileList = new ArrayList<>();
  private List<Bomb> bombList = new ArrayList<>();
  private List<Enemy> enemyList = new ArrayList<Enemy>();
  private Bomber bomber;

  public void addEntity(Entity entity) {
    //Design pattern ???
  }

  public void removeEntity(Entity entity) {
    //
  }
}
