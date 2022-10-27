package scenes;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import mainClass.App;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import mainClass.Sound;
import scenes.*;
public class MainMenu {
  public Button campaignButton = new Button();
  public Button endlessButton = new Button();
  public Button settingButton = new Button();
  public Button howToPlayButton = new Button();
  public Button creditButton = new Button();
  public CheckBox musicCheckBox = new CheckBox();
  public Button nameButton = new Button();
  public Button exitButton = new Button();
  public Button characterMainMenuButton = new Button();
  public ImageView characterMainMenuImage = new ImageView();
  public AnchorPane insidePane = new AnchorPane();
  public Button insidePaneExitButton = new Button();
  public Text insidePaneStatusText = new Text();
  public TextField insidePaneUsernameTextField = new TextField();
  public TextField insidePanePasswordTextField = new TextField();
  public Text insidePaneResultText = new Text();
  private int currentIndex;

  //private boolean loginOrRegister = true;

  private CharacterSceneManagement csm;
  public void initialize(){
    csm = new CharacterSceneManagement();
    currentIndex = csm.getCurrentLength()*100;
    characterMainMenuImage.setOnMouseClicked(e->changeCharacterMainMenu());
    characterMainMenuImage.setImage(csm.getImageFromIndex(currentIndex%csm.getCurrentLength()));
    characterMainMenuButton.setText(csm.getTextFromIndex(currentIndex%csm.getCurrentLength()));
    if(!App.inAccount){
      nameButton.setText("Log in");
    }else{
      nameButton.setText(App.currentPlayer.getUserName());
    }
    insidePane.setVisible(false);
    insidePaneStatusText.setText("Login/Register");
    insidePaneResultText.setText("");
  }

  @FXML
  public void switchToCampaignScene() throws IOException {
    App.setRoot("/scenes/campaignScene");
    CharacterChoosing.setCampaignOrEndless(true);
  }

  public void switchToEndlessScene() throws IOException {
    App.setRoot("/scenes/endlessScene");
    CharacterChoosing.setCampaignOrEndless(false);

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

  public void changeCharacterMainMenu(){
    if(currentIndex<csm.getCurrentLength()){
      currentIndex+=csm.getCurrentLength()*100;
    }
    currentIndex--;
    characterMainMenuImage.setImage(csm.getImageFromIndex(currentIndex%csm.getCurrentLength()));
    characterMainMenuButton.setText(csm.getTextFromIndex(currentIndex%csm.getCurrentLength()));
  }
  public void togglePlayerButton() throws IOException {
    insidePane.setVisible(true);
  }

  public void toggleMusicCheckBox() throws IOException {
    if(!musicCheckBox.isSelected()){
      Sound.toggleMuteBackgroundSound(true);
    }
    if(musicCheckBox.isSelected()){
      Sound.toggleMuteBackgroundSound(false);
    }
  }

  public void insidePaneExit()throws IOException{
    insidePane.setVisible(false);
    insidePaneResultText.setText("");
  }

  public void registerPlayer()throws IOException{
    insidePaneResultText.setText("In developing. Sorry for the inconvenience.");
  }

  public void loginPlayer() throws IOException{
    insidePaneResultText.setText("In developing. Sorry for the inconvenience.");
  }

  public void exitGame() throws IOException {
    Platform.exit();
    System.exit(0);
  }
}
