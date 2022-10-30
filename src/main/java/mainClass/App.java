package mainClass;

import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
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
  private static Stage stage;
  public Sound sound;

  public static boolean inAccount = false;
  public static Player currentPlayer;

  @Override
  public void start(Stage stage) throws IOException {

    Image icon = new Image(App.class.getResource("/Pictures/icon.png").toString());

    /**
     * vao in-game bomberman:
     */
    this.stage = stage;
    toMainGame();



    /**
     * vao menu:
     */
    //scene = new Scene(loadFxml("/scenes/loadingMenu"), 1024, 768);
    //stage.setScene(scene);


    stage.getIcons().add(icon);
    stage.setResizable(false);
    stage.setFullScreen(false);
    stage.show();
    sound = new Sound();
    //playMedia(0);
    /**
     * Tam thoi tat tieng. Dcm tieng game buoi dem nghe kinh vcl
     */
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

  public static void main(String[] args) {
    launch();
  }

}
