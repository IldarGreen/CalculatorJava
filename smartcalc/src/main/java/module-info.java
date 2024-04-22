module com.greenone.smartcalc {
    requires javafx.controls;
    requires javafx.fxml;

//    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.greenone.smartcalc to javafx.fxml;
    exports com.greenone.smartcalc;
}