package com.example.motorbreedfinal.view1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SellerHomepageControllerG {

    @FXML
    private Button logoutButton;


    @FXML
    private Button insertAdbtn;

    @FXML
    private Button myProfilebtn;

    @FXML
    private TextField nameSurnameTF;

    public void setNameSurnameTF(String firstName, String lastName) {
        nameSurnameTF.setText("Welcome " + firstName+ " "+ lastName);
    }

    @FXML
    void openSellerProfile(ActionEvent event) {
        FxmlLoader.setPage("SellerUserPage");
    }

    @FXML
    void insertAd(ActionEvent event) {
        FxmlLoader.setPage("InsertPage");
    }

    @FXML
    void logout(ActionEvent event) {
        FxmlLoader.setPage("Homepage");
    }
}
