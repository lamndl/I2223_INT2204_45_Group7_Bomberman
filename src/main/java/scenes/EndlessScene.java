package scenes;

import java.io.IOException;
import javafx.scene.control.Button;
import mainClass.App;
import mainClass.Sound;

public class EndlessScene {

  public void initialize() {
  }

  public void goBackMainMenu() throws IOException {
    App.setRoot("/scenes/mainMenu");
    Sound.playInGameSound(6);
  }

  public void changeChoosingCharacterScene() throws IOException {
    App.setRoot("/scenes/characterChoosing");
    Sound.playInGameSound(6);
  }
}
