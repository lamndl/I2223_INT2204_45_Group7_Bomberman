package scenes;


import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import mainClass.App;
import mainClass.Player;
import mainClass.PlayerManagement;
import mainClass.Sound;

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
  public TextArea insidePaneTextArea = new TextArea();
  public Button insidePaneStatButton = new Button();
  public Button insidePaneLogoutButton = new Button();
  public Button insidePaneRegisterButton = new Button();
  public Button insidePaneLoginButton = new Button();
  public Button insidePaneChangeInformationButton = new Button();
  private boolean changing = false;
  private Alert alert = new Alert(AlertType.ERROR);
  private int currentIndex;
  private boolean insidePaneStatClicked = false;
  private CharacterSceneManagement csm;

  /**
   * If no logged account -> Create new dummy account .
   */

  public Button leaderboardButton = new Button();

  public void initialize() {

    csm = new CharacterSceneManagement();
    currentIndex = csm.getCurrentLength() * 100;
    characterMainMenuImage.setOnMouseClicked(e -> changeCharacterMainMenu());
    characterMainMenuImage.setImage(csm.getImageFromIndex(currentIndex % csm.getCurrentLength()));
    characterMainMenuButton.setText(csm.getTextFromIndex(currentIndex % csm.getCurrentLength()));

    App.currentPlayer = PlayerManagement.getLoggedAccount();
    // bug here
    if (App.currentPlayer != null) {
      nameButton.setText(App.currentPlayer.getUserName());
    } else {
      Player newPlayer = new Player(PlayerManagement.generateRandomString(),
          PlayerManagement.generateRandomString(), true);
      newPlayer.setLogged(true);
      App.currentPlayer = newPlayer;
      PlayerManagement.addPlayer(newPlayer);
    }
    App.inAccount = true;
    insidePane.setVisible(false);
    insidePaneStatusText.setText("Login/Register");
    insidePaneResultText.setText("");
    insidePaneLogoutButton.setVisible(false);
    insidePaneTextArea.setVisible(false);
    setUpAfterLoginAndRegister();

    musicCheckBox.setSelected(!Sound.isBackgroundMute());
  }

  @FXML
  public void switchToCampaignScene() throws IOException {
    if (!App.inAccount || App.currentPlayer == null) {
      showAlert("You have to log in your account before playing campaign.", "Error");
    } else {
      App.setRoot("/scenes/campaignScene");
      CharacterChoosing.setCampaignOrEndless(true);
    }
    App.coe = true;

  }

  public void switchToEndlessScene() throws IOException {
    if (!App.inAccount || App.currentPlayer == null) {
      showAlert("You have to log in your account before playing endless.", "Error");
    } else {
      App.setRoot("/scenes/endlessScene");
      CharacterChoosing.setCampaignOrEndless(false);
    }
    App.coe = false;
    App.mapLevel = 1;

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

  public void changeCharacterMainMenu() {
    if (currentIndex < csm.getCurrentLength()) {
      currentIndex += csm.getCurrentLength() * 100;
    }
    currentIndex--;
    characterMainMenuImage.setImage(csm.getImageFromIndex(currentIndex % csm.getCurrentLength()));
    characterMainMenuButton.setText(csm.getTextFromIndex(currentIndex % csm.getCurrentLength()));
  }

  public void togglePlayerButton() throws IOException {
    insidePane.setVisible(true);
  }

  public void toggleMusicCheckBox() throws IOException {
    if (!musicCheckBox.isSelected()) {
      Sound.toggleMuteBackgroundSound(true);
    }
    if (musicCheckBox.isSelected()) {
      Sound.toggleMuteBackgroundSound(false);
    }
  }

  public void insidePaneExit() throws IOException {
    insidePane.setVisible(false);
    insidePaneResultText.setText("");
  }

  public boolean checkValidInformation() {
    if (insidePaneUsernameTextField.getText().length() >= 6
        && insidePanePasswordTextField.getText().length() >= 6 && checkDuplicate()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean checkDuplicate() {
    for (Player i : PlayerManagement.getArrayListPlayer()) {
      if (insidePaneUsernameTextField.getText().equals(i.getUsername()) &&
          insidePanePasswordTextField.getText().equals(i.getPassword())) {
        return false;
      }
    }
    return true;
  }

  public void insidePaneShowGuide() {
    insidePaneResultText.setText("Username and password must contain at least 6 characters.");
  }

  public void setUpAfterLoginAndRegister() {
    changing = false;
    insidePanePasswordTextField.setVisible(false);
    insidePaneUsernameTextField.setVisible(false);
    insidePaneLoginButton.setText("Login");
    insidePaneLogoutButton.setVisible(true);
    nameButton.setText(App.currentPlayer.getUserName());
    insidePaneStatusText.setText("Welcome, " + App.currentPlayer.getUserName() + ".");
    insidePaneStatButton.setVisible(true);
    insidePaneResultText.setText("");
    insidePaneLoginButton.setVisible(false);
    insidePaneRegisterButton.setVisible(false);
    insidePaneChangeInformationButton.setVisible(true);
  }

  public void insidePaneShowChangeInformation() {
    changing = true;
    insidePaneChangeInformationButton.setVisible(false);
    insidePaneLoginButton.setVisible(true);
    insidePaneLoginButton.setText("Change");
    insidePanePasswordTextField.setVisible(true);
    insidePaneUsernameTextField.setVisible(true);
    // todo: Add more

  }

  public void registerPlayer() throws IOException { // change
    if (checkValidInformation()) {
      Player newPlayer =
          new Player(insidePaneUsernameTextField.getText(), insidePanePasswordTextField.getText());
      PlayerManagement.addPlayer(newPlayer);
      App.currentPlayer = newPlayer;
      App.currentPlayer.setLogged(true);
      setUpAfterLoginAndRegister();
    } else {
      insidePaneResultText.setText("You have to enter information before click to that button.");
    }
  }

  public void loginPlayer() throws IOException {
    if (checkValidInformation()) {
      if (!changing) {
        if (PlayerManagement.checkIfExistPlayer(insidePaneUsernameTextField.getText(),
            insidePanePasswordTextField.getText()) != -1) {
          App.currentPlayer = PlayerManagement.getPlayer(PlayerManagement.checkIfExistPlayer(
              insidePaneUsernameTextField.getText(), insidePanePasswordTextField.getText()));
          App.currentPlayer.setLogged(true);
          setUpAfterLoginAndRegister();

        } else {
          insidePaneResultText.setText("Wrong information. Please try again");
        }
      } else {
        App.currentPlayer.setUsername(insidePaneUsernameTextField.getText());
        App.currentPlayer.setPassword(insidePanePasswordTextField.getText());
        setUpAfterLoginAndRegister();
      }

    } else {
      if (!checkDuplicate()) {
        insidePaneResultText.setText("Information duplicated");
      } else {
        insidePaneResultText
            .setText("Please conform the guide (click on i button for more information)");
      }
    }
  }

  public void insidePaneLogout() {
    App.currentPlayer.setLogged(false);
    if (App.currentPlayer.isDummyAccount()) {
      PlayerManagement.removePlayer(App.currentPlayer);
    }
    App.currentPlayer = null;
    App.inAccount = false;
    insidePaneStatClicked = true;
    insidePaneShowStat();
    insidePaneStatusText.setText("Login/Register");
    insidePaneResultText.setText("");
    insidePaneLogoutButton.setVisible(false);
    insidePanePasswordTextField.setVisible(true);
    insidePaneUsernameTextField.setVisible(true);
    insidePaneStatButton.setVisible(false);
    nameButton.setText("Login");
    insidePaneLoginButton.setVisible(true);
    insidePaneRegisterButton.setVisible(true);
    insidePaneChangeInformationButton.setVisible(false);
  }

  public void insidePaneShowStat() {
    if (!insidePaneStatClicked) {
      insidePaneTextArea.setVisible(true);
      insidePaneTextArea.setText(App.currentPlayer.toString());
      insidePaneStatButton.setVisible(true);
      insidePaneStatClicked = true;
    } else {
      insidePaneTextArea.setVisible(false);
      insidePaneStatClicked = false;
    }

  }

  public void exitGame() throws IOException {
    // dummy account must be deleted
    if (App.currentPlayer != null && App.currentPlayer.isDummyAccount()) {
      PlayerManagement.getArrayListPlayer().remove(App.currentPlayer);

    }
    PlayerManagement.writeDataLineByLine("/test.csv");
    Platform.exit();
    System.exit(0);
  }

  public void showAlert(String text, String title) {
    alert.setContentText(text);
    alert.setTitle(title);
    alert.show();
  }

  public void setNameButton(String label) {
    nameButton.setText(label);
  }

  public void switchToLeaderboardScene() throws IOException {
    App.setRoot("/scenes/leaderboardScene");
  }
}
