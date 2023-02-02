package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.RegistrationController;
import com.example.motorbreedfinal.model.exceptions.FailedRegistrationException;
import com.example.motorbreedfinal.view1.fagioli.RegistrationBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class RegistrationControllerG {

    @FXML
    private Rectangle background;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonRegister;

    @FXML
    private Label errorConfirmPassword;

    @FXML
    private Label errorEmail;

    @FXML
    private Label errorPassword;

    @FXML
    private Label labelAccountData;

    @FXML
    private Label labelConfirmPassword;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelMandatoryFields;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelPersonalData;

    @FXML
    private Label labelPwdStrength;

    @FXML
    private Label labelRegister;

    @FXML
    private Label labelSurname;

    @FXML
    private PasswordField pfConfirmPassword;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfSurname;

    @FXML
    private Label lblErrorEmail;

    @FXML
    private Label lblRole;

    @FXML
    void Registration(ActionEvent event) {
        RegistrationBean registrationBean = new RegistrationBean();
        registrationBean.setEmail(tfEmail.getText());
        registrationBean.setPassword(pfPassword.getText());
        registrationBean.setConfirmationPassword(pfConfirmPassword.getText());
        registrationBean.setFirstName(tfName.getText());
        registrationBean.setLastName(tfSurname.getText());
        registrationBean.setRole(lblRole.getText());

        if(registrationBean.validation() && registrationBean.checkPasswords()){
            try{
                RegistrationController registrationController = new RegistrationController();
                registrationController.registration(registrationBean);
                FxmlLoader.setPage("LoginPage");
            } catch (FailedRegistrationException e){
                lblErrorEmail.setText(e.getMessage());
                lblErrorEmail.setVisible(true);
            }
        }
    }

    @FXML
    void enableRegister(KeyEvent event) {
        lblErrorEmail.setVisible(false);
        buttonRegister.setDisable(!(tfEmail.getText().length() > 4 && pfPassword.getText().length() > 5 &&
                                  pfPassword.getText().equals(pfConfirmPassword.getText()) &&
                                  tfName.getText().length() > 0 && tfSurname.getText().length() > 0));
    }

    @FXML
    void goBack(ActionEvent event) {
        FxmlLoader.setPage("Homepage");
    }

    public void setRole(String role){
        lblRole.setText(role);
    }

}
