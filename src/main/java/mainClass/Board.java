package mainClass;

import static mainClass.App.currentPlayer;

import entity.Entity;
import entity.animated.Bomb;
import entity.animated.Flame;
import entity.animated.mob.enemy.Balloom;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
  /**
   * ingame information
   */
  private static Text ingameName;
  private static Text ingameScore;
  private static Text ingameHealth;
  private static Text ingameTime;
  private static AnchorPane ap = new AnchorPane();

  private static Bomber bomber;

  public static long frame;
  public static Set<KeyCode> input = new HashSet<>();

  private static LevelLoader lvd;
  private static int playerNumber = 1; //by default

  public static long unresetFrame=0;

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
    /**
     * ap and ingame text init
     */
    //ap init
    ap = new AnchorPane();
    //init of ingameInformation
    ingameName = new Text();
    ingameName.setWrappingWidth(500.0);
    ingameName.setLayoutX(39.0);
    ingameName.setLayoutY(503.0);
    ingameName.setText("Name: " + currentPlayer.getUserName());
    ingameHealth = new Text("Health: 1");
    //change if we customize the characters
    ingameHealth.setWrappingWidth(500.0);
    ingameHealth.setLayoutX(39.0);
    ingameHealth.setLayoutY(531.0);
    ingameScore = new Text("Score: 0");
    ingameScore.setWrappingWidth(500.0);
    ingameScore.setLayoutX(39.0);
    ingameScore.setLayoutY(559.0);
    ingameTime = new Text("Time: 00:00");
    ingameTime.setWrappingWidth(500.0);
    ingameTime.setLayoutX(39.0);
    ingameTime.setLayoutY(587.0);
    ingameName.setFont(new Font("System",20));
    ingameTime.setFont(new Font("System",20));
    ingameHealth.setFont(new Font("System",20));
    ingameScore.setFont(new Font("System",20));

    root.getChildren().add(ingameName);
    root.getChildren().add(ingameHealth);
    root.getChildren().add(ingameScore);
    root.getChildren().add(ingameTime);

    /**
     * timing init.
     */
    unresetFrame=0;
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        frame++;
        unresetFrame++;
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
        //for showing
        if (unresetFrame == Long.MAX_VALUE) {
          unresetFrame = 0;
        }
        if (unresetFrame % 180 == 0) { //increase point per 3 seconds
          currentPlayer.setLastestScore(currentPlayer.getLastestScore() + 1);
          ingameScore.setText("Score: "+currentPlayer.getLastestScore());
        }

        if (unresetFrame % 60 == 0) { //increase second played
          currentPlayer.setSecondsPlayed(currentPlayer.getSecondsPlayed() + 1);
          currentPlayer.setDummyAccount(false);//not dummy account any more.
          long tempSeconds = unresetFrame / 60;
          long tempMinutes = tempSeconds / 60;
          tempSeconds%=60;
          String tempTime = "Time: "+
              ((tempMinutes < 10) ? ("0" + tempMinutes) : Long.toString(tempMinutes)) + ":" + (
              (tempSeconds < 10) ? ("0" + tempSeconds) : Long.toString(tempSeconds));
          ingameTime.setText(tempTime);
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

  public static int getPlayerNumber() {
    return playerNumber;
  }

  public static void setPlayerNumber(int playerNumber) {
    Board.playerNumber = playerNumber;
  }
}
