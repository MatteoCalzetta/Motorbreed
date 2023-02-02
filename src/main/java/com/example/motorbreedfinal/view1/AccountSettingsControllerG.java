package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.CustomizeProfileController;
import com.example.motorbreedfinal.model.users.AccountObserver;
import com.example.motorbreedfinal.model.users.AccountSubject;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.model.exceptions.FailedProfileCustomizationException;
import com.example.motorbreedfinal.view1.fagioli.AccountBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

public class AccountSettingsControllerG implements AccountObserver {

    @FXML
    private Rectangle background;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonSave;

    @FXML
    private Label errorAddress;

    @FXML
    private Label errorConfirmNewPassword;

    @FXML
    private Label errorEmail;

    @FXML
    private Label errorNewPassword;

    @FXML
    private Label errorPassword;

    @FXML
    private Label labelAccountData;

    @FXML
    private Label labelAddress;

    @FXML
    private Label labelConfirmNewPassword;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelName;

    @FXML
    private Label labelNewPassword;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelPersonalData;

    @FXML
    private Label labelProfile;

    @FXML
    private Label labelPwdStrength;

    @FXML
    private PasswordField pfConfirmNewPassword;

    @FXML
    private PasswordField pfNewPassword;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFirstname;

    @FXML
    private TextField tfLastname;

    @FXML
    private TextField tfNewEmail;

    @FXML
    private TextField tfNewFirstname;

    @FXML
    private TextField tfNewLastname;

    @FXML
    private TextField tfNewPassword;

    @FXML
    void goBack(ActionEvent event) {
        FxmlLoader.setPage(LoggedUser.getInstance().getRole() + "UserPage");
    }

    @FXML
    void save(ActionEvent event) throws FailedProfileCustomizationException {
        AccountBean accountBean = new AccountBean();
        CustomizeProfileController customizeProfileController = new CustomizeProfileController();



        if(!tfNewFirstname.getText().equalsIgnoreCase(LoggedUser.getInstance().getAccount().getFirstName()) && !tfNewFirstname.getText().isEmpty()){
            String firstName = tfNewFirstname.getText().toLowerCase();
            String initial = firstName.substring(0,1).toUpperCase();
            firstName = initial + firstName.substring(1);
            accountBean.setFirstName(firstName);
            customizeProfileController.changeFirstName(accountBean);
        }

        if(!tfNewLastname.getText().equalsIgnoreCase(LoggedUser.getInstance().getAccount().getLastName()) && !tfNewLastname.getText().isEmpty()){
            String lastName = tfNewLastname.getText().toLowerCase();
            String initial = lastName.substring(0,1).toUpperCase();
            lastName = initial + lastName.substring(1);
            accountBean.setLastName(lastName);
            customizeProfileController.changeLastName(accountBean);
        }
        if(!tfNewEmail.getText().equalsIgnoreCase(LoggedUser.getInstance().getAccount().getEmail()) && !tfNewEmail.getText().isEmpty()){
            accountBean.setEmail(tfNewEmail.getText().toLowerCase());
            customizeProfileController.changeEmail(accountBean);
        }

        resetButtons();
    }

    private void resetButtons() {
        buttonSave.setVisible(false);
        buttonEdit.setVisible(true);
        tfNewFirstname.setText("");
        tfNewLastname.setText("");
        tfNewEmail.setText("");
        tfNewEmail.setVisible(false);
        tfNewFirstname.setVisible(false);
        tfNewLastname.setVisible(false);
    }

    @FXML
    void setEditable(ActionEvent event) {
        buttonSave.setVisible(true);
        buttonEdit.setVisible(false);
        tfNewEmail.setVisible(true);
        tfNewFirstname.setVisible(true);
        tfNewLastname.setVisible(true);
    }

    @Override
    public void update() {
        tfFirstname.setText(LoggedUser.getInstance().getAccount().getFirstName());
        tfLastname.setText(LoggedUser.getInstance().getAccount().getLastName());
        tfEmail.setText(LoggedUser.getInstance().getAccount().getEmail());

    }

    public void initialize(){
        tfFirstname.setText(LoggedUser.getInstance().getAccount().getFirstName());
        tfLastname.setText(LoggedUser.getInstance().getAccount().getLastName());
        tfEmail.setText(LoggedUser.getInstance().getAccount().getEmail());

        AccountSubject.attach(this);
    }


}
