package entity.tile;

import entity.Entity;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import sprite.Sprite;

public class Overlay extends Entity {

  public Overlay(int x, int y) {
    super(x, y);
  }

  @Override
  public void update() {
  }

  @Override
  public Image getImage() {
    return Sprite.AIDot;
  }

  @Override
  public BoundingBox getBoundingBox() {
    return null;
  }
  
}
