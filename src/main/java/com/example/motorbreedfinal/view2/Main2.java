package com.example.motorbreedfinal.view2;


import com.example.motorbreedfinal.view2.utility.LinePrinter;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main2 extends Application {
    public static void main(String[] args){
        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginControllerG2 loginControllerG2 = new LoginControllerG2();
        loginControllerG2.getRole();
    }
}
