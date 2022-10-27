package scenes;
import java.io.IOException;
import java.util.ArrayList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import mainClass.App;

public class LoadingMenu {


  protected static ArrayList<String> helpfulTips = new ArrayList<>();
  private int currentIndex = (int)Math.round(Math.random()*10);
  public TextArea tipTextField;

  public FXMLLoader testing = new FXMLLoader(getClass().getResource("/scenes/loadingMenu.fxml"));

  public Button goButton = new Button();

  Task<Void> goButtonSleep = new Task<Void>(){
    @Override
    protected Void call() throws Exception{
      try{
        Thread.sleep(5000);
      }catch(InterruptedException e){

      }
      return null;
    }
  };

  @FXML
  private void switchToMainMenu() throws IOException {
    App.setRoot("/scenes/mainMenu");
  }

  public void initialize(){
    goButton.setText("Please wait few seconds...");
    goButton.setDisable(true);
    tipTextField.setStyle("-fx-font-size: 22");
    tipTextField.setCursor(Cursor.OPEN_HAND);
    try{
      changeTip();
    }catch (Exception e){
      System.out.println("Sth wrong when init the changetip()");
    }

    goButtonSleep.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
      @Override
      public void handle(WorkerStateEvent workerStateEvent) {
        goButton.setText("Go!");
        goButton.setDisable(false);
      }
    });

    new Thread(goButtonSleep).start();

  }


  public void changeTip() throws IOException {
    if (helpfulTips.isEmpty()) {
      helpfulTips.add("Turn off the Unikey if you wonder why your character doesn't move.");
      helpfulTips.add("Sometimes it's better to get score instead of killing enemies.");
      helpfulTips.add("Fortunately we won't randomly order the tips, so you can read in the sequence.");
      helpfulTips.add("Game too hard? It means you're a noob.");
      helpfulTips.add("Don't move the character adjacent to the bomb and you will alive.");
      helpfulTips.add("You can change the character image in main menu by clicking its image.");
      helpfulTips.add("Don't AFK. The enemies will soon chase you.");
      helpfulTips.add("If you are our teacher, please give us a full score");
      helpfulTips.add("You should buy a new keyboard if you are an aggressive person.");
      helpfulTips.add("In endless mode, there is no \"in-game menu\"!");
      helpfulTips.add("Higher number of level means harder to win.");

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
