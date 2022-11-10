package mainClass;

import static mainClass.Sound.playMedia;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App.
 */
public class App extends Application {

  public static Keyboard KB = new Keyboard();
  private static Scene scene;
  public static Stage stage;
  public Sound sound;

  public static boolean inAccount = false;
  public static Player currentPlayer;

  public static final int WIDTH = 1024;
  public static final int HEIGHT = 768;

  public static boolean coe = true;
  public static int mapLevel = 1;

  public static boolean toogleAI = false;

  public static boolean secondTime = false;

  @Override
  public void start(Stage stage) throws IOException {

    Image icon = new Image(App.class.getResource("/Pictures/icon.png").toString());

    /**
     * vao in-game bomberman:
     */
    this.stage = stage;
    //toMainGame();

    /**
     * vao menu:
     */
    scene = new Scene(loadFxml("/scenes/loadingMenu"), WIDTH, HEIGHT);
    stage.setScene(scene);

    stage.getIcons().add(icon);
    stage.setResizable(false);
    stage.setFullScreen(false);
    stage.show();
    sound = new Sound();
    playMedia(0);
  }

  public static void toMainGame() {
    Board.init();
    stage.setScene(Board.getScene());
    stage.centerOnScreen();
  }


  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFxml(fxml));

  }

  private static Parent loadFxml(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static Parent publicLoadFxml(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void goBackMainMenu(){
    Board.setRoot(new Group());
    stage.setScene(null);
    try{
      setRoot("/scenes/mainMenu");
    }catch(Exception eie){
      System.out.println(eie);
    }

    stage.setScene(scene);

  }
  public static void main(String[] args) {
    launch();
  }

}
