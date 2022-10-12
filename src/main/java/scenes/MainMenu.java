package scenes;
import mainClass.App;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainMenu {

  @FXML
  public void switchToCampaignScene() throws IOException {
    App.setRoot("/scenes/campaignScene");
  }

  public void switchToEndlessScene() throws IOException {
    App.setRoot("/scenes/endlessScene");
  }

  public void switchToSettingScene() throws IOException {
    App.setRoot("/scenes/settingScene");
  }

  public void switchToHowToPlayScene() throws IOException {
    App.setRoot("/scenes/howToPlayScene");
  }

  public void switchToCreditScene() throws IOException {
    App.setRoot("/scenes/creditScene");
  }

  public void changeCharacterMainMenu() throws IOException{

  }
  public void togglePlayerButton() throws IOException {

  }

  public void toggleMusicCheckBox() throws IOException {

  }

  public void exitGame() throws IOException {
    Platform.exit();
    System.exit(0);
  }
}
