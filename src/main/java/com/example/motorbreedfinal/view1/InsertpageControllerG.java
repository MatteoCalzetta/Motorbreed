package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.InsertionController;
import com.example.motorbreedfinal.view1.fagioli.CarBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class InsertpageControllerG {

    @FXML
    private Button autosell;

    @FXML
    private Label brandLabel;

    @FXML
    private TextField brandTextField;

    @FXML
    private Button confirmButton;

    @FXML
    private Label fuelTypeLabel;

    @FXML
    private TextField fuelTypeTextField;

    @FXML
    private CheckBox heatedSeatsCheckBox;

    @FXML
    private Label horsepowerLabel;

    @FXML
    private TextField horsepowerTextField;

    @FXML
    private Label immatricolationDateLabel;

    @FXML
    private TextField immatricolationDateTextField;

    @FXML
    private CheckBox cruiseControlCheckBox;

    @FXML
    private CheckBox keylessSystemCheckBox;

    @FXML
    private CheckBox ledHeadlightsCheckBox;

    @FXML
    private Label licencePlateLabel;

    @FXML
    private TextField licencePlateTextField;

    @FXML
    private Label mileageLabel;

    @FXML
    private TextField mileageTextField;

    @FXML
    private Pane errorPane;

    @FXML
    private Label modelLabel;

    @FXML
    private TextField modelTextField;

    @FXML
    private CheckBox parkingSensorsCheckBox;

    @FXML
    private Label productionYearLabel;

    @FXML
    private TextField productionYearTextField;

    @FXML
    private Label transmissionLabel;

    @FXML
    private TextField transmissionTextField;

    @FXML
    void setHomepage(ActionEvent event) {
        FxmlLoader.setPage("SellerHomepage");
    }
    InsertionController insertionController = new InsertionController();

    private boolean[] decorationsArray = new boolean[5];

    FXMLLoader fxmlLoader;

    DescriptionpageControllerG descriptionpageControllerG;

    private CarBean createCarBean(){
        CarBean carBean = new CarBean();
        carBean.setBrand(brandTextField.getText());
        carBean.setModel(modelTextField.getText());

        if(!(mileageTextField.getText().isEmpty())){
            carBean.setMileage(Integer.parseInt(mileageTextField.getText()));
        }
        if(!(horsepowerTextField.getText().isEmpty())){
            carBean.setHorsepower(Integer.parseInt(horsepowerTextField.getText()));
        }

        carBean.setFuelType(fuelTypeTextField.getText());
        carBean.setLicencePlate(licencePlateTextField.getText());
        carBean.setTransmission(transmissionTextField.getText());
        carBean.setImmatricolationDate(immatricolationDateTextField.getText());
        carBean.setProductionYear(productionYearTextField.getText());

        decorationsArray[0] = cruiseControlCheckBox.isSelected();
        decorationsArray[1] = keylessSystemCheckBox.isSelected();
        decorationsArray[2] = heatedSeatsCheckBox.isSelected();
        decorationsArray[3] = ledHeadlightsCheckBox.isSelected();
        decorationsArray[4] = parkingSensorsCheckBox.isSelected();

        carBean.setDecorationsArray(decorationsArray);

        return carBean;
    }

    @FXML
    void insertCar(ActionEvent event) {
        CarBean carBean = createCarBean();

        if(carBean.validate()){
            insertionController.insertCar(carBean);
            fxmlLoader = FxmlLoader.setPage("DescriptionPage");
            descriptionpageControllerG = fxmlLoader.getController();
            insertionController.setDescriptionController(descriptionpageControllerG);

        }else {
            errorPane.setDisable(false);
            errorPane.setVisible(true);
        }
    }

    @FXML
    void closeErrorPaneButton(ActionEvent event) {
        errorPane.setDisable(true);
        errorPane.setVisible(false);
    }

}
