package entity.tile;

import entity.animated.mob.Bomber;
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
