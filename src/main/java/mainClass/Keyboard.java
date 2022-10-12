package mainClass;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {

  private KeyCode moveUp = KeyCode.W;
  private KeyCode moveDown = KeyCode.S;
  private KeyCode moveLeft = KeyCode.A;
  private KeyCode moveRight = KeyCode.D;
  private KeyCode bombPlacement = KeyCode.SPACE;

  public KeyCode getMoveUp() {
    return moveUp;
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
  public void printKeyCodeStatus(){
    System.out.println(this.getMoveUp() + " " + this.getMoveDown() + " "+ this.getMoveLeft() + " " + this.getMoveRight() + " " + this.getBombPlacement());

  }
}
