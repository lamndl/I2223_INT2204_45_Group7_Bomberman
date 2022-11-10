package scenes;

import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mainClass.App;
import mainClass.Sound;

public class HowToPlayScene {

  public ImageView imageViewHowToPlay = new ImageView();

  public Image introductionImage =
      new Image(App.class.getResource("/Pictures/HowToPlayScene/Introduction.png").toString());
  public Image charactersImage =
      new Image(App.class.getResource("/Pictures/HowToPlayScene/Character.png").toString());
  public Image enemiesImage =
      new Image(App.class.getResource("/Pictures/HowToPlayScene/Enemies.png").toString());
  public Image keyboardImage =
      new Image(App.class.getResource("/Pictures/HowToPlayScene/Keyboard-new.png").toString());

  public void switchToIntroductionTab() throws IOException {
    imageViewHowToPlay.setImage(introductionImage);
    Sound.playInGameSound(6);
  }

  public void switchToCharactersTab() throws IOException {
    imageViewHowToPlay.setImage(charactersImage);
    Sound.playInGameSound(6);
  }

  public void switchToEnemiesTab() throws IOException {
    imageViewHowToPlay.setImage(enemiesImage);
    Sound.playInGameSound(6);
  }

  public void switchToKeyboardTab() throws IOException {
    imageViewHowToPlay.setImage(keyboardImage);
    Sound.playInGameSound(6);
  }

  public void backToMainMenu() throws IOException {
    App.setRoot("/scenes/mainMenu");
    Sound.playInGameSound(6);
  }
}
