package entity.tile;

import entity.Entity;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import sprite.Sprite;

public class Overlay extends Entity {

  public Overlay(int x, int y) {
    super(x, y);
    //TODO Auto-generated constructor stub
  }

  @Override
  public void update() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Image getImage() {
    // TODO Auto-generated method stub
    return Sprite.explosion_horizontal;
  }

  @Override
  public BoundingBox getBoundingBox() {
    // TODO Auto-generated method stub
    return null;
  }
  
}
