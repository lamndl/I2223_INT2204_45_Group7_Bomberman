package scenes;

import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import mainClass.App;
import mainClass.Sound;

public class CampaignScene {

  public VBox vb = new VBox();
  public Button goButton = new Button();
  public Button backButton = new Button();
  public ImageView mapView = new ImageView();
  public ScrollBar sc = new ScrollBar();
  public Text mapChoosingNotification = new Text();

  public void initialize() {
    for (int i = 0; i < 5; i++) {
      final Image image = new Image(
          getClass().getResourceAsStream("/Pictures/CampaignScene/MapChoosingTesting.png"));
      final ImageView pic = new ImageView(image);
      vb.getChildren().add(pic);
    }

    mapView.setImage(getImageSample());

  }

  public void goMainMenu() throws IOException {
    Sound.playInGameSound(6);
    App.setRoot("/scenes/mainMenu");
  }

  public Image getImageSample(){
    Image ss = new Image(getClass().getResourceAsStream("/Pictures/MapImage/MapSample.png"));
    return ss;
  }

  public void changeMapNotification(String text){
    mapChoosingNotification.setText(text);
  }

  public void goChoosingCharacterScene() throws IOException{
    Sound.playInGameSound(6);
    App.setRoot("/scenes/characterChoosing");

  }

}
