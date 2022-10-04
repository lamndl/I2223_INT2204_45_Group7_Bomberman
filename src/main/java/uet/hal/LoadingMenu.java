package uet.hal;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class LoadingMenu {


  protected static ArrayList<String> helpfulTips = new ArrayList<String>(100);
  private int currentIndex = 0;
  public TextField tipTextField;

  public FXMLLoader testing = new FXMLLoader(getClass().getResource("loadingMenu.fxml"));


  @FXML
  private void switchToMainMenu() throws IOException {
    App.setRoot("mainMenu");
  }

  public void changeTip() throws IOException {
    if (helpfulTips.isEmpty()) {
      helpfulTips.add("Turn off the Unikey if you wonder why your character doesn't move.");
      helpfulTips.add("Sometimes it's better to get score instead of killing enemies.");
      helpfulTips.add("Fortunately we won't randomly order the tips, so you can read in the sequence.");
      helpfulTips.add("Game too hard? It means you're a noob.");
      helpfulTips.add("Don't move the character adjacent to the bomb and you will alive.");
      helpfulTips.add("You can change the character image in main menu by clicking its image.");
      changeTip();
    } else {
      currentIndex++;
      if (currentIndex == helpfulTips.size()) {
        currentIndex -= helpfulTips.size();
      }
      tipTextField.setText(helpfulTips.get(currentIndex));
    }
  }


}
