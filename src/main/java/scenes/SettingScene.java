package scenes;

import java.io.IOException;
import java.util.ArrayList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import mainClass.App;
import mainClass.Keyboard;

public class SettingScene {

  public Button backButton = new Button();

  public Slider soundSlider = new Slider();
  public Text testChangeText = new Text(); // must create label first, hence it's failed

  public Button moveUpButton = new Button();

  public Button moveDownButton = new Button();

  public Button moveLeftButton = new Button();

  public Button moveRightButton = new Button();

  public Button bombPlacementButton = new Button();

  public Button inGameMenuButton = new Button();

  /// public KeyEvent moveUpKey = new KeyEvent();

  public Text statusText = new Text();

  public Keyboard KB = new Keyboard(); // have to refactor later to use in-game
  private double soundValue; // have to refactor later to use in-game
  // first show that status is changing
  // then show finished after changed
  // then disappear after 3 seconds
  // remember the method taskname.cancel();
  private ArrayList<Integer> statusKeyEvent = new ArrayList<Integer>();



  Task<Void> diNgu = new Task<Void>() {
    @Override
    protected Void call() throws Exception {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {

      }
      return null;
    }
  };

  public void initialize() {
    soundValue = soundSlider.getValue();
    statusKeyEvent.add(0);// up
    statusKeyEvent.add(0);// down
    statusKeyEvent.add(0);// left
    statusKeyEvent.add(0);// right
    statusKeyEvent.add(0);// place bomb
    statusKeyEvent.add(0);// in game menu
    statusText.setText("");
    KB.printKeyCodeStatus();
    setTextForButtons();

    diNgu.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
      @Override
      public void handle(WorkerStateEvent workerStateEvent) {
        statusText.setText("");
      }
    });
  }



  public double getSoundValue() {
    soundValue = soundSlider.getValue();
    return this.soundValue;
  }

  public void backToMainMenu() throws IOException {
    App.setRoot("/scenes/mainMenu");
  }

  public void changeSound() throws IOException {
    System.out.println("Sound value: " + getSoundValue());
  }

  public void changeMoveUp() throws IOException {
    diNgu.cancel();
    statusText.setText("Changing the move up button...");
    statusKeyEvent.set(0, 1);
    if (statusKeyEvent.get(0) == 1) {
      moveUpButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
          KeyCode temp = KB.getMoveUp();
          KB.setMoveUp(event.getCode());
          if (KB.validation()) {
            cancelAllChangeButton(true);
          } else {
            KB.setMoveUp(temp);
            cancelAllChangeButton(false);
          }

          statusKeyEvent.set(0, 0);
          // KB.printKeyCodeStatus();
          setTextForButtons();
          // new Thread(diNgu).start();
        }

      });
    }

  }

  public void changeMoveDown() throws IOException {
    diNgu.cancel();
    statusText.setText("Changing the move down button...");
    statusKeyEvent.set(1, 1);
    if (statusKeyEvent.get(1) == 1) {
      moveDownButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
          KeyCode temp = KB.getMoveDown();
          KB.setMoveDown(event.getCode());
          if (KB.validation()) {
            cancelAllChangeButton(true);
          } else {
            KB.setMoveDown(temp);
            cancelAllChangeButton(false);
          }
          statusKeyEvent.set(1, 0);
          // KB.printKeyCodeStatus();
          setTextForButtons();

        }

      });
    }
  }

  public void changeMoveLeft() throws IOException {
    diNgu.cancel();
    statusText.setText("Changing the move left button...");
    statusKeyEvent.set(2, 1);
    if (statusKeyEvent.get(2) == 1) {
      moveLeftButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
          KeyCode temp = KB.getMoveLeft();
          KB.setMoveLeft(event.getCode());
          if (KB.validation()) {
            cancelAllChangeButton(true);
          } else {
            KB.setMoveLeft(temp);
            cancelAllChangeButton(false);
          }
          statusKeyEvent.set(2, 0);
          // KB.printKeyCodeStatus();
          setTextForButtons();

        }

      });
    }
  }

  public void changeMoveRight() throws IOException {
    diNgu.cancel();
    statusText.setText("Changing the move right button...");
    statusKeyEvent.set(3, 1);
    if (statusKeyEvent.get(3) == 1) {
      moveRightButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
          KeyCode temp = KB.getMoveRight();
          KB.setMoveRight(event.getCode());
          if (KB.validation()) {
            cancelAllChangeButton(true);
          } else {
            KB.setMoveRight(temp);
            cancelAllChangeButton(false);
          }
          statusKeyEvent.set(3, 0);
          // KB.printKeyCodeStatus();
          setTextForButtons();

        }

      });
    }
  }

  public void changeBombPlacement() throws IOException {
    diNgu.cancel();
    statusText.setText("Changing the bomb placement button...");
    statusKeyEvent.set(4, 1);
    if (statusKeyEvent.get(4) == 1) {
      bombPlacementButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
          KeyCode temp = KB.getBombPlacement();
          KB.setBombPlacement(event.getCode());
          if (KB.validation()) {
            cancelAllChangeButton(true);
          } else {
            KB.setBombPlacement(temp);
            cancelAllChangeButton(false);
          }
          statusKeyEvent.set(4, 0);
          // KB.printKeyCodeStatus();
          setTextForButtons();

        }

      });
    }
  }

  public void changeInGameMenu() throws IOException {
    diNgu.cancel();
    statusText.setText("Changing the in game menu button...");
    statusKeyEvent.set(5, 1);
    if (statusKeyEvent.get(5) == 1) {
      inGameMenuButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
          KeyCode temp = KB.getInGameMenu();
          KB.setInGameMenu(event.getCode());
          if (KB.validation()) {
            cancelAllChangeButton(true);
          } else {
            KB.setInGameMenu(temp);
            cancelAllChangeButton(false);
          }
          statusKeyEvent.set(5, 0);
          // KB.printKeyCodeStatus();
          setTextForButtons();

        }

      });
    }
  }

  public void cancelAllChangeButton(Boolean allowed) {
    moveUpButton.setOnKeyPressed(null);
    moveDownButton.setOnKeyPressed(null);
    moveLeftButton.setOnKeyPressed(null);
    moveDownButton.setOnKeyPressed(null);
    bombPlacementButton.setOnKeyPressed(null);

    // need to disappear after 3 seconds
    if (allowed) {
      statusText.setText("Done!");
    } else {
      statusText.setText("Unsuccessful. Please try again.");
    }


  }

  public void setTextForButtons() {
    moveUpButton.setText(KB.getMoveUp().toString());
    moveDownButton.setText(KB.getMoveDown().toString());
    moveLeftButton.setText(KB.getMoveLeft().toString());
    moveRightButton.setText(KB.getMoveRight().toString());
    bombPlacementButton.setText(KB.getBombPlacement().toString());

  }
}// end of class
