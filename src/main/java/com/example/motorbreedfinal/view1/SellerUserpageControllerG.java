package com.example.motorbreedfinal.view1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SellerUserpageControllerG {
    @FXML
    private Button analizeAdsButton;

    @FXML
    private Label labelLoggedUser;

    @FXML
    private Button modifyProfileButton;

    @FXML
    private Button myAdsButton;

    @FXML
    private Button readReviewsButton;

    @FXML
    void analizeAds(ActionEvent event) {

    }

    @FXML
    void openMyAds(ActionEvent event) {

    }

    @FXML
    void setCustomizeProfile(ActionEvent event) {
        FxmlLoader.setPage("InformationPage");
    }

    @FXML
    void openSellerHomepage(ActionEvent event) {
        FxmlLoader.setPage("SellerHomepage");
    }
}
