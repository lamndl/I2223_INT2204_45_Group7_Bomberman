package scenes;

import static mainClass.App.KB;

import java.util.ArrayList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.chart.NumberAxis;
import javafx.scene.text.*;
import mainClass.App;
import mainClass.Keyboard;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import mainClass.Sound;



public class SettingScene {

  public Button backButton = new Button();

  public Slider soundSlider = new Slider();
  public Text testChangeText = new Text(); //must create label first, hence it's failed

  public Button moveUpButton = new Button();

  public Button moveDownButton = new Button();

  public Button moveLeftButton = new Button();

  public Button moveRightButton =new Button();

  public Button bombPlacementButton = new Button();

  public Button inGameMenuButton = new Button();
  public ComboBox backgroundSoundBox=new ComboBox<String>();
  public Button ingameSoundButton = new Button();

  ///public KeyEvent moveUpKey = new KeyEvent();

  public Text statusText= new Text();


  private double soundValue; // have to refactor later to use in-game

  private ArrayList<Integer> statusKeyEvent = new ArrayList<Integer>();
  public Button ingameAIButton = new Button();


  Task<Void> diNgu = new Task<Void>(){
    @Override
    protected Void call() throws Exception{
      try{
        Thread.sleep(3000);
      }catch(InterruptedException e){

      }
      return null;
    }
  };
  public void initialize() {

    soundSlider.adjustValue(99.0);

    soundValue= soundSlider.getValue();
    statusKeyEvent.add(0);//up
    statusKeyEvent.add(0);//down
    statusKeyEvent.add(0);//left
    statusKeyEvent.add(0);//right
    statusKeyEvent.add(0);//place bomb
    statusKeyEvent.add(0);//in game menu
    statusText.setText("");
    KB.printKeyCodeStatus();
    setTextForButtons();

    backgroundSoundBox.getItems().addAll(
        "Background 1",
        "Background 2",
        "Background 3"
    );
    ingameSoundButton.setText(String.valueOf(Sound.isIngameSound()).toUpperCase());
    ingameAIButton.setText(String.valueOf(App.toogleAI).toUpperCase());

  }



  public double getSoundValue(){
    soundValue= soundSlider.getValue();
    return this.soundValue;
  }


  public void setSoundValue(double newValue){
    soundValue=newValue;
  }
  public void backToMainMenu() throws IOException {
    App.setRoot("/scenes/mainMenu");
  }

  public void changeSound() throws IOException {

    // System.out.println("Sound value: "+ getSoundValue());
    Sound.setBackgroundSoundVolume(getSoundValue()/100.0);

  }

  public void changeMoveUp() throws IOException{
    diNgu.cancel();
    statusText.setText("Changing the move up button...");
    statusKeyEvent.set(0,1);
    if(statusKeyEvent.get(0)==1){
      moveUpButton.setOnKeyPressed(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
          KeyCode temp = KB.getMoveUp();
          KB.setMoveUp(event.getCode());
          if(KB.validation()){
            cancelAllChangeButton(true);
          }else{
            KB.setMoveUp(temp);
            cancelAllChangeButton(false);
          }

          statusKeyEvent.set(0,0);
          //KB.printKeyCodeStatus();
          setTextForButtons();
          //new Thread(diNgu).start();
        }

      });
    }

  }

  public void changeMoveDown() throws IOException{
    diNgu.cancel();
    statusText.setText("Changing the move down button...");
    statusKeyEvent.set(1,1);
    if(statusKeyEvent.get(1)==1){
      moveDownButton.setOnKeyPressed(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
          KeyCode temp = KB.getMoveDown();
          KB.setMoveDown(event.getCode());
          if(KB.validation()){
            cancelAllChangeButton(true);
          }else{
            KB.setMoveDown(temp);
            cancelAllChangeButton(false);
          }
          statusKeyEvent.set(1,0);
          //KB.printKeyCodeStatus();
          setTextForButtons();

        }

      });
    }
  }

  public void changeMoveLeft() throws IOException{
    diNgu.cancel();
    statusText.setText("Changing the move left button...");
    statusKeyEvent.set(2,1);
    if(statusKeyEvent.get(2)==1){
      moveLeftButton.setOnKeyPressed(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
          KeyCode temp = KB.getMoveLeft();
          KB.setMoveLeft(event.getCode());
          if(KB.validation()){
            cancelAllChangeButton(true);
          }else{
            KB.setMoveLeft(temp);
            cancelAllChangeButton(false);
          }
          statusKeyEvent.set(2,0);
          //KB.printKeyCodeStatus();
          setTextForButtons();

        }

      });
    }
  }

  public void changeMoveRight() throws IOException{
    diNgu.cancel();
    statusText.setText("Changing the move right button...");
    statusKeyEvent.set(3,1);
    if(statusKeyEvent.get(3)==1){
      moveRightButton.setOnKeyPressed(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
          KeyCode temp = KB.getMoveRight();
          KB.setMoveRight(event.getCode());
          if(KB.validation()){
            cancelAllChangeButton(true);
          }else{
            KB.setMoveRight(temp);
            cancelAllChangeButton(false);
          }
          statusKeyEvent.set(3,0);
          //KB.printKeyCodeStatus();
          setTextForButtons();

        }

      });
    }
  }

  public void changeBombPlacement() throws IOException{
    diNgu.cancel();
    statusText.setText("Changing the bomb placement button...");
    statusKeyEvent.set(4,1);
    if(statusKeyEvent.get(4)==1){
      bombPlacementButton.setOnKeyPressed(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
          KeyCode temp = KB.getBombPlacement();
          KB.setBombPlacement(event.getCode());
          if(KB.validation()){
            cancelAllChangeButton(true);
          }else{
            KB.setBombPlacement(temp);
            cancelAllChangeButton(false);
          }
          statusKeyEvent.set(4,0);
          //KB.printKeyCodeStatus();
          setTextForButtons();

        }

      });
    }
  }

  public void changeInGameMenu() throws IOException{
    diNgu.cancel();
    statusText.setText("Changing the in game menu button...");
    statusKeyEvent.set(5,1);
    if(statusKeyEvent.get(5)==1){
      inGameMenuButton.setOnKeyPressed(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
          KeyCode temp = KB.getInGameMenu();
          KB.setInGameMenu(event.getCode());
          if(KB.validation()){
            cancelAllChangeButton(true);
          }else{
            KB.setInGameMenu(temp);
            cancelAllChangeButton(false);
          }
          statusKeyEvent.set(5,0);
          //KB.printKeyCodeStatus();
          setTextForButtons();

        }

      });
    }
  }

  public void cancelAllChangeButton(Boolean allowed){
    moveUpButton.setOnKeyPressed(null);
    moveDownButton.setOnKeyPressed(null);
    moveLeftButton.setOnKeyPressed(null);
    moveDownButton.setOnKeyPressed(null);
    bombPlacementButton.setOnKeyPressed(null);

    //need to disappear after 3 seconds
    if(allowed){
      statusText.setText("Done!");
    }else{
      statusText.setText("Unsuccessful. Please try again.");
    }


  }

  public void setTextForButtons(){
    moveUpButton.setText(KB.getMoveUp().toString());
    moveDownButton.setText(KB.getMoveDown().toString());
    moveLeftButton.setText(KB.getMoveLeft().toString());
    moveRightButton.setText(KB.getMoveRight().toString());
    bombPlacementButton.setText(KB.getBombPlacement().toString());
    inGameMenuButton.setText(KB.getInGameMenu().toString());
    new Thread(diNgu).start();
  }

  public void changeBackgroundSound(){
    Sound.stopBackgroundSound();
    if(backgroundSoundBox.getValue().equals("Background 1")){
      Sound.setCurrentIndexBackgroundSound(0);
      Sound.playMedia(0);
    }else if(backgroundSoundBox.getValue().equals("Background 2")){
      Sound.setCurrentIndexBackgroundSound(1);
      Sound.playMedia(1);
      System.out.println("chganged");
    }else if(backgroundSoundBox.getValue().equals("Background 3")){
      Sound.setCurrentIndexBackgroundSound(2);
      Sound.playMedia(2);
    }
  }

  public void toogleIngameSound(){
    Sound.setIngameSound(!Sound.isIngameSound());
    if(Sound.isIngameSound()){
      ingameSoundButton.setText("True");
    }else{
      ingameSoundButton.setText("False");
    }
    cancelAllChangeButton(true);
  }

  public void toogleAI(){
    App.toogleAI = !App.toogleAI;
    if(App.toogleAI){
      ingameAIButton.setText("True");
    }else{
      ingameAIButton.setText("False");
    }
    cancelAllChangeButton(true);

  }
}//end of class
