module uet.hal {
    requires javafx.controls;
    requires javafx.fxml;
  requires java.desktop;

  opens uet.hal to javafx.fxml;
    exports uet.hal;
}
