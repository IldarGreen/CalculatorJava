module com.greenone.installer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.greenone.installer to javafx.fxml;
    exports com.greenone.installer;
}