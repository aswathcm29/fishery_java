module com.example.fishery {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongodb.jdbc;


    opens com.example.fishery to javafx.fxml;
    exports com.example.fishery;
}