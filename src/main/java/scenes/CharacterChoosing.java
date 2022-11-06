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

  public void initialize() {
    csm = new CharacterSceneManagement();
    currentIndex = csm.getCurrentLength() * 100;
    applyToScene();
  }

  public void goBack() throws IOException {
    if (isCampaignOrEndless()) {
      App.setRoot("/scenes/campaignScene");
    } else {
      App.setRoot("/scenes/mainMenu");
    }

  }

  public void goPreviousCharacter() {
    if (currentIndex < csm.getCurrentLength()) {
      currentIndex += csm.getCurrentLength() * 100;
    }
    currentIndex--;
    applyToScene();
    System.out.println(currentIndex);
  }

  public void goNextCharacter() {
    currentIndex++;
    applyToScene();
    System.out.println(currentIndex);
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
//    if(csm.getTextFromIndex(currentIndex% csm.getCurrentLength()).equals("Green")){
//      Board.setPlayerNumber(2);
//    }else if(csm.getTextFromIndex(currentIndex% csm.getCurrentLength()).equals("Blue")){
//      Board.setPlayerNumber(1);
//    }else{
//      Board.setPlayerNumber(3);
//    }
    System.out.println(currentIndex+1);
    Board.setPlayerNumber(currentIndex+1);
    App.toMainGame(Board.getBoardLevel());
    //todo: Change above code, so right level can be chosen.

  }

}
