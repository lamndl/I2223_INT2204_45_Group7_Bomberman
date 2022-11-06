package entity.tile;

import entity.Entity;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import mainClass.Board;
import sprite.Sprite;

public class Portal extends Tile {

  public Portal(int x, int y) {
    super(x, y);
  }

  @Override
  public Image getImage() {
    return Sprite.portal;
  }

  @Override
  public void update() {

  }

  public void whenReceived(){
    Board.goInGamePane(0);
  }

  @Override
  public boolean isCollidedWith(Entity other) {
    return true;
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(0, 0, 0, 0);
  }

}
