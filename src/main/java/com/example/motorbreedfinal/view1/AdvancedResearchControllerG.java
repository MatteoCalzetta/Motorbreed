package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.ResearchController;
import com.example.motorbreedfinal.model.users.Buyer;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.model.exceptions.FailedResearchException;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.AdvancedResearchBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdvancedResearchControllerG {

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField costFromTextField;

    @FXML
    private TextField costToTextField;

    @FXML
    private TextField fuelTypeTextField;

    @FXML
    private CheckBox heatedSeatsCheckBox;

    @FXML
    private TextField startingHPLabel;

    @FXML
    private TextField maxHPLabel;

    @FXML
    private CheckBox insuranceCheckBox;

    @FXML
    private CheckBox keylessSystemCheckBox;

    @FXML
    private CheckBox ledHeadlightsCheckBox;

    @FXML
    private TextField mileageFromTextField;

    @FXML
    private TextField mileageToTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private CheckBox parkingSensorsCheckBox;

    @FXML
    private TextField productionYearTextField;

    @FXML
    private TextField transmissionTextField;

    @FXML
    private Label lblError;

    @FXML
    void setHomepage(ActionEvent event) {
        Buyer buyer = LoggedUser.getInstance().getBuyer();
        FXMLLoader loader = FxmlLoader.setPage("BuyerHomepage");
        BuyerHomepageControllerG buyerHomepageControllerG = loader.getController();
        buyerHomepageControllerG.setNameSurnameTF(buyer.getFirstName(), buyer.getLastName());
    }

    @FXML
    void searchCar(ActionEvent event){
        lblError.setVisible(false);
        AdvancedResearchBean advancedResearchBean = new AdvancedResearchBean();
        advancedResearchBean.setData(brandTextField.getText(), modelTextField.getText(), fuelTypeTextField.getText(),
                productionYearTextField.getText(), startingHPLabel.getText(), maxHPLabel.getText(), transmissionTextField.getText());

        advancedResearchBean.setInfos(costFromTextField.getText(), costToTextField.getText(), mileageFromTextField.getText(), mileageToTextField.getText(),
                setDecorations());

        if(advancedResearchBean.isDataValid()){
            try {
                AdBean adBean = ResearchController.getInstance().advancedResearch(advancedResearchBean);
                if(!adBean.getAds().isEmpty()){
                    setResultsPage(adBean);
                } else {
                    lblError.setText("No ads found");
                    lblError.setVisible(true);
                }
            } catch (FailedResearchException e) {
                lblError.setText(e.getMessage());
                lblError.setVisible(true);
            }
        }

    }

    private String setDecorations(){
        return translate((insuranceCheckBox.isSelected())) + translate(keylessSystemCheckBox.isSelected()) + translate(heatedSeatsCheckBox.isSelected()) +
                translate(parkingSensorsCheckBox.isSelected()) + translate(ledHeadlightsCheckBox.isSelected());
    }

    public String translate(boolean condition){
        String translate;
        if(condition){
            translate = "1";
        } else{
            translate = "0";
        }
        return translate;
    }

    private void setResultsPage(AdBean adBean) {
        FXMLLoader loader = FxmlLoader.setPage("ResultsPage");
        ResultsPageControllerG resultsPageControllerG = loader.getController();
        resultsPageControllerG.setAd(adBean);
    }

}