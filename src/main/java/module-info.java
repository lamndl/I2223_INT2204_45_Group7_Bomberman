module mainClass {
    requires javafx.controls;
    requires javafx.fxml;
  requires java.desktop;

  opens mainClass to javafx.fxml;
  opens scenes to javafx.fxml;
  opens experiment to javafx.fxml;
  exports mainClass;
    exports scenes;
    exports experiment;



}
