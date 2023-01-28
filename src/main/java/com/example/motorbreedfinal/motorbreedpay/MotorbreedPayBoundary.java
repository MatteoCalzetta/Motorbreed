package com.example.motorbreedfinal.motorbreedpay;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MotorbreedPayBoundary implements MotorbreedPayInterface{

    private MotorbreedPayControllerG motorbreedPayControllerG = null;

    @Override
    public void startTransaction(String firstName, String lastName, float expense) {
        //facciamo partire la finta scene
        motorbreedPayControllerG = new MotorbreedPayControllerG(firstName,lastName, expense);

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = MotorbreedPayBoundary.class.getResource("Motorbreedpay.fxml");
        loader.setLocation(fxmlLocation);
        loader.setController(motorbreedPayControllerG);

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("MotorbreedPay");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    @Override
    public int convalidPayment(){
       return 1;
    }

}


