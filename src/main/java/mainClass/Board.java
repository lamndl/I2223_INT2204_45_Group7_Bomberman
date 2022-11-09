package mainClass;

import entity.Entity;
import entity.animated.Bomb;
import entity.animated.Flame;
import entity.animated.mob.Balloom;
import entity.animated.mob.Bomber;
import entity.animated.mob.enemy.Enemy;
import entity.tile.powerup.PowerUp;
import entity.tile.Grass;
import entity.tile.Overlay;
import entity.tile.Portal;
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
import javafx.scene.control.Button;
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

  public static int getHeight() {
    return height;
  }


  public static int getWidth() {
    return width;
  }

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

  public static List<PowerUp> getPowerUpList() {
    return powerUpList;
  }

  public static Bomber getBomber() {
    return bomber;
  }

  private static List<Tile> tileList = new ArrayList<>();
  private static List<Bomb> bombList = new ArrayList<>();
  private static List<Flame> flameList = new ArrayList<>();
  private static List<Enemy> enemyList = new ArrayList<>();
  private static List<PowerUp> powerUpList = new ArrayList<>();
  private static Portal portal;
  public static List<Overlay> overlays = new ArrayList<>();

  private static Bomber bomber;

  public static long frame;
  public static Set<KeyCode> input = new HashSet<>();

  private static LevelLoader lvd;

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

    lvd = new FileLevelLoader(App.mapLevel);

    bomber = new Bomber(32, 32);

    if (lvd.getLevel() == 3) {
      lvd.createEntities(3);
    } else {
      lvd.createEntities();
    }
    canvas = new Canvas(width, height);
    canvas.setLayoutX(16);
    canvas.setLayoutY(30);
    // ve lai background tu y=476
    root.getChildren().add(canvas);
    graphicsContext = canvas.getGraphicsContext2D();
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
        if (frame % 15 == 0) {
          overlays.clear();
          if (bombList.isEmpty() && !enemyList.isEmpty()) {
            Enemy nearestEnemy = enemyList.get(0);
            for (Enemy e : enemyList) {
              if (e.calculateDistance(bomber) < nearestEnemy.calculateDistance(bomber)) {
                nearestEnemy = e;
              }
            }
            bomber.path = bomber.findPath(nearestEnemy);
          } else if (bombList.isEmpty() && enemyList.isEmpty()) {
            for (Tile t : tileList) {
              if (t instanceof Portal) {
                bomber.path = bomber.findPath(t);
                break;
              }
            }
          } else {
            bomber.path = bomber.findPath(new Grass(32, 32));
          }
        }
        if (frame % 2 == 0) {
          update();
          render();
        }
        if (frame % 25 == 0 && bomber.getMoving() == 1) {
          Sound.playInGameSound(3);
        }
      }
    };
    timer.start();

  }

  public static void addEntity(Entity entity) {
    // Design pattern ???
    if (entity instanceof PowerUp) {
      powerUpList.add((PowerUp) entity);
    } else if (entity instanceof Tile) {
      tileList.add((Tile) entity);
    } else if (entity instanceof Bomb) {
      bombList.add((Bomb) entity);
    } else if (entity instanceof Flame) {
      flameList.add((Flame) entity);
    } else if (entity instanceof Enemy) {
      enemyList.add((Enemy) entity);
    } else if (entity instanceof Overlay) {
      overlays.add((Overlay) entity);
    } else {
      bomber = (Bomber) entity;
    }
  }

  public static void removeEntity(Entity entity) {
    if (entity instanceof PowerUp) {
      powerUpList.remove(entity);
    } else if (entity instanceof Tile) {
      tileList.remove(entity);
    } else if (entity instanceof Bomb) {
      bombList.remove(entity);
    } else if (entity instanceof Flame) {
      flameList.remove(entity);
    } else if (entity instanceof Enemy) {
      enemyList.remove(entity);
    } else {
      bomber = new Bomber(-32, -32);
    }
  }

  public static void render() {
    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    powerUpList.forEach(i -> i.draw(graphicsContext));
    tileList.forEach(i -> i.draw(graphicsContext));
    enemyList.forEach(i -> i.draw(graphicsContext));
    bombList.forEach(i -> i.draw(graphicsContext));
    bomber.draw(graphicsContext);
    flameList.forEach(i -> i.draw(graphicsContext));
    overlays.forEach(i -> i.draw(graphicsContext));
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
    for (int i = 0; i < powerUpList.size(); i++) {
      powerUpList.get(i).update();
    }
  }

  public static void nextLevel() {
    loadLevel(lvd.getLevel() + 1);
  }

  private static void loadLevel(int level) {
    lvd.clearAll();
    lvd = new FileLevelLoader(level);

    // test random map
    if (level == 3) {
      lvd.createEntities(3);
    } else {
      lvd.createEntities();
    }
    overlays.clear();
    if (bombList.isEmpty() && !enemyList.isEmpty()) {
      Enemy nearestEnemy = enemyList.get(0);
      for (Enemy e : enemyList) {
        if (e.calculateDistance(bomber) < nearestEnemy.calculateDistance(bomber)) {
          nearestEnemy = e;
        }
      }
      bomber.path = bomber.findPath(nearestEnemy);
    } else if (bombList.isEmpty() && enemyList.isEmpty()) {
      for (Tile t : tileList) {
        if (t instanceof Portal) {
          bomber.path = bomber.findPath(t);
          break;
        }
      }
    } else {
      bomber.path = bomber.findPath(new Grass(32, 32));
    }
  }

  public static void setHeight(int height) {
    Board.height = height;
  }

  public static void setWidth(int width) {
    Board.width = width;
  }


  // demo
  public static void goEndGame() {
    Button b = new Button("END GAME");
    b.setMinSize(200, 200);
    b.setLayoutX(width / 2 - 100);
    b.setLayoutY(50);
    root.getChildren().add(b);
  }

  public static LevelLoader getLvd() {
    return lvd;
  }

  public static void setLvd(LevelLoader lvd) {
    Board.lvd = lvd;
  }
}
