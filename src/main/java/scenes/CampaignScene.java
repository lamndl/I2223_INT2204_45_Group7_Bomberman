package scenes;

import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
  private int mapChoosing = 1;
  public ComboBox combobox = new ComboBox<>();

  public void initialize() {
    for (int i = 0; i < 5; i++) {
      final Image image = new Image(
          getClass().getResourceAsStream("/Pictures/CampaignScene/Map" + String.valueOf(i+1) + "Thumbnail.png"));
      final ImageView pic = new ImageView(image);
      vb.getChildren().add(pic);
    }

    mapView.setImage(getImageSample());
    combobox.getItems().addAll("Map 1","Map 2", "Map 3", "Map 4", "Map 5");

  }

  public int getMapChoosing() {
    return mapChoosing;
  }

  public void setMapChoosing(int mapChoosing) {
    this.mapChoosing = mapChoosing;
  }

  public void goMainMenu() throws IOException {
    Sound.playInGameSound(6);
    App.setRoot("/scenes/mainMenu");
  }

  public Image getImageSample(){
    Image ss = new Image(getClass().getResourceAsStream("/Pictures/MapImage/Map" + String.valueOf(getMapChoosing()) + ".png"));
    return ss;
  }

  public void changeMapNotification(String text){
    mapChoosingNotification.setText(text);
  }

  public void goChoosingCharacterScene() throws IOException{
    Sound.playInGameSound(6);
    App.setRoot("/scenes/characterChoosing");

  }
  public void changeMap(){
    if(combobox.getValue().equals("Map 1")){
      setMapChoosing(1);
    }else if(combobox.getValue().equals("Map 2")){
      setMapChoosing(2);
    }else if(combobox.getValue().equals("Map 3")){
      setMapChoosing(3);
    }else if(combobox.getValue().equals("Map 4")){
      setMapChoosing(4);
    }else{
      setMapChoosing(5);

    }

    mapView.setImage(getImageSample());
  }

}
