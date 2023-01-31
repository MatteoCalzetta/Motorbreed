package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.ManageAdsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    ManageAdsController manageAdsController;

    FXMLLoader fxmlLoader;

    @FXML
    void analizeAds(ActionEvent event) {

    }

    @FXML
    void openMyAds(ActionEvent event) {
        manageAdsController = new ManageAdsController();

        manageAdsController.retrieveMyAds();

        fxmlLoader = FxmlLoader.setPage("ModifyAdPage");
        manageAdsController.setModifyAdControllerG(fxmlLoader.getController());
        manageAdsController.showAd(0);

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
