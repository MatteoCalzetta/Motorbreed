package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.ResearchController;
import com.example.motorbreedfinal.model.exceptions.FailedResearchException;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.ResearchBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class BuyerHomepageControllerG {

    @FXML
    private Button buyCarbtn;

    @FXML
    private Label labelLoggedUser;

    @FXML
    private Button myProfilebtn;

    @FXML
    private TextField nameSurnameTF;

    @FXML
    private Button btnAdvancedRsrch;

    @FXML
    private Button btnBuycar;

    @FXML
    private Button btnResearch;
    @FXML
    private Pane paneResearch;

    @FXML
    private TextField tfMaxMileage;

    @FXML
    private TextField tfMaxPrice;

    @FXML
    private TextField tfMinMileage;

    @FXML
    private TextField tfMinPrice;
    @FXML
    private TextField tfBrand;

    @FXML
    private ImageView btnX;

    @FXML
    private ImageView btnX2;

    @FXML
    private Label lblErrorResearch;

    @FXML
    private Pane paneResearch1;


    @FXML
    void buyCar(ActionEvent event) {
        paneResearch.setVisible(true);
    }

    @FXML
    void searchCar(ActionEvent event){
        System.out.println(tfMinPrice.getText());
        ResearchBean researchBean = new ResearchBean(tfBrand.getText(), tfMinMileage.getText(), tfMaxMileage.getText(),
                tfMinPrice.getText(), tfMaxPrice.getText());

        System.out.println(researchBean.getStartingPrice());

        if(researchBean.isDataValid()){
            AdBean adBean = null;
            try {
                adBean = ResearchController.getInstance().baseResearch(researchBean);
                if(!adBean.getAds().isEmpty()){
                    setResultsPage(adBean);
                } else{
                    paneResearch1.setVisible(true);
                    lblErrorResearch.setText("No results found.");
                }
            } catch (FailedResearchException e) {
                paneResearch1.setVisible(true);
                lblErrorResearch.setText(e.getMessage());
            }

        } else{
            paneResearch1.setVisible(true);
            lblErrorResearch.setText("Woops. Looks like the inserted data are not valid. Insert again.");
        }


    }

    private void setResultsPage(AdBean adBean) {
        FXMLLoader loader = FxmlLoader.setPage("ResultsPage");
        ResultsPageControllerG resultsPageControllerG = loader.getController();
        resultsPageControllerG.setAd(adBean);
    }


    public void setNameSurnameTF(String firstName, String lastName) {
        nameSurnameTF.setText("Welcome " + firstName+ " "+ lastName);
    }

    @FXML
    void setAdvancedResearch(ActionEvent event) {
        FxmlLoader.setPage("AdvancedResearchPage");
    }

    @FXML
    void closePane(MouseEvent event) {
        paneResearch.setVisible(false);
        paneResearch1.setVisible(false);
    }

    @FXML
    void closeErrorPane(MouseEvent event){
        paneResearch1.setVisible(false);
    }

    @FXML
    void setMyprofile(ActionEvent event) {
        FxmlLoader.setPage("BuyerUserPage");
    }

}
