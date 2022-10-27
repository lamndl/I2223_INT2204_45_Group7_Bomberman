package mainClass;

import entity.Entity;
import entity.animated.Bomb;
import entity.animated.mob.Bomber;
import entity.animated.mob.Enemy;
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
import javafx.scene.paint.Color;
import level.FileLevelLoader;
import level.LevelLoader;


public class Board {
  private static Scene scene;
  private static Group root;
  private static Canvas canvas;
  private static GraphicsContext graphicsContext;
  private static int height;
  private static int width;
  private static List<Entity> tileList = new ArrayList<>();
  private static List<Entity> bombList = new ArrayList<>();
  private static List<Entity> enemyList = new ArrayList<>();
  private static Bomber bomber;

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
    graphicsContext.setFill(Color.GREEN);
    bomber = new Bomber(16, 16);
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        bomber.update(event);
      }
    });
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        render();
        update();
      }
    };
    timer.start();
  }

  public static void addEntity(Entity entity) {
    // Design pattern ???
    if (entity instanceof Tile) {
      tileList.add(entity);
    } else if (entity instanceof Bomb) {
      bombList.add(entity);
    } else if (entity instanceof Enemy) {
      enemyList.add(entity);
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

  public static void setHeight(int height) {
    Board.height = height;
  }

  public static void setWidth(int width) {
    Board.width = width;
  }

}
