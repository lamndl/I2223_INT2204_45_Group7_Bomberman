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

  /**
   * Media of background sound
   */
  private static ArrayList<Media> backgroundSoundList = new ArrayList<Media>();

  /**
   * MediaPlayer for background sound
   */
  private static ArrayList<MediaPlayer> backgroundSoundMediaPlayer = new ArrayList<MediaPlayer>();

  /**
   * -1 mean no backgroundSound selected
   */
  private static int currentIndexBackgroundSound;
  /**
   * idea: Keep track of sound in-game, store by ArrayList to easily "throw" when needed
   * not sure if these below arrayList should change to static or not (static right now)
   */
  private static ArrayList<Media> soundList = new ArrayList<Media>();
  private static ArrayList<MediaPlayer> mediaPlayers = new ArrayList<MediaPlayer>();

  /**
   * constructor. HAVE TO CALLED TO INIT BELOW IMPORTANT FUNCTIONS.
   * welcome to change if there's a better way.
   */
  public Sound(){
    importBackgroundSound();
    currentIndexBackgroundSound=-1;
  }

  /**
   * import sound from resources folder
   */
  public void importBackgroundSound(){
    //media
    backgroundSoundList.add(new Media(getClass().getResource("/audio/backgroundAudio1.mp3").toExternalForm()));
    backgroundSoundList.add(new Media(getClass().getResource("/audio/backgroundAudio2.mp3").toExternalForm()));
    backgroundSoundList.add(new Media(getClass().getResource("/audio/backgroundAudio3.mp3").toExternalForm()));
    //mediaPlayer
    for(Media m: backgroundSoundList) {
      backgroundSoundMediaPlayer.add(new MediaPlayer(m));
    }

  }

  /**
   * start the sound
   * @param index to choose soundtrack
   */
  public static void playMedia(int index){
    backgroundSoundMediaPlayer.get(index).play();
    currentIndexBackgroundSound=index;
  }


  public static Media getCurrentPlayingSound(){
    return backgroundSoundMediaPlayer.get(currentIndexBackgroundSound).getMedia();
  }

  public static Media getBackgroundSoundMedia(int index){
    if(index>=backgroundSoundList.size()){
      return null;
    }else{
      return backgroundSoundList.get(index);
    }

  }

  public static ArrayList<Media> getBackgroundSoundList(){
    return backgroundSoundList;
  }

  public static MediaPlayer getBackgroundSoundMediaPlayer(int index){
    if(index>=backgroundSoundList.size()){
      return null;
    }else{
      return backgroundSoundMediaPlayer.get(index);
    }

  }

  /**
   * stop all background sound. Game must be quited. No MediaPlayer running.
   */
  public static void stopBackgroundSound(){
    for(MediaPlayer mp: backgroundSoundMediaPlayer){
      mp.stop();
    }
    currentIndexBackgroundSound=-1;
  }

  /**
   * use to mute sound, but MediaPlayer is still running.
   */
  public static void toggleMuteBackgroundSound(boolean bool){
    for(MediaPlayer mp: backgroundSoundMediaPlayer){
      mp.setMute(bool);
    }
  }

  /**
   * change how loud it is
   * @param volumeStandardized is in [0.0,1.0]. 1.0 means loudest.
   */
  public static void setBackgroundSoundVolume(double volumeStandardized){
    for(MediaPlayer mp: backgroundSoundMediaPlayer){
      mp.setVolume(volumeStandardized);
    }
  }



}
