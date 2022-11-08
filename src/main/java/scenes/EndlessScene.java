package scenes;

import java.io.IOException;
import mainClass.App;

public class EndlessScene {

  public void initialize() {
  }

  public void goBackMainMenu() throws IOException {
    App.setRoot("/scenes/mainMenu");
  }

  public void changeChoosingCharacterScene() throws IOException {
    App.setRoot("/scenes/characterChoosing");
  }
}
