module ContactListAppJavaFx {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.xml;

    opens sample;
    opens sample.dataModel to javafx.base;
}