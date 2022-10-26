package scenes;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import mainClass.App;


public class CharacterChoosing {

  public Button backButton = new Button();
  public Text showCharacterName = new Text();
  public Button prevButton = new Button();
  public Button nextButton = new Button();
  public ImageView previousCharacterImage = new ImageView();
  public ImageView currentCharacterImage = new ImageView();
  public ImageView nextCharacterImage = new ImageView();



  public void initialize(){
    previousCharacterImage.setImage(getImage("/Pictures/testTransparency.png"));
    currentCharacterImage.setImage(getImage("/Pictures/testTransparency.png"));
    nextCharacterImage.setImage(getImage("/Pictures/testTransparency.png"));
  }
  public void goBack() throws IOException {
    if(App.isCampaignOrEndless()){
      App.setRoot("/scenes/campaignScene");
    }else{
      App.setRoot("/scenes/mainMenu");
    }

  }

  public void goPreviousCharacter() {

  }

  public void goNextCharacter() {

  }



  public Image getImage(String url){
    Image ss = new Image(getClass().getResourceAsStream(url));
    return ss;
  }

}
