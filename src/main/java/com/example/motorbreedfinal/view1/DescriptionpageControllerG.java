package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.InsertionController;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.DescriptionpageBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DescriptionpageControllerG {

    @FXML
    private Button MotorbreedButton;

    @FXML
    private TextField adLocationTF;

    @FXML
    private Button buttonBack;

    @FXML
    private TextField descriptionTF;

    @FXML
    private Button evaluationButton;

    @FXML
    private TextField proposedPriceTF;

    @FXML
    private TextField sellerPriceTF;

    @FXML
    private Label titleLabel;


    @FXML
    private ImageView uploadImageView;

    @FXML
    private Pane paneError;

    @FXML
    private Pane evaluatorPane;

    @FXML
    private Pane failedInsertionPane;

    @FXML
    private Button insertAdButton;

    private InsertionController insertionController;

    private InputStream inputStream;

    @FXML
    void closePaneError(ActionEvent event) {
        paneError.setVisible(false);
        insertAdButton.setDisable(false);
    }

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void setInsertionPage(ActionEvent event) {
        FxmlLoader.setPage("InsertPage");
    }

    @FXML
    void evaluateCar(ActionEvent event) {
        insertionController.startEvaluation();
    }

    @FXML
    void uploadImage(ActionEvent event) {

        FileChooser fc = new FileChooser();
        inputStream = null;
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.jpg,*.png,*.jpeg","*.jpg","*.png", "*.jpeg"));
        File file = fc.showOpenDialog(null);
        if(file!=null){
            String imagePath = file.getAbsolutePath();
            try {
                inputStream = new FileInputStream(imagePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image cover = new Image(inputStream);
            uploadImageView.setImage(cover);
        }

    }

    @FXML
    void insertAd(ActionEvent event) {

        AdBean adBean;

        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        try {
            if (sellerPriceTF.getText().isEmpty() && !proposedPriceTF.getText().isEmpty()) {
                adBean = new AdBean(out, descriptionTF.getText()
                        , adLocationTF.getText(), Integer.parseInt(proposedPriceTF.getText().substring(0, proposedPriceTF.getText().length()-1)), true, inputStream);

                if(adBean.Validation()){
                    insertionController.insertAd(adBean);
                }else {
                    System.out.println("not validation");
                }
            } else if (!sellerPriceTF.getText().isEmpty() && proposedPriceTF.getText().isEmpty()) {
                adBean = new AdBean(out, descriptionTF.getText()
                        , adLocationTF.getText(), Integer.parseInt(sellerPriceTF.getText().substring(0, proposedPriceTF.getText().length()-1)), false,inputStream);

                if(adBean.Validation()){
                    insertionController.insertAd(adBean);
                }else {
                    System.out.println("not validation");
                }


            } else {
                paneError.setVisible(true);
                paneError.setDisable(false);
                insertAdButton.setDisable(true);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @FXML
    void closeFailedInsertion(ActionEvent event) {
        FxmlLoader.setPage("InsertPage");
    }

    @FXML
    void openEvaluatorInfo(MouseEvent event) {
        evaluatorPane.setDisable(false);
        evaluatorPane.setVisible(true);
    }

    @FXML
    void closeEvaluatorInfo(ActionEvent event) {
        evaluatorPane.setVisible(false);
        evaluatorPane.setDisable(true);
    }

    public void setInsertionController(InsertionController insertionController){
        this.insertionController = insertionController;
    }

    public void setProposedPrice(DescriptionpageBean descriptionpageBean){
        this.proposedPriceTF.setText(descriptionpageBean.getEvaluatedPrice()+"€");
    }

    public void showFailedInsertion() {
        failedInsertionPane.setDisable(false);
        failedInsertionPane.setVisible(true);
    }
}

