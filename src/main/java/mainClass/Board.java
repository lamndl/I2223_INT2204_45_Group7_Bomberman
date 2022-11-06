package mainClass;

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
import javafx.geometry.Pos;
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
  private static int playerNumber=1; //by default

  /**
   * setup the level of game.
   * MUST BE SET BEFORE LOAD
   */
  private static int boardLevel = 1;
  public static List<Tile> getTileList() {
    return tileList;
  }

  public static List<PowerUpFlame> getPowerUpFlameList(){ return powerUpFlameList; }

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

  public static int getPlayerNumber(){
    return playerNumber;
  }

  public static void setPlayerNumber(int number){
    playerNumber=number;
  }

  public static int getBoardLevel() {
    return boardLevel;
  }

  /**
   * Needed because SCENES AND CHILDREN ROOT MUST BE LOADED SEQUENTIALLY.
   * @param boardLevel indicate level we want to play.
   */
  public static void setBoardLevel(int boardLevel) {
    Board.boardLevel = boardLevel;
  }

  private static List<Tile> tileList = new ArrayList<>();
  private static List<Bomb> bombList = new ArrayList<>();
  private static List<Flame> flameList = new ArrayList<>();
  private static List<Enemy> enemyList = new ArrayList<>();
  private static List<PowerUpFlame> powerUpFlameList = new ArrayList<>();
  private static List<PowerUpSpeed> powerUpSpeedList = new ArrayList<>();
  private static List<PowerUpBomb> powerUpBombList = new ArrayList<>();
  private static Bomber bomber;
  private static long unresetFrame =0 ;
  public static long frame;
  public static Set<KeyCode> input = new HashSet<>();

  public static LevelLoader lvd;

  private static AnchorPane ap = new AnchorPane();
  private static AnimationTimer timer = new AnimationTimer() {
    @Override
    public void handle(long now) {
      frame++;
      unresetFrame++;
      frame %= 60;
      if (frame % 2 == 0) {
        update();
        render();
      }
      if(unresetFrame==Long.MAX_VALUE){
        unresetFrame=0;
      }
    }
  };
  public static Scene getScene() {
    return scene;
  }

  public static Group getRoot(){
    return root;
  }

  public static void setRoot(Group g){
    root = g;
  }

  public static void init() {
    root = new Group();

    FXMLLoader fx = new FXMLLoader(App.class.getResource("/scenes/board.fxml"));
    try {
      root.getChildren().add(fx.load());
    } catch (IOException i) {

    }

    scene = new Scene(root);

    setLevelLoader(getBoardLevel());
    canvas = new Canvas(width, height);
    canvas.setLayoutX(16);
    canvas.setLayoutY(30);
    // ve lai background tu y=476
    root.getChildren().add(canvas);
    graphicsContext = canvas.getGraphicsContext2D();
    //bomber = new Bomber(32, 32);
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

    timer.start();
  }

  public static void addEntity(Entity entity) {
    // Design pattern ???
    if (entity instanceof Tile) {
      tileList.add((Tile) entity);
      if(entity instanceof PowerUpFlame){
        powerUpFlameList.add((PowerUpFlame) entity);
      }
      if(entity instanceof PowerUpSpeed){
        powerUpSpeedList.add((PowerUpSpeed) entity);
      }
      if(entity instanceof PowerUpBomb){
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
      if(entity instanceof PowerUpFlame){
        powerUpFlameList.removeIf(i->i.equals(entity));
      }
      if(entity instanceof PowerUpSpeed){
        powerUpSpeedList.removeIf(i->i.equals(entity));
      }
      if(entity instanceof PowerUpBomb){
        powerUpBombList.removeIf(i->i.equals(entity));
      }
    } else if (entity instanceof Bomb) {
      bombList.removeIf(i -> i.equals(entity));
    } else if (entity instanceof Flame) {
      flameList.removeIf(i -> i.equals(entity));
    } else if (entity instanceof Enemy) {
      enemyList.removeIf(i -> i.equals(entity)&&i.getHealth()<=0);
    } else {
      bomber = new Bomber(-32, -32);
    }
  }

  public static void render() {
    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    //this already loaded power up list.
    tileList.forEach(i -> i.draw(graphicsContext));
    enemyList.forEach(i -> i.draw(graphicsContext));
    bombList.forEach(i -> i.draw(graphicsContext));
    bomber.draw(graphicsContext);
    flameList.forEach(i -> i.draw(graphicsContext));
    //System.out.println(getPlayerNumber());
    //powerUpFlameList.forEach(i->i.draw(graphicsContext));

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
    for(int i = 0 ; i< powerUpFlameList.size();i++){
      powerUpFlameList.get(i).update();
    }
    for(int i =0;i<powerUpBombList.size();i++){
      powerUpBombList.get(i).update();
    }
    for(int i =0;i<powerUpSpeedList.size();i++){
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

  public static String getEnemyType(Enemy e){
    if(e instanceof Balloom){
      return "Ballom";
    }
    if(e instanceof Oneal){
      return "Oneal";
    }
    //todo: (LOW PRIORITY) Add more enemys beyond, although idea of this function was falled
    return "no";
  }


  public static void setHeight(int height) {
    Board.height = height;
  }

  public static void setWidth(int width) {
    Board.width = width;
  }

  public static void explode(int x, int y){
    bomber.explode(x,y);
  }

  public static long getUnresetFrame(){
    return unresetFrame;
  }

  public static void setUnresetFrame(long frame){
    unresetFrame=frame;
  }

  public static void setLevelLoader(int index){
    if(!(lvd == null)){
      lvd.removeAll();
    }
    if(root.getChildren().contains(ap)){
      root.getChildren().remove(ap);
    }
    lvd  = new FileLevelLoader(index);
    lvd.createEntities();
  }
  public static void goInGamePane(int status){
    /**
     * 0: Win
     * 1: Loss
     * 2: Pause in-game
     */
    //todo: Show noti, people can choose action.
    timer.stop();

    ap.setLayoutX(219.0);
    ap.setLayoutY(259.0);
    ap.setPrefHeight(250.0);
    ap.setPrefWidth(586.0);
    ap.setMinSize(290.0,250.0);
    BackgroundFill bgfill = new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY);
    Background bg  = new Background(bgfill);
    ap.setBackground(bg);
    //Text
    Text statusText = new Text();
    if(status==0){
      statusText.setText("You win!");
    }else if(status==1){
      statusText.setText("You lose!");
    }else{
      statusText.setText("In-game pause.");
    }
    Font f = new Font("System",24);
    statusText.setFont(f);
    statusText.setWrappingWidth(296.607);
    statusText.setLayoutX(145.0);
    statusText.setLayoutY(54.0);
    statusText.setTextAlignment(TextAlignment.valueOf("CENTER"));
    //Button
    Button b1 = new Button("Back");
    Button b2 = new Button("Next");
    Button b3 = new Button("Replay");
    b1.setPadding(new Insets(10,10,10,10));
    b1.setPrefWidth(100.0);
    b1.setPrefHeight(38.0);
    b1.setLayoutX(57.0);
    b1.setLayoutY(164.0);
    b1.setOnAction(e->{
      timer.start();
      App.goBackMainMenu();

      //setLevelLoader(1);
    });
    b2.setPadding(new Insets(10,10,10,10));
    b2.setPrefWidth(100.0);
    b2.setPrefHeight(38.0);
    b2.setLayoutX(243.0);
    b2.setLayoutY(164.0);
    if(status==2){
      b2.setText("Continue");
    }

    b2.setOnAction(e->{
      timer.start();
      if(status==2){
        if(root.getChildren().contains(ap)){
          root.getChildren().remove(ap);
        }
      }else if(status==0){
        if(getBoardLevel()==2){
          //todo: Create more map and change above number
          System.out.println("In developing...");
        }else{
          setBoardLevel(getBoardLevel()+1);
          setLevelLoader(getBoardLevel());
        }
      }


    });
    b3.setPadding(new Insets(10,10,10,10));
    b3.setPrefWidth(100.0);
    b3.setPrefHeight(38.0);
    b3.setLayoutX(422.0);
    b3.setLayoutY(164.0);
    b3.setOnAction(e->{
      timer.start();
      setLevelLoader(getBoardLevel());
    });

    ap.getChildren().add(statusText);
    ap.getChildren().add(b1);

    if(status!=1){
      ap.getChildren().add(b2);
    }
    ap.getChildren().add(b3);
    root.getChildren().add(ap);

  }



}
