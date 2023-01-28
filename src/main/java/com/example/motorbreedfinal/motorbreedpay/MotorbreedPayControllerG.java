package com.example.motorbreedfinal.motorbreedpay;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MotorbreedPayControllerG{

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

    private String firstname;
    private String lastname;
    private float expense;

    public MotorbreedPayControllerG(String firstName, String lastName, float expense){
        firstname = firstName;
        lastname = lastName;
        this.expense = expense;
    }

    @FXML
    public void initialize(){
        tfFirstName.setText(firstname);
        tfLastName.setText(lastname);
        lblPrice.setText("Payment: "+ expense +"€");
    }

    @FXML
    public void close(ActionEvent event){
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }



}

