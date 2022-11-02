package entity.tile;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import sprite.Sprite;

public class PowerUpFlame extends Tile{

  public PowerUpFlame(int x, int y){
    super(x,y);
  }

  public void update(){

  }

  public Image getImage(){
    return Sprite.powerup_flames;
  }

  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 2, y + 2, 28, 28);
  }

}
