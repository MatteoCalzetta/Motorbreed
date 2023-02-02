package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.ResearchController;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class BuyerUserPageControllerG {

    @FXML
    private Label labelLoggedUser;

    @FXML
    private Label lblError;

    @FXML
    private Pane paneError;

    @FXML
    void setCustomizeProfile(ActionEvent event) {
        FxmlLoader.setPage("InformationPage");

    }

    @FXML
    void setFavorites(ActionEvent event) {
        if (!LoggedUser.getInstance().getBuyer().getFavourites().isEmpty()) {
            FXMLLoader loader = FxmlLoader.setPage("ResultsPage");
            AdBean adBean = new AdBean();
            adBean.setAds(LoggedUser.getInstance().getBuyer().getFavourites());
            ResultsPageControllerG resultsPageControllerG = loader.getController();
            resultsPageControllerG.setAd(adBean);
        } else {
            paneError.setVisible(true);
            lblError.setText("Looks like the page you requested is empty! Close to continue");
        }
    }

        @FXML
        void setHomepage(ActionEvent event) {
        FXMLLoader loader = FxmlLoader.setPage("BuyerHomepage");
        BuyerHomepageControllerG buyerHomepageControllerG = loader.getController();
        buyerHomepageControllerG.setNameSurnameTF(LoggedUser.getInstance().getBuyer().getFirstName(),
                LoggedUser.getInstance().getBuyer().getLastName());
    }

    @FXML
    void setOrders(ActionEvent event) {
        LoggedUser.getInstance().getBuyer().setOrders(ResearchController.getInstance().getBuyerOrders());
        if (!LoggedUser.getInstance().getBuyer().getOrders().isEmpty()) {
            FXMLLoader loader = FxmlLoader.setPage("ResultsPage");
            AdBean adBean = new AdBean();
            adBean.setAds(LoggedUser.getInstance().getBuyer().getOrders());
            ResultsPageControllerG resultsPageControllerG = loader.getController();
            resultsPageControllerG.setAd(adBean);
        } else {
            paneError.setVisible(true);
            lblError.setText("Looks like the page you requested is empty! Close to continue");
        }
    }


    @FXML
    void close(ActionEvent event) {
        paneError.setVisible(false);
    }



}
