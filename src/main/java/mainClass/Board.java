package mainClass;

import entity.Entity;
import entity.animated.Bomb;
import entity.animated.Flame;
import entity.animated.mob.Bomber;
import entity.animated.mob.Enemy;
import entity.animated.mob.Mob;
import entity.tile.Grass;
import entity.tile.Tile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
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
  public static List<Tile> getTileList() {
    return tileList;
  }


  public static List<Bomb> getBombList() {
    return bombList;
  }


  public static List<Flame> getFlameList() {
    return flameList;
  }


  public static List<Enemy> getEnemyList() {
    return enemyList;
  }


  public static Bomber getBomber() {
    return bomber;
  }

  private static List<Tile> tileList = new ArrayList<>();
  private static List<Bomb> bombList = new ArrayList<>();
  private static List<Flame> flameList = new ArrayList<>();
  private static List<Enemy> enemyList = new ArrayList<>();
  private static Bomber bomber;

  public static long frame;
  public static Set<KeyCode> input = new HashSet<>();

  public static Scene getScene() {
    return scene;
  }


  public static void init() {
    root = new Group();

    FXMLLoader fx = new FXMLLoader(App.class.getResource("/scenes/board.fxml"));
    try {
      root.getChildren().add(fx.load());
    } catch (IOException i) {

    }

    scene = new Scene(root);
    LevelLoader lvd = new FileLevelLoader(1);
    lvd.createEntities();
    canvas = new Canvas(width, height);
    canvas.setLayoutX(16);
    canvas.setLayoutY(30);
    // ve lai background tu y=476
    root.getChildren().add(canvas);
    graphicsContext = canvas.getGraphicsContext2D();
    bomber = new Bomber(32, 32);
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        input.add(event.getCode());
      }
    });
    scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        input.remove(keyEvent.getCode());
      }
    });
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        frame++;
        frame %= 60;
        if (frame % 2 == 0) {
          update();
          render();
        }
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
    } else if (entity instanceof Flame) {
      flameList.add((Flame) entity);
    } else if (entity instanceof Enemy) {
      enemyList.add((Enemy) entity);
    } else {
      bomber = (Bomber) entity;
    }
  }

  public static void removeEntity(Entity entity) {
    if (entity instanceof Tile) {
      tileList.removeIf(i -> i.equals(entity));
    } else if (entity instanceof Bomb) {
      bombList.removeIf(i -> i.equals(entity));
    } else if (entity instanceof Flame) {
      flameList.removeIf(i -> i.equals(entity));
    } else if (entity instanceof Enemy) {
      enemyList.removeIf(i -> i.equals(entity));
    } else {
      bomber = new Bomber(-32, -32);
    }
  }

  public static void render() {
    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    tileList.forEach(i -> i.draw(graphicsContext));
    enemyList.forEach(i -> i.draw(graphicsContext));
    bombList.forEach(i -> i.draw(graphicsContext));
    bomber.draw(graphicsContext);
    flameList.forEach(i -> i.draw(graphicsContext));

  }

  public static void update() {
    bomber.update();
    for (int i = 0; i < enemyList.size(); i++) {
      enemyList.get(i).update();
    }
    for (int i = 0; i < bombList.size(); i++) {
      bombList.get(i).update();
    }
    for (int i = 0; i < flameList.size(); i++) {
      flameList.get(i).update();
    }
    for (int i = 0; i < tileList.size(); i++) {
      tileList.get(i).update();
    }
  }


  public static Entity getEntity(double x, double y, Mob m) {
    // TODO: Check xem vị trí x, y có entity nào
    Entity res = null;

    // res = getBombAt(x, y);
    // if( res != null) return res;

    res = getTileEntityAt((int) x, (int) y);
    if (res != null) {
      return res;
    }

    return null;
  }

  /**
   * lấy tile entity tại vị trí (x, y)
   */
  public static Entity getTileEntityAt(int x, int y) { // x, y tọa độ pixel
    for (int i = 0; i < tileList.size(); i++) {
      int tileX = tileList.get(i).getX();
      int tileY = tileList.get(i).getY();

      for (int ii = 0; ii < 32; ii++) {
        for (int jj = 0; jj < 32; jj++) {
          if (jj < 25 && x == tileX + ii && y == tileY + jj) {
            return tileList.get(i);
          } else if (jj >= 25 && x == tileX + ii && y == tileY + jj) {
            return new Grass(x, y);
          }
        }
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
