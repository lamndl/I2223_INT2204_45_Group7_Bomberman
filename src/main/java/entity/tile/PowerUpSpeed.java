package entity.tile;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import sprite.Sprite;

public class PowerUpSpeed extends Tile{
  public PowerUpSpeed(int x, int y){
    super(x,y);
  }

  public void update(){

  }

  public Image getImage(){
    return Sprite.powerup_speed;
  }

  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 2, y + 2, 28, 28);
  }

}
