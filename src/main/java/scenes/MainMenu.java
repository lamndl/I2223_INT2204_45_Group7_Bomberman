package scenes;
import static mainClass.App.currentPlayer;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mainClass.App;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import mainClass.Player;
import mainClass.PlayerManagement;
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
  public TextArea insidePaneTextArea = new TextArea();
  public Button insidePaneStatButton = new Button();
  public Button insidePaneLogoutButton = new Button();
  public Button insidePaneRegisterButton = new Button();
  public Button insidePaneLoginButton = new Button();
  private Alert alert = new Alert(AlertType.ERROR);
  private int currentIndex;

  //private boolean loginOrRegister = true;
  private boolean insidePaneStatClicked = false;

  private CharacterSceneManagement csm;

  /**
   * If no logged account -> Create new dummy account
   */

  public void initialize(){
    csm = new CharacterSceneManagement();
    currentIndex = csm.getCurrentLength()*100;
    characterMainMenuImage.setOnMouseClicked(e->changeCharacterMainMenu());
    characterMainMenuImage.setImage(csm.getImageFromIndex(currentIndex%csm.getCurrentLength()));
    characterMainMenuButton.setText(csm.getTextFromIndex(currentIndex%csm.getCurrentLength()));
    PlayerManagement.readDataLineByLine("/test.csv");
    currentPlayer=PlayerManagement.getLoggedAccount();
    if(currentPlayer!=null){
      nameButton.setText(currentPlayer.getUserName());
    }else{
      Player newPlayer = new Player(PlayerManagement.generateRandomString(),PlayerManagement.generateRandomString(),true);
      newPlayer.setLogged(true);
      currentPlayer=newPlayer;
      PlayerManagement.addPlayer(newPlayer);
    }
    App.inAccount=true;
    insidePane.setVisible(false);
    insidePaneStatusText.setText("Login/Register");
    insidePaneResultText.setText("");
    insidePaneLogoutButton.setVisible(false);
    insidePaneTextArea.setVisible(false);
    setUpAfterLoginAndRegister();
  }

  @FXML
  public void switchToCampaignScene() throws IOException {
    if(!App.inAccount||currentPlayer==null){
      showAlert("You have to log in your account before playing campaign.","Error");
    }else{
      App.setRoot("/scenes/campaignScene");
      CharacterChoosing.setCampaignOrEndless(true);
    }

  }

  public void switchToEndlessScene() throws IOException {
    if(!App.inAccount||currentPlayer==null){
      showAlert("You have to log in your account before playing endless.","Error");
    }else{
      App.setRoot("/scenes/endlessScene");
      CharacterChoosing.setCampaignOrEndless(false);
    }
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

  public boolean checkValidInformation(){
    if(insidePaneUsernameTextField.getText().length()>=6&&insidePanePasswordTextField.getText().length()>=6){
      return true;
    }else{
      return false;
    }
  }

  public void insidePaneShowGuide(){
    insidePaneResultText.setText("Username and password must contain at least 6 characters.");
  }
  public void setUpAfterLoginAndRegister(){
    insidePanePasswordTextField.setVisible(false);
    insidePaneUsernameTextField.setVisible(false);
    insidePaneLogoutButton.setVisible(true);
    nameButton.setText(currentPlayer.getUserName());
    insidePaneStatusText.setText("Welcome, " + currentPlayer.getUserName()+".");
    insidePaneStatButton.setVisible(true);
    insidePaneResultText.setText("");
    insidePaneLoginButton.setVisible(false);
    insidePaneRegisterButton.setVisible(false);

  }

  public void registerPlayer()throws IOException{ //change
    if(checkValidInformation()){
      Player newPlayer = new Player(insidePaneUsernameTextField.getText(),insidePanePasswordTextField.getText());
      PlayerManagement.addPlayer(newPlayer);
      currentPlayer= newPlayer;
      currentPlayer.setLogged(true);
      setUpAfterLoginAndRegister();
    }else{
      insidePaneResultText.setText("You have to enter information before click to that button.");
    }
  }

  public void loginPlayer() throws IOException{
    if(checkValidInformation()){
      if(PlayerManagement.checkIfExistPlayer(insidePaneUsernameTextField.getText(),insidePanePasswordTextField.getText())!=-1){
        currentPlayer = PlayerManagement.getPlayer(PlayerManagement.checkIfExistPlayer(insidePaneUsernameTextField.getText(),insidePanePasswordTextField.getText()));
        currentPlayer.setLogged(true);
        setUpAfterLoginAndRegister();

      }else{
        insidePaneResultText.setText("Wrong information. Please try again");
      }
    }else{
      insidePaneResultText.setText("Please conform the guide (click on i button for more information)");
    }
  }

  public void insidePaneLogout(){
    currentPlayer.setLogged(false);
    if(currentPlayer.isDummyAccount()){
      PlayerManagement.removePlayer(currentPlayer);
    }
    currentPlayer=null;
    App.inAccount=false;
    insidePaneStatClicked=true;
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
  }

  public void insidePaneShowStat(){
    if(!insidePaneStatClicked){
      insidePaneTextArea.setVisible(true);
      insidePaneTextArea.setText(currentPlayer.toString());
      insidePaneStatButton.setVisible(true);
      insidePaneStatClicked=true;
    }else{
      insidePaneTextArea.setVisible(false);
      insidePaneStatClicked=false;
    }

  }
  public void exitGame() throws IOException {
    //dummy account must be deleted
    if(currentPlayer!=null){
      if(currentPlayer.isDummyAccount()){
        PlayerManagement.getArrayListPlayer().remove(currentPlayer);
      }
    }
    PlayerManagement.writeDataLineByLine("/test.csv");
    Platform.exit();
    System.exit(0);
  }

  public void showAlert(String text,String title){
    alert.setContentText(text);
    alert.setTitle(title);
    alert.show();
  }

  public void setNameButton(String label){
    nameButton.setText(label);
  }

}
