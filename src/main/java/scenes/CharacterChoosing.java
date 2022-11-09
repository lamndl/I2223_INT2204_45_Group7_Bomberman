package scenes;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import mainClass.App;
import mainClass.Board;
import mainClass.Sound;


public class CharacterChoosing {

  public Button backButton = new Button();
  public Text showCharacterName = new Text();
  public Button prevButton = new Button();
  public Button nextButton = new Button();

  public Button goButton = new Button();
  public ImageView previousCharacterImage = new ImageView();
  public ImageView currentCharacterImage = new ImageView();
  public ImageView nextCharacterImage = new ImageView();
  private static boolean campaignOrEndless = true;
  private CharacterSceneManagement csm;
  private int currentIndex;
  public Text informationText = new Text();
  public Text mapText = new Text();
  public Text modeText = new Text();

  public void initialize() {
    csm = new CharacterSceneManagement();
    currentIndex = csm.getCurrentLength() * 100;
    applyToScene();
    if(App.mapLevel <=3){
      informationText.setText("Normal difficulty");
    }else{
      informationText.setText("May make you die some more");

    }

    mapText.setText("Map " + App.mapLevel);
    if(isCampaignOrEndless()){
      modeText.setText("Campaign mode");
    }else{
      modeText.setText("Endless mode");
    }


  }

  public void goBack() throws IOException {
    if (isCampaignOrEndless()) {
      App.setRoot("/scenes/campaignScene");
    } else {
      App.setRoot("/scenes/mainMenu");
    }
    Sound.playInGameSound(6);
  }

  public void goPreviousCharacter() {
    if (currentIndex < csm.getCurrentLength()) {
      currentIndex += csm.getCurrentLength() * 100;
    }
    currentIndex--;
    applyToScene();
    Sound.playInGameSound(6);
  }

  public void goNextCharacter() {
    currentIndex++;
    applyToScene();
    Sound.playInGameSound(6);
  }

  public void applyToScene(){
    previousCharacterImage.setImage(
        csm.getImageFromIndex((currentIndex - 1) % csm.getCurrentLength()));
    currentCharacterImage.setImage(csm.getImageFromIndex(currentIndex % csm.getCurrentLength()));
    nextCharacterImage.setImage(csm.getImageFromIndex((currentIndex + 1) % csm.getCurrentLength()));
    showCharacterName.setText(csm.getTextFromIndex(currentIndex % csm.getCurrentLength())+" character");

  }

  public static void setCampaignOrEndless(boolean newValue) {

    campaignOrEndless = newValue;
  }

  public static boolean isCampaignOrEndless() {
    return campaignOrEndless;
  }

  public Image getImage(String url) {
    Image ss = new Image(getClass().getResourceAsStream(url));
    return ss;
  }

  public void switchToMainGame() throws IOException {
    currentIndex = currentIndex % csm.getCurrentLength();

    Board.setPlayerNumber(currentIndex+1);
    App.toMainGame();
    Sound.playInGameSound(6);
  }

}
