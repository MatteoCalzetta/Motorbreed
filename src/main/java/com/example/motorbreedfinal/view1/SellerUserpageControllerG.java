package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.ManageAdsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SellerUserpageControllerG {
    @FXML
    private Button analizeAdsButton;

    @FXML
    private Label labelLoggedUser;

    @FXML
    private Pane errorPane;

    @FXML
    private Label errorPaneLabel;

    @FXML
    private Button modifyProfileButton;

    @FXML
    private Button myAdsButton;

    @FXML
    private Button readReviewsButton;

    ManageAdsController manageAdsController;

    FXMLLoader fxmlLoader;
    @FXML
    void openMyAds(ActionEvent event) {
        manageAdsController = new ManageAdsController();

        manageAdsController.retrieveMyAds();

        fxmlLoader = FxmlLoader.setPage("ModifyAdPage");
        manageAdsController.setModifyAdControllerG(fxmlLoader.getController());
        try {
            manageAdsController.showAd(0);
        }catch (Exception e){
            errorPaneLabel.setText("This Seller does not have any ad");
            errorPane.setDisable(false);
            errorPane.setVisible(true);
        }
    }

    @FXML
    void closeErrorPane(ActionEvent event) {
        errorPane.setDisable(true);
        errorPane.setVisible(false);
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
