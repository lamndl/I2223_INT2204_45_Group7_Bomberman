package entity.tile.powerup;

import entity.animated.mob.Bomber;
import entity.tile.Grass;
import entity.tile.Tile;
import javafx.geometry.BoundingBox;
import mainClass.Board;

public abstract class PowerUp extends Tile {

  protected PowerUp(int x, int y) {
    super(x, y);
  }

  protected abstract void effect(Bomber bomber);

  public final BoundingBox getBoundingBox() {
    return new BoundingBox(x + 8, y + 8, 20, 20);
  }

  @Override
  public final void update() {
    Bomber bomber = Board.getBomber();
    if (isCollidedWith(bomber)) {
      effect(bomber);
      Board.removeEntity(this);
      Board.addEntity(new Grass(x, y));
    }
  }
}
