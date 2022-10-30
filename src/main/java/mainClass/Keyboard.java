package mainClass;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public class Keyboard implements KeyListener {

  private KeyCode moveUp = KeyCode.UP;
  private KeyCode moveDown = KeyCode.DOWN;
  private KeyCode moveLeft = KeyCode.LEFT;
  private KeyCode moveRight = KeyCode.RIGHT;
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

  public Boolean validation() {
    ArrayList<String> arr = new ArrayList<String>();
    arr.add(getMoveUp().toString());
    arr.add(getBombPlacement().toString());
    arr.add(getInGameMenu().toString());
    arr.add(getMoveLeft().toString());
    arr.add(getMoveRight().toString());
    arr.add(getMoveDown().toString());
    for (int i = 0; i < arr.size() - 1; i++) {
      for (int j = i + 1; j < arr.size(); j++) {
        if (arr.get(i).equals(arr.get(j))) {
          return false;
        }
      }
    }
    return true;
  }

  public void printKeyCodeStatus() {
    System.out.println(this.getMoveUp() + " " + this.getMoveDown() + " " + this.getMoveLeft() + " "
        + this.getMoveRight() + " " + this.getBombPlacement() + " " + this.getInGameMenu());

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
