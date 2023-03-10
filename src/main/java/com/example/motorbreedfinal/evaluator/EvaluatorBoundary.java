package com.example.motorbreedfinal.evaluator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class EvaluatorBoundary {

    public EvaluatorControllerG startEvaluator() {
        //facciamo partire la finta scene

        EvaluatorControllerG evaluatorControllerG = null;

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        URL fxmlLocation = EvaluatorBoundary.class.getResource("Evaluator.fxml");
        loader.setLocation(fxmlLocation);
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
            evaluatorControllerG = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Motorbreed");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        return evaluatorControllerG;
    }
}
