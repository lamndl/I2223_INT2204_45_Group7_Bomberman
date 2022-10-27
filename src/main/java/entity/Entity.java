package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import level.Coordinates;
import sprite.Screen;
import sprite.Sprite;

public abstract class Entity {
    protected int _x;
    protected int _y;
    protected Image image;
    protected Sprite _sprite;

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, _x, _y);
    }

    public Sprite getSprite() {
        return _sprite;
    }


    public boolean isCollidedWith(Entity other) {
        //
        return false;
    }

    /**
     * Phương thức này được gọi liên tục trong vòng lặp game,
     * mục đích để xử lý sự kiện và cập nhật trạng thái Entity
     */

    public abstract void update();

    public abstract void render(Screen screen);

    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public int getXTile() {
        return Coordinates.pixelToTile(_x + _sprite.SIZE / 2);
    }

    public int getYTile() {
        return Coordinates.pixelToTile(_y - _sprite.SIZE / 2);
    }

}
