package mainClass;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {

  private KeyCode moveUp = KeyCode.W;
  private KeyCode moveDown = KeyCode.S;
  private KeyCode moveLeft = KeyCode.A;
  private KeyCode moveRight = KeyCode.D;
  private KeyCode bombPlacement = KeyCode.SPACE;

  private KeyCode inGameMenu = KeyCode.ESCAPE;

  public KeyCode getMoveUp() {
    return moveUp;
  }

  public KeyCode getInGameMenu() {
    return inGameMenu;
  }

  public void setInGameMenu(KeyCode inGameMenu) {
    this.inGameMenu = inGameMenu;
  }

  public void setMoveUp(KeyCode moveUp) {
    this.moveUp = moveUp;
  }

  public KeyCode getMoveDown() {
    return moveDown;
  }

  public void setMoveDown(KeyCode moveDown) {
    this.moveDown = moveDown;
  }

  public KeyCode getMoveLeft() {
    return moveLeft;
  }

  public void setMoveLeft(KeyCode moveLeft) {
    this.moveLeft = moveLeft;
  }

  public KeyCode getMoveRight() {
    return moveRight;
  }

  public void setMoveRight(KeyCode moveRight) {
    this.moveRight = moveRight;
  }

  public KeyCode getBombPlacement() {
    return bombPlacement;
  }

  public void setBombPlacement(KeyCode bombPlacement) {
    this.bombPlacement = bombPlacement;
  }

  public Boolean validation(){
    ArrayList<String> arr = new ArrayList<String>();
    arr.add(getMoveUp().toString());
    arr.add(getBombPlacement().toString());
    arr.add(getInGameMenu().toString());
    arr.add(getMoveLeft().toString());
    arr.add(getMoveRight().toString());
    arr.add(getMoveDown().toString());
    for(int i = 0; i < arr.size()-1;i++){
      for(int j = i+1;j<arr.size();j++){
        if(arr.get(i).equals(arr.get(j))){
          return false;
        }
      }
    }
    return true;
  }
  public void printKeyCodeStatus(){
    System.out.println(this.getMoveUp() + " " + this.getMoveDown() + " "+ this.getMoveLeft() + " " + this.getMoveRight() + " " + this.getBombPlacement() + " " + this.getInGameMenu());

  }
}
