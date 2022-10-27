package mainClass;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
public class Sound {
  private static Media buzzer;
  private static MediaPlayer mediaPlayer;
  public Sound(){
    buzzer = new Media(getClass().getResource("/audio/backgroundAudio.mp3").toExternalForm());
    mediaPlayer=new MediaPlayer(buzzer);
  }

  public static void playMedia(){
    mediaPlayer.play();

  }

  public static Media getMedia(){
    return buzzer;
  }

  public static MediaPlayer getMediaPlayer(){
    return mediaPlayer;
  }

}
