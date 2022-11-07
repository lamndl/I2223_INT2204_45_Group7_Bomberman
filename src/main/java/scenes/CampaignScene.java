package scenes;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import mainClass.App;

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
//    sc.valueProperty().addListener(new ChangeListener<Number>() {
//      public void changed(ObservableValue<? extends Number> ov,
//          Number old_val, Number new_val) {
//        vb.setLayoutY(-new_val.doubleValue());
//      }
//    });

    mapView.setImage(getImageSample());

  }

  public void goMainMenu() throws IOException {
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
    App.setRoot("/scenes/characterChoosing");
  }

}
