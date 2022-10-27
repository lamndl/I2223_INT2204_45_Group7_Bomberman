package mainClass;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sprite.Screen;

import static mainClass.Board.update;

/**
 * JavaFX App.
 */
public class App extends Application {

    public static Scene scene;
    private static Stage stage;
    public static Screen screen;

    public static final int TILES_SIZE = 16,
            WIDTH = TILES_SIZE * (31 /2 ),
            HEIGHT = 13 * TILES_SIZE;


    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Image icon = new Image(App.class.getResource("/Pictures/icon.png").toString());
        scene = new Scene(loadFxml("/scenes/loadingMenu"), 1024, 768);
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.show();

    }

    public static void toMainGame() {
        Board board = new Board();
        board.init();
        Scene newScene = new Scene(board.getRoot());
        stage.setScene(newScene);
        stage.centerOnScreen();
    }


    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFxml(fxml));

    }

    private static Parent loadFxml(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
//    private void renderGame() {
//        BufferStrategy bs = getBufferStrategy();
//        if(bs == null) {
//            createBufferStrategy(3);
//            return;
//        }
//
//        screen.clear();
//
//        _board.render(screen);
//
//        for (int i = 0; i < pixels.length; i++) {
//            pixels[i] = screen._pixels[i];
//        }
//
//        Graphics g = bs.getDrawGraphics();
//
//        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
//        _board.renderMessages(g);
//
//        g.dispose();
//        bs.show();
//    }
//
//    private void renderScreen() {
//        BufferStrategy bs = getBufferStrategy();
//        if(bs == null) {
//            createBufferStrategy(3);
//            return;
//        }
//
//        screen.clear();
//
//        Graphics g = bs.getDrawGraphics();
//
//        _board.drawScreen(g);
//
//        g.dispose();
//        bs.show();
//    }

    public static void main(String[] args) {
        launch();
    }

}
