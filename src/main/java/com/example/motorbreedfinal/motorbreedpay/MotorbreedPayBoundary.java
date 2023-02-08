package com.example.motorbreedfinal.motorbreedpay;

import com.example.motorbreedfinal.controller.boundary.PaymentBoundary;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MotorbreedPayBoundary implements MotorbreedPayInterface{

    @Override
    public void startTransaction(String firstName, String lastName, float expense, String idAd) {
        //facciamo partire la finta scene

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = MotorbreedPayBoundary.class.getResource("Motorbreedpay.fxml");
        loader.setLocation(fxmlLocation);


        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        MotorbreedPayControllerG motorbreedPayControllerG = loader.getController();
        motorbreedPayControllerG.setData(firstName, lastName, expense, idAd);
        stage.setTitle("MotorbreedPay");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void convalidPayment(String idAd){
        PaymentBoundary paymentBoundary = new PaymentBoundary();
        paymentBoundary.paymentIsValid(idAd);
    }

}


