package mainClass;

import entity.animated.mob.Bomber;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import entity.*;
import entity.animated.*;
import entity.tile.*;
import sprite.Sprite;


/**
 * JavaFX App.
 */
public class App extends Application {

  public static final int WIDTH = 20;
  public static final int HEIGHT = 15;

  private GraphicsContext gc;
  private Canvas canvas;
  private List<Entity> entities = new ArrayList<>();
  private List<Entity> stillObjects = new ArrayList<>();

  private static Scene scene;



  @Override
  public void start(Stage stage) throws IOException {
    //start testing
//    canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
//    gc = canvas.getGraphicsContext2D();
//
//    // Tao root container
//    Group root = new Group();
//    root.getChildren().add(canvas);
//
//    // Tao scene
//    Scene scene = new Scene(root);
//
//    // Them scene vao stage
//    stage.setScene(scene);
//    stage.show();
//
//    AnimationTimer timer = new AnimationTimer() {
//      @Override
//      public void handle(long l) {
//        render();
//        update();
//      }
//    };
//    timer.start();
//
//    createMap();
//
//    Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
//    entities.add(bomberman);


    //end testing
    Image icon = new Image(App.class.getResource("/Pictures/icon.png").toString());

    scene = new Scene(loadFxml("/scenes/loadingMenu"), 1024, 768);

    stage.setScene(scene);
    stage.getIcons().add(icon);
    stage.setResizable(false);
    stage.setFullScreen(false);

    stage.show();

  }

  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFxml(fxml));

  }

  private static Parent loadFxml(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public void createMap() {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        Entity object;
        if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
          object = new Wall(i, j, Sprite.wall.getFxImage());
        }
        else {
          object = new Grass(i, j, Sprite.grass.getFxImage());
        }
        stillObjects.add(object);
      }
    }
  }

  public void update() {
    entities.forEach(Entity::update);
  }

  public void render() {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
  }



  public static void main(String[] args) {
    launch();
  }

}
