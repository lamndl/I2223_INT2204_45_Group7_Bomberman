package experiment;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ButtonExample extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    BorderPane pane = new BorderPane();
    Button button = new Button("Press Me!");
    pane.setCenter(button);
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setScene(scene);
    primaryStage.show();

    button.setOnKeyPressed(new EventHandler<KeyEvent>() {

      @Override
      public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
          System.out.println("Enter Pressed");
        }
      }
    });
  }

  public static void main(String[] args) {
    launch(args);
  }
}