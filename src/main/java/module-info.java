module com.example.motorbreedfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.j;
    requires org.controlsfx.controls;
    requires java.mail;


    exports com.example.motorbreedfinal.view1;
    exports com.example.motorbreedfinal.view1.fagioli;
    opens com.example.motorbreedfinal.view1 to javafx.fxml;
    opens com.example.motorbreedfinal.evaluator to javafx.fxml;
    opens com.example.motorbreedfinal.motorbreedpay to javafx.fxml;

}