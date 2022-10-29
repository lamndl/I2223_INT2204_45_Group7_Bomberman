package mainClass;

import entity.Entity;
import entity.animated.Bomb;
import entity.animated.mob.Bomber;
import entity.animated.mob.Enemy;
import entity.animated.mob.Mob;
import entity.tile.Grass;
import entity.tile.Tile;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import level.FileLevelLoader;
import level.LevelLoader;


public class Board {
  private static Scene scene;
  private static Group root;
  private static Canvas canvas;
  private static GraphicsContext graphicsContext;
  private static int height;
  private static int width;
  private static List<Tile> tileList = new ArrayList<>();
  private static List<Bomb> bombList = new ArrayList<>();
  private static List<Enemy> enemyList = new ArrayList<>();
  private static Bomber bomber;

  public static long frame;

  public static Scene getScene() {
    return scene;
  }

  public static void init() {
    root = new Group();
    scene = new Scene(root);
    LevelLoader lvd = new FileLevelLoader(1);
    lvd.createEntities();
    canvas = new Canvas(width, height);
    root.getChildren().add(canvas);
    graphicsContext = canvas.getGraphicsContext2D();
    bomber = new Bomber(32, 32);
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        bomber.update(event);
      }
    });
    scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        bomber.setMoving(false);
      }
    });
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        frame++;
        frame %= 120;
        render();
      }
    };
    timer.start();
  }

  public static void addEntity(Entity entity) {
    // Design pattern ???
    if (entity instanceof Tile) {
      tileList.add((Tile) entity);
    } else if (entity instanceof Bomb) {
      bombList.add((Bomb) entity);
    } else if (entity instanceof Enemy) {
      enemyList.add((Enemy) entity);
    } else {
      bomber = (Bomber) entity;
    }
  }

  public static void removeEntity(Entity entity) {
    //
  }

  public static void render() {
    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    tileList.forEach(i -> i.draw(graphicsContext));
    bombList.forEach(i -> i.draw(graphicsContext));
    enemyList.forEach(i -> i.draw(graphicsContext));
    bomber.draw(graphicsContext);
  }

  public static void update() {
    //
  }


  public static Entity getEntity(double x, double y, Mob m) {
    // TODO: Check xem vị trí x, y có entity nào
    Entity res = null;

    // res = getBombAt(x, y);
    // if( res != null) return res;

    res = getTileEntityAt((int) x, (int) y);
    if (res != null)
      return res;

    return null;
  }

  /**
   * lấy tile entity tại vị trí (x, y)
   */
  public static Entity getTileEntityAt(int x, int y) { // x, y tọa độ pixel
    for (int i = 0; i < tileList.size(); i++) {
      int tileX = tileList.get(i).getX();
      int tileY = tileList.get(i).getY();


      for (int ii = 0; ii < 32; ii++)
        for (int jj = 0; jj < 32; jj++) {
          if (jj < 25 && x == tileX + ii && y == tileY + jj) {
            return tileList.get(i);
          } else if (jj >= 25 && x == tileX + ii && y == tileY + jj)
            return new Grass(x, y);

        }
    }
    return null;
  }

  public static void setHeight(int height) {
    Board.height = height;
  }

  public static void setWidth(int width) {
    Board.width = width;
  }


}
