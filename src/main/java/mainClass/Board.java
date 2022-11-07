package mainClass;

import static mainClass.App.currentPlayer;

import entity.Entity;
import entity.animated.Bomb;
import entity.animated.Flame;
import entity.animated.mob.Balloom;
import entity.animated.mob.Bomber;
import entity.animated.mob.Enemy;
import entity.animated.mob.Mob;
import entity.animated.mob.Oneal;
import entity.tile.Grass;
import entity.tile.PowerUpBomb;
import entity.tile.PowerUpFlame;
import entity.tile.PowerUpSpeed;
import entity.tile.Tile;

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
  private static int playerNumber = 1; // by default

  /**
   * setup the level of game. MUST BE SET BEFORE LOAD
   */
  private static int boardLevel = 1;

  private static List<Tile> tileList = new ArrayList<>();
  private static List<Bomb> bombList = new ArrayList<>();
  private static List<Flame> flameList = new ArrayList<>();
  private static List<Enemy> enemyList = new ArrayList<>();
  private static List<PowerUpFlame> powerUpFlameList = new ArrayList<>();
  private static List<PowerUpSpeed> powerUpSpeedList = new ArrayList<>();
  private static List<PowerUpBomb> powerUpBombList = new ArrayList<>();
  private static Bomber bomber;
  private static long unresetFrame = 0;
  public static long frame;
  public static Set<KeyCode> input = new HashSet<>();

  public static LevelLoader lvd;
  private static AnchorPane ap = new AnchorPane();
  private static AnimationTimer timer;

  /**
   * ingame information
   */
  private static Text ingameName;
  private static Text ingameScore;
  private static Text ingameHealth;
  private static Text ingameTime;
  private static boolean accountUpdated = false;

  public static List<Tile> getTileList() {
    return tileList;
  }

  public static List<PowerUpFlame> getPowerUpFlameList() {
    return powerUpFlameList;
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

  public static List<PowerUpSpeed> getPowerUpSpeedList() {
    return powerUpSpeedList;
  }

  public static void setPowerUpSpeedList(List<PowerUpSpeed> powerUpSpeedList) {
    Board.powerUpSpeedList = powerUpSpeedList;
  }

  public static List<PowerUpBomb> getPowerUpBombList() {
    return powerUpBombList;
  }

  public static void setPowerUpBombList(List<PowerUpBomb> powerUpBombList) {
    Board.powerUpBombList = powerUpBombList;
  }

  public static Bomber getBomber() {
    return bomber;
  }

  public static int getPlayerNumber() {
    return playerNumber;
  }

  public static void setPlayerNumber(int number) {
    playerNumber = number;
  }

  public static int getBoardLevel() {
    return boardLevel;
  }

  /**
   * Needed because SCENES AND CHILDREN ROOT MUST BE LOADED SEQUENTIALLY.
   *
   * @param boardLevel indicate level we want to play.
   */
  public static void setBoardLevel(int boardLevel) {
    Board.boardLevel = boardLevel;
  }

  public static Scene getScene() {
    return scene;
  }

  public static Group getRoot() {
    return root;
  }

  public static void setRoot(Group g) {
    root = g;
  }

  public static void init(int boardLevel) {
    setBoardLevel(boardLevel);

    root = new Group();
    // load background of this class
    FXMLLoader fx = new FXMLLoader(App.class.getResource("/scenes/board.fxml"));
    try {
      root.getChildren().add(fx.load());
    } catch (IOException i) {
    }

    scene = new Scene(root);
    // load level game and initial render
    setLevelLoader(getBoardLevel());
    canvas = new Canvas(width, height);
    canvas.setLayoutX(16);
    canvas.setLayoutY(30);
    // ve lai background tu y=476
    root.getChildren().add(canvas);
    graphicsContext = canvas.getGraphicsContext2D();
    // bomber = new Bomber(32, 32);
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
    // ap init
    ap = new AnchorPane();
    // init of ingameInformation
    ingameName = new Text();
    ingameName.setWrappingWidth(500.0);
    ingameName.setLayoutX(39.0);
    ingameName.setLayoutY(503.0);
    ingameName.setText("Name: " + currentPlayer.getUserName());
    ingameHealth = new Text("Health: 1");
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
    // init of timing
    timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        frame++;
        unresetFrame++;
        frame %= 60;
        if (frame % 2 == 0) {
          update();
          render();
        }
        if (unresetFrame == Long.MAX_VALUE) {
          unresetFrame = 0;
        }
        if (unresetFrame % 180 == 0) { // increase point per 3 seconds
          currentPlayer.setLastestScore(currentPlayer.getLastestScore() + 1);
          ingameScore.setText("Score: " + currentPlayer.getLastestScore());
        }
        if (unresetFrame % 60 == 0) { // increase second played
          currentPlayer.setSecondsPlayed(currentPlayer.getSecondsPlayed() + 1);
          currentPlayer.setDummyAccount(false);// not dummy account any more.
          long tempSeconds = unresetFrame / 60;
          long tempMinutes = tempSeconds / 60;
          tempSeconds %= 60;
          String tempTime =
              "Time: " + ((tempMinutes < 10) ? ("0" + tempMinutes) : Long.toString(tempMinutes))
                  + ":" + ((tempSeconds < 10) ? ("0" + tempSeconds) : Long.toString(tempSeconds));
          ingameTime.setText(tempTime);
        }


      }
    };
    timer.start();

  }

  public static void addEntity(Entity entity) {
    // Design pattern ???
    if (entity instanceof Tile) {
      tileList.add((Tile) entity);
      if (entity instanceof PowerUpFlame) {
        powerUpFlameList.add((PowerUpFlame) entity);
      }
      if (entity instanceof PowerUpSpeed) {
        powerUpSpeedList.add((PowerUpSpeed) entity);
      }
      if (entity instanceof PowerUpBomb) {
        powerUpBombList.add((PowerUpBomb) entity);
      }
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
      if (entity instanceof PowerUpFlame) {
        powerUpFlameList.removeIf(i -> i.equals(entity));
      }
      if (entity instanceof PowerUpSpeed) {
        powerUpSpeedList.removeIf(i -> i.equals(entity));
      }
      if (entity instanceof PowerUpBomb) {
        powerUpBombList.removeIf(i -> i.equals(entity));
      }
    } else if (entity instanceof Bomb) {
      bombList.removeIf(i -> i.equals(entity));
    } else if (entity instanceof Flame) {
      flameList.removeIf(i -> i.equals(entity));
    } else if (entity instanceof Enemy) {
      enemyList.removeIf(i -> i.equals(entity) && i.getHealth() <= 0);
    } else {
      bomber = new Bomber(-32, -32);
    }
  }

  public static void render() {
    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    // this already loaded power up list.
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
    for (int i = 0; i < powerUpFlameList.size(); i++) {
      powerUpFlameList.get(i).update();
    }
    for (int i = 0; i < powerUpBombList.size(); i++) {
      powerUpBombList.get(i).update();
    }
    for (int i = 0; i < powerUpSpeedList.size(); i++) {
      powerUpSpeedList.get(i).update();
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

  public static String getEnemyType(Enemy e) {
    if (e instanceof Balloom) {
      return "Ballom";
    }
    if (e instanceof Oneal) {
      return "Oneal";
    }
    // todo: (LOW PRIORITY) Add more enemys beyond, although idea of this function was falled
    return "no";
  }


  public static void setHeight(int height) {
    Board.height = height;
  }

  public static void setWidth(int width) {
    Board.width = width;
  }

  public static void explode(int x, int y) {
    bomber.explode(x, y);
  }

  public static long getUnresetFrame() {
    return unresetFrame;
  }

  public static void setUnresetFrame(long frame) {
    unresetFrame = frame;
  }

  public static void setLevelLoader(int index) {
    if (!(lvd == null)) {
      lvd.removeAll();
    }
    if (root.getChildren().contains(ap)) {
      root.getChildren().remove(ap);
    }
    lvd = new FileLevelLoader(index);
    lvd.createEntities();
    // init of timing
    timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        frame++;
        unresetFrame++;
        frame %= 60;
        if (frame % 2 == 0) {
          update();
          render();
        }
        if (unresetFrame == Long.MAX_VALUE) {
          unresetFrame = 0;
        }
        if (unresetFrame % 180 == 0) { // increase point per 3 seconds
          currentPlayer.setLastestScore(currentPlayer.getLastestScore() + 1);
          ingameScore.setText("Score: " + currentPlayer.getLastestScore());
        }
        if (unresetFrame % 60 == 0) { // increase second played
          currentPlayer.setSecondsPlayed(currentPlayer.getSecondsPlayed() + 1);
          currentPlayer.setDummyAccount(false);// not dummy account any more.
          long tempSeconds = unresetFrame / 60;
          long tempMinutes = tempSeconds / 60;
          tempSeconds %= 60;
          String tempTime =
              "Time: " + ((tempMinutes < 10) ? ("0" + tempMinutes) : Long.toString(tempMinutes))
                  + ":" + ((tempSeconds < 10) ? ("0" + tempSeconds) : Long.toString(tempSeconds));
          ingameTime.setText(tempTime);
        }
      }
    };

  }

  public static void clearInput() {
    input = new HashSet<>();
  }

  public static void removeInRoot(Node n) {
    if (root.getChildren().contains(n)) {
      root.getChildren().remove(n);
    }
  }

  public static void goInGamePane(int status) {
    if (!accountUpdated) {
      timer.stop();
      // Some change inside account
      if (status != 2) {
        currentPlayer.setAccumulateScore(
            currentPlayer.getAccumulateScore() + currentPlayer.getLastestScore());
        System.out.println("Accu: " + currentPlayer.getAccumulateScore());
        if (currentPlayer.getLastestScore() > currentPlayer.getHighestScore()) {
          currentPlayer.setHighestScore(currentPlayer.getLastestScore());
        }
      }
      if (status == 1) {
        currentPlayer.setNumberOfDead(currentPlayer.getNumberOfDead() + 1);
      }
      accountUpdated = true;
      // because by unknown reason accu score was + 2 times.
      // Anchor pane
      ap.setLayoutX(219.0);
      ap.setLayoutY(259.0);
      ap.setPrefHeight(250.0);
      ap.setPrefWidth(586.0);
      ap.setMinSize(290.0, 250.0);
      BackgroundFill bgfill = new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY);
      Background bg = new Background(bgfill);
      ap.setBackground(bg);
      // Text
      Text statusText = new Text();
      if (status == 0) {
        statusText.setText("You win! Score: " + currentPlayer.getLastestScore());
      } else if (status == 1) {
        statusText.setText("You lose! Score: " + currentPlayer.getLastestScore());
      } else {
        statusText.setText("In-game pause.");
      }
      Font f = new Font("System", 24);
      statusText.setFont(f);
      statusText.setWrappingWidth(296.607);
      statusText.setLayoutX(145.0);
      statusText.setLayoutY(54.0);
      statusText.setTextAlignment(TextAlignment.valueOf("CENTER"));
      // Button
      Button b1 = new Button("Back");
      Button b2 = new Button("Next");
      Button b3 = new Button("Replay");
      b1.setPadding(new Insets(10, 10, 10, 10));
      b1.setPrefWidth(100.0);
      b1.setPrefHeight(38.0);
      b1.setLayoutX(57.0);
      b1.setLayoutY(164.0);
      b1.setOnAction(e -> {
        removeInRoot(b1);
        removeInRoot(b2);
        removeInRoot(b3);
        removeInRoot(statusText);
        // timer.start();
        currentPlayer.setLastestScore(0);
        accountUpdated = false;
        statusText.setText("");
        clearInput();
        App.goBackMainMenu();

        // setLevelLoader(1);
      });
      b2.setPadding(new Insets(10, 10, 10, 10));
      b2.setPrefWidth(100.0);
      b2.setPrefHeight(38.0);
      b2.setLayoutX(243.0);
      b2.setLayoutY(164.0);
      if (status == 2) {
        b2.setText("Continue");
      }

      b2.setOnAction(e -> {
        // timer.start();
        if (status == 2) {
          if (root.getChildren().contains(ap)) {
            root.getChildren().remove(ap);
          }
        } else if (status == 0) {
          if (getBoardLevel() == 5) {

            System.out.println("In developing...");
            currentPlayer.setLastestScore(0);
          } else {
            statusText.setText("");
            setBoardLevel(getBoardLevel() + 1);
            setLevelLoader(getBoardLevel());
            clearInput();
            removeInRoot(b1);
            removeInRoot(b2);
            removeInRoot(b3);
            removeInRoot(statusText);
            App.goBackMainMenu();
            App.toMainGame(Board.getBoardLevel());
            currentPlayer.setLastestScore(0);
          }
        }
        accountUpdated = false;
        clearInput();
      });
      b3.setPadding(new Insets(10, 10, 10, 10));
      b3.setPrefWidth(100.0);
      b3.setPrefHeight(38.0);
      b3.setLayoutX(422.0);
      b3.setLayoutY(164.0);
      b3.setOnAction(e -> {
        clearInput();
        removeInRoot(b1);
        removeInRoot(b2);
        removeInRoot(b3);
        removeInRoot(statusText);
        App.goBackMainMenu();
        App.toMainGame(Board.getBoardLevel());
        // setLevelLoader(getBoardLevel());
        currentPlayer.setLastestScore(0);
        statusText.setText("");
        accountUpdated = false;
        clearInput();

      });

      ap.getChildren().add(statusText);
      ap.getChildren().add(b1);

      if (status != 1||getBoardLevel()==5) {
        ap.getChildren().add(b2);
      }
      ap.getChildren().add(b3);
      root.getChildren().add(ap);
    }
  }
}
