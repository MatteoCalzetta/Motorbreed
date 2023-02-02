package com.example.motorbreedfinal.view1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class  FxmlLoader extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(FxmlLoader.class.getResource("Homepage.fxml"));
        Scene scene = new Scene(fxmlloader.load(), 1280, 720);
        stage = primaryStage;
        stage.setTitle("MotorBreed");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public static FXMLLoader setPage(String fileName) {

        URL fileUrl = FxmlLoader.class.getResource(fileName + ".fxml");
        FXMLLoader loader = new FXMLLoader(fileUrl);
        assert fileUrl != null;
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root, 1280, 720);
            stage.setScene(scene);
            return loader;
        } catch(IOException e){
            //to handle
        }
        return loader;
    }
    public static void main(String[] args) {
        launch();
    }
}


