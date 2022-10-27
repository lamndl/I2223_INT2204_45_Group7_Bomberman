package entity.tile;

import level.Coordinates;
import sprite.Screen;
import sprite.Sprite;

public class Brick extends Tile{

    public Brick(int x, int y, Sprite brick) {
        super();
    }

    @Override
    public void render(Screen screen) {
        int x = Coordinates.tileToPixel(_x);
        int y = Coordinates.tileToPixel(_y);

//        if(_destroyed) {
//            _sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);
//
//            screen.renderEntityWithBelowSprite(x, y, this, _belowSprite);
//        }
//        else
            screen.renderEntity( x, y, this);
    }
    @Override
    public void update() {

    }
}
