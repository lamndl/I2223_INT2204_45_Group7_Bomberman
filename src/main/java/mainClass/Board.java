package mainClass;

import entity.Entity;
import entity.animated.Bomb;
import entity.animated.mob.Bomber;
import entity.animated.mob.Enemy;
import entity.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import exceptions.LoadLevelException;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import level.FileLevelLoader;
import level.LevelLoader;
import sprite.Screen;


public class Board {
    private static Group root;
    private static Canvas canvas;
    private static GraphicsContext graphicsContext;
    private static int height;
    private static int width;
    public static Entity[] _entities;
    protected LevelLoader _levelLoader;
    private static List<Entity> tileList = new ArrayList<>();
    private static List<Entity> bombList = new ArrayList<>();
    private static List<Entity> enemyList = new ArrayList<>();
    private static Bomber bomber;

    public static Group getRoot() {
        return root;
    }

    public void init() {
        root = new Group();
        height = App.HEIGHT;
        width = App.WIDTH;
        canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.GREEN);
        bomber = new Bomber();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                render(App.screen);

            }
        };
        timer.start();
    }

    public static void addEntity(Entity entity) {
        // Design pattern ???
        if (entity instanceof Tile) {
            tileList.add(entity);
        } else if (entity instanceof Bomb) {
            bombList.add(entity);
        } else if (entity instanceof Enemy) {
            enemyList.add(entity);
        } else {
            bomber = (Bomber) entity;
        }
    }

    public static void addEntity(int pos, Entity e) {
        _entities[pos] = e;
    }

    public static void removeEntity(Entity entity) {
        //
    }

    public void render(Screen screen) {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        bomber.draw(graphicsContext);
        int x0 = Screen.xOffset >> 4; //tile precision, -> left X
        int x1 = (Screen.xOffset + screen.getWidth() + App.TILES_SIZE) / App.TILES_SIZE; // -> right X
        int y0 = Screen.yOffset >> 4;
        int y1 = (Screen.yOffset + screen.getHeight()) / App.TILES_SIZE; //render one tile plus to fix black margins

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                _entities[x + y * _levelLoader.getWidth()].render(screen);
            }
        }

//        renderBombs(screen);
//        renderCharacter(screen);

    }

    public static void update() {
        //
    }

    public void loadLevel(int level) {
//        _time = Game.TIME;
//        _screenToShow = 2;
//        _game.resetScreenDelay();
//        _game.pause();
//        _characters.clear();
//        _bombs.clear();
//        _messages.clear();

        try {
            _levelLoader = new FileLevelLoader(this, level);
            _entities = new Entity[_levelLoader.getHeight() * _levelLoader.getWidth()];

            _levelLoader.createEntities();
        } catch (LoadLevelException e) {
            //endGame();
        }
    }
    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }
}
