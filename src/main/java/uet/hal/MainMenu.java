package uet.hal;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainMenu {

  @FXML
  public void switchToCampaignScene() throws IOException {
    App.setRoot("campaignScene");
  }

  public void switchToEndlessScene() throws IOException {
    App.setRoot("endlessScene");
  }

  public void switchToSettingScene() throws IOException {
    App.setRoot("settingScene");
  }

  public void switchToHowToPlayScene() throws IOException {
    App.setRoot("howToPlayScene");
  }

  public void switchToCreditScene() throws IOException {
    App.setRoot("creditScene");
  }

  public void changeCharaterMainMenu() throws IOException{

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
