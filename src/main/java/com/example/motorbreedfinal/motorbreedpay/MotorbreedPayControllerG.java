package com.example.motorbreedfinal.motorbreedpay;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MotorbreedPayControllerG {

    @FXML
    private Button btnBuy;

    @FXML
    private Label lblPrice;

    @FXML
    private TextField tfCard;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblCardError;
    
    @FXML
    public void buyNow(ActionEvent event){
        String idAd = lblOrderId.getText().substring(29, lblOrderId.getText().length());
        if(tfCard.getText().length() > 11){
            MotorbreedPayController motorbreedPayController = new MotorbreedPayController();
            motorbreedPayController.isPaymentValid(idAd);
        } else {
            lblCardError.setVisible(true);
        }
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void enableDisableError(KeyEvent event) {
        lblCardError.setVisible(false);
    }


    @FXML
    public void close(ActionEvent event){
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }


    public void setData(String firstName, String lastName, float expense, String idAd) {
        tfFirstName.setText(firstName);
        tfLastName.setText(lastName);
        lblPrice.setText("Payment: "+ expense +"€");
        lblOrderId.setText(lblOrderId.getText() + idAd);
    }
}

