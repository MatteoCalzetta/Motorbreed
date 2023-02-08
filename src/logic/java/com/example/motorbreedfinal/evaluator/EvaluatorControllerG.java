package com.example.motorbreedfinal.evaluator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EvaluatorControllerG {

    @FXML
    private TextField brandTF;

    @FXML
    private TextField evaluationTF;

    @FXML
    private TextField fuelTypeTF;

    @FXML
    private TextField horsePowerTF;

    @FXML
    private TextField mileageTF;

    @FXML
    private TextField modelTF;

    @FXML
    private TextField prYearTF;

    @FXML
    private Button usePriceButton;

    private EvaluatorController evaluatorController;

    private String evaluatedPrice;

    @FXML
    void usePrice(ActionEvent event) {
        evaluatorController.setPriceOnScreen(evaluatedPrice);
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close(); // chiude automaticamente la scene
    }

    public void setData(EvaluatorBean evaluatorBean) {
        this.brandTF.setText(evaluatorBean.getBrand());
        this.fuelTypeTF.setText(evaluatorBean.getFuelType());
        this.modelTF.setText(evaluatorBean.getModel());
        this.horsePowerTF.setText(evaluatorBean.getHorsepower());
        this.mileageTF.setText(evaluatorBean.getMileage());
        this.prYearTF.setText(evaluatorBean.getProductionYear());

        if(evaluatorBean.getFinalPrice().equals("Price was not Evaluable")){
            usePriceButton.setDisable(true);
        }else{
            evaluatedPrice = evaluatorBean.getFinalPrice();
        }

        this.evaluationTF.setText(evaluatorBean.getFinalPrice());
    }

    public void setEvaluatorController(EvaluatorController evaluatorController){
        this.evaluatorController = evaluatorController;
    }
}
