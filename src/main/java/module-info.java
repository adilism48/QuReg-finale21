module com.qureg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.qureg to javafx.fxml;
    exports com.qureg;
}