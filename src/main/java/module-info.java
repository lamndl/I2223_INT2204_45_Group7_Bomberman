module mainClass {
    requires javafx.controls;
    requires javafx.fxml;
  requires java.desktop;
requires javafx.base;
requires javafx.graphics;
requires javafx.media;
requires com.opencsv;
  opens mainClass to javafx.fxml;
  opens scenes to javafx.fxml;
  exports mainClass;
    exports scenes;



}
