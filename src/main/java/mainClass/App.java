package mainClass;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App.
 */
public class App extends Application {

  private static Scene scene;

  @Override
  public void start(Stage stage) throws IOException {
    Image icon = new Image(App.class.getResource("/Pictures/icon.png").toString());

    Board.init();
    scene = new Scene(Board.root, 1024, 768);
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

  public static void main(String[] args) {
    launch();
  }

}
