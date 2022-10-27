package entity.tile;

import entity.Entity;
import level.Coordinates;
import sprite.Screen;
import sprite.Sprite;

public abstract class Tile extends Entity {
    public Tile(int x, int y, Sprite sprite) {
        this._x = x;
        this._y = y;
        _sprite = sprite;
    }

    public Tile() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderEntity( Coordinates.tileToPixel(_x), Coordinates.tileToPixel(_y), this);
    }
}
