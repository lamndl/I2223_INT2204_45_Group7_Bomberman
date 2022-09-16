module uet.hal {
    requires javafx.controls;
    requires javafx.fxml;

    opens uet.hal to javafx.fxml;
    exports uet.hal;
}
