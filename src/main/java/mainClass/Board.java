package mainClass;

import static mainClass.App.currentPlayer;

import entity.Entity;
import entity.animated.Bomb;
import entity.animated.Flame;
import entity.animated.mob.Bomber;
import entity.animated.mob.enemy.Enemy;
import entity.tile.Grass;
import entity.tile.Overlay;
import entity.tile.Portal;
import entity.tile.Tile;
import entity.tile.powerup.PowerUp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
  private static int playerNumber = 1; // by default

  public static long unresetFrame = 0;
  public static boolean gameOver = false;

  public static AnimationTimer timer;

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
    // ap init
    ap = new AnchorPane();
    // init of ingameInformation
    ingameName = new Text();
    ingameName.setWrappingWidth(500.0);
    ingameName.setLayoutX(39.0);
    ingameName.setLayoutY(503.0);
    ingameName.setText("Name: " + currentPlayer.getUserName());
    ingameHealth = new Text("Health: "+bomber.getHp());
    // change if we customize the characters
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
    ingameName.setFont(new Font("System", 20));
    ingameTime.setFont(new Font("System", 20));
    ingameHealth.setFont(new Font("System", 20));
    ingameScore.setFont(new Font("System", 20));

    root.getChildren().add(ingameName);
    root.getChildren().add(ingameHealth);
    root.getChildren().add(ingameScore);
    root.getChildren().add(ingameTime);

    /**
     * timing init.
     */
    unresetFrame = 0;
    timerInit();

  }

  public static void timerInit() {
    timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        frame++;
        unresetFrame++;
        frame %= 60;
        if (App.toggleAI && frame % 6 == 0) {
          findPath();
        }
        if (frame % 2 == 0) {
          update();
          render();
        }
        if (frame % 25 == 0 && bomber.getMoving() == 1) {
          Sound.playInGameSound(3);
        }
        // for showing
        if (unresetFrame == Long.MAX_VALUE) {
          unresetFrame = 0;
        }
        if (!gameOver) {
          if (unresetFrame % 180 == 0) { //increase point per 3 seconds
            currentPlayer.setLastestScore(currentPlayer.getLastestScore() + 1);
            ingameScore.setText("Score: " + currentPlayer.getLastestScore());
            ingameHealth.setText("Health: "+bomber.getHp());
          }

          if (unresetFrame % 60 == 0) { // increase second played
            currentPlayer.setSecondsPlayed(currentPlayer.getSecondsPlayed() + 1);
            currentPlayer.setDummyAccount(false);// not dummy account any more.
            long tempSeconds = unresetFrame / 60;
            long tempMinutes = tempSeconds / 60;
            tempSeconds %= 60;
            String tempTime = "Time: " +
                ((tempMinutes < 10) ? ("0" + tempMinutes) : Long.toString(tempMinutes)) + ":" + (
                (tempSeconds < 10) ? ("0" + tempSeconds) : Long.toString(tempSeconds));
            ingameTime.setText(tempTime);
          }
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

  public static void removeAllEntity() {
    powerUpList.removeAll(powerUpList);
    tileList.removeAll(tileList);
    bombList.removeAll(bombList);
    flameList.removeAll(flameList);
    enemyList.removeAll(enemyList);
    overlays.removeAll(overlays);
    if (!gameOver) {
      bomber.die();
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
    //quick fix overlay, there may be a better way
    if(!App.toggleAI){
      overlays.removeAll(overlays);
    }
  }

  public static void nextLevel() {
    if (App.coe) {
      loadLevel(lvd.getLevel() + 1);
    } else {
      //get back
      if (lvd.getLevel() == 5) {
        loadLevel(1);
      } else {
        loadLevel(lvd.getLevel() + 1);

      }
    }

  }

  private static void loadLevel(int level) {
    /**
     * when reach the max level
     */
    if (level > 5) {
      goEndGame();
      return;
    }
    lvd.clearAll();
    removeAllEntity();
    clearInput();
    lvd = new FileLevelLoader(level);
    // test random map
    if (level == 3) {
      lvd.createEntities(3);
    } else {
      lvd.createEntities();
    }
    findPath();
  }

  public static void setHeight(int height) {
    Board.height = height;
  }

  public static void setWidth(int width) {
    Board.width = width;
  }

  public static void goEndGame() {

    timer.stop();
    ap.setLayoutX(219.0);
    ap.setLayoutY(259.0);
    ap.setPrefHeight(250.0);
    ap.setPrefWidth(586.0);
    ap.setMinSize(290.0, 250.0);
    BackgroundFill bgfill = new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY);
    Background bg = new Background(bgfill);
    ap.setBackground(bg);
    // Text
    Text statusText = new Text("Game over. Your score: " + ingameScore.getText().substring(7));
    Font f = new Font("System", 24);
    statusText.setFont(f);
    statusText.setWrappingWidth(296.607);
    statusText.setLayoutX(145.0);
    statusText.setLayoutY(54.0);
    statusText.setTextAlignment(TextAlignment.valueOf("CENTER"));
    Button b2 = new Button("Back");
    b2.setPadding(new Insets(10, 10, 10, 10));
    b2.setPrefWidth(100.0);
    b2.setPrefHeight(38.0);
    b2.setLayoutX(243.0);
    b2.setLayoutY(164.0);
    b2.setOnAction(e -> {
      removeInRoot(b2);
      removeInRoot(statusText);
      removeInRoot(ap);
      removeInRoot(canvas);
      removeAllEntity();
      currentPlayer.setLastestScore(0);
      statusText.setText("");
      clearInput();
      App.goBackMainMenu();
    });
    gameOver = true;
    ap.getChildren().add(statusText);
    ap.getChildren().add(b2);
    root.getChildren().add(ap);
    App.secondTime = true;
  }

  public static void goIngameMenu() {
    timer.stop();
    ap.setLayoutX(219.0);
    ap.setLayoutY(259.0);
    ap.setPrefHeight(250.0);
    ap.setPrefWidth(586.0);
    ap.setMinSize(290.0, 250.0);
    BackgroundFill bgfill = new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY);
    Background bg = new Background(bgfill);
    ap.setBackground(bg);
    // Text
    Text statusText = new Text("Game paused");
    Font f = new Font("System", 24);
    statusText.setFont(f);
    statusText.setWrappingWidth(296.607);
    statusText.setLayoutX(145.0);
    statusText.setLayoutY(54.0);
    statusText.setTextAlignment(TextAlignment.valueOf("CENTER"));
    Button b3 = new Button("Back");
    Button b2 = new Button("Continue");
    Button b1 = new Button("Replay");

    b2.setPadding(new Insets(10, 10, 10, 10));
    b2.setPrefWidth(100.0);
    b2.setPrefHeight(38.0);
    b2.setLayoutX(243.0);
    b2.setLayoutY(164.0);
    b2.setOnAction(e -> {
      removeInRoot(b2);
      removeInRoot(b1);
      removeInRoot(b3);
      removeInRoot(statusText);
      removeInRoot(ap);
      timer.start();
      statusText.setText("");
      clearInput();
    });

    b3.setPadding(new Insets(10, 10, 10, 10));
    b3.setPrefWidth(100.0);
    b3.setPrefHeight(38.0);
    b3.setLayoutX(57.0);
    b3.setLayoutY(164.0);
    b3.setOnAction(e -> {
      removeInRoot(b2);
      removeInRoot(b1);
      removeInRoot(b3);
      removeInRoot(statusText);
      removeInRoot(ap);
      removeInRoot(canvas);
      removeAllEntity();
      gameOver = true;
      currentPlayer.setLastestScore(0);
      statusText.setText("");
      clearInput();
      App.goBackMainMenu();
    });
    b1.setPadding(new Insets(10, 10, 10, 10));
    b1.setPrefWidth(100.0);
    b1.setPrefHeight(38.0);
    b1.setLayoutX(422.0);
    b1.setLayoutY(164.0);
    b1.setOnAction(e -> {
      timer.start();
      currentPlayer.setLastestScore(0);
      statusText.setText("");
      removeInRoot(b2);
      removeInRoot(b1);
      removeInRoot(b3);
      removeInRoot(statusText);
      removeInRoot(ap);
      loadLevel(lvd.getLevel());
    });
    ap.getChildren().add(statusText);
    ap.getChildren().add(b2);
    ap.getChildren().add(b3);
    ap.getChildren().add(b1);
    root.getChildren().add(ap);
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

  public static void clearInput() {
    input = new HashSet<>();
  }

  public static void removeInRoot(Node n) {
    if (root.getChildren().contains(n)) {
      root.getChildren().remove(n);
    }
  }

  public static Group getRoot() {
    return root;
  }

  public static void setRoot(Group g) {
    root = g;
  }

  public static void findPath() {
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

}
