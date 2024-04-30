module com.greenone.smartcalc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.greenone.smartcalc to javafx.fxml;
    exports com.greenone.smartcalc;
}
