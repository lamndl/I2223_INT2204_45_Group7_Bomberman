package mainClass;

import entity.Entity;
import entity.animated.Bomb;
import entity.animated.mob.Bomber;
import entity.animated.mob.Enemy;
import entity.tile.Tile;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class Board {
  public static Group root;
  private static Canvas canvas;
  private static GraphicsContext graphicsContext;
  private static int height;
  private static int width;
  private static List<Tile> tileList = new ArrayList<>();
  private static List<Bomb> bombList = new ArrayList<>();
  private static List<Enemy> enemyList = new ArrayList<Enemy>();
  private static Bomber bomber;

  public static void init() {
    root = new Group();
    canvas = new Canvas(480, 480);
    root.getChildren().add(canvas);
    graphicsContext = canvas.getGraphicsContext2D();
    bomber = new Bomber();
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
  }

  public static void removeEntity(Entity entity) {
    //
  }

  public static void render() {
    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    bomber.draw(graphicsContext);
  }

  public static void update() {
    //
  }
}
