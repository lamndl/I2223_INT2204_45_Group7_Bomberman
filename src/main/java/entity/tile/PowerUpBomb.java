package entity.tile;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import sprite.Sprite;

public class PowerUpBomb extends Tile{
  public PowerUpBomb(int x, int y){
    super(x,y);
  }

  public void update(){

  }

  public Image getImage(){
    return Sprite.powerup_bombs;
  }

  public BoundingBox getBoundingBox() {
    return new BoundingBox(x + 2, y + 2, 28, 28);
  }

}
