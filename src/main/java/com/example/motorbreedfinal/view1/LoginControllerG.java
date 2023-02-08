package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.LoginController;
import com.example.motorbreedfinal.view1.fagioli.AccountHomepageBean;
import com.example.motorbreedfinal.view1.fagioli.LoginBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LoginControllerG {
    @FXML
    private ImageView btnFacebook;
    @FXML
    private ImageView btnGoogle;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegisterConcessionaria;
    @FXML
    private Button btnRegisterPrivato;
    @FXML
    private Button lblMotorbreed;
    @FXML
    private ImageView logoMotorbreed;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private TextField tfEmail;
    @FXML
    private Label lblWrongFormat;

    LoginController loginController = new LoginController(); // istanziamo il controller applicativo

    FXMLLoader fxmlLoader;

    AccountHomepageBean accountHomepageBean;
    @FXML
    void setHomepage(ActionEvent event) {
        FxmlLoader.setPage("Homepage");
    } // operazione usata al termine del caso d'uso

    @FXML
    void login() {
        LoginBean loginBean = new LoginBean();              // settaggio parametri LoginBean
        loginBean.setEmail(tfEmail.getText());
        loginBean.setPassword(pfPassword.getText());

        if(loginBean.validation()){

            try {
                accountHomepageBean = loginController.login(loginBean);

                if (accountHomepageBean.getRole().equals("Seller")) {
                    SellerHomepageControllerG sellerHomepageControllerG;
                    fxmlLoader = FxmlLoader.setPage("SellerHomepage");
                    sellerHomepageControllerG = fxmlLoader.getController();
                    sellerHomepageControllerG.setNameSurnameTF(accountHomepageBean.getFirstName(), accountHomepageBean.getLastName());

                }else if(accountHomepageBean.getRole().equals("Buyer")) {
                    BuyerHomepageControllerG buyerHomepageControllerG;
                    fxmlLoader = FxmlLoader.setPage("BuyerHomepage");
                    buyerHomepageControllerG = fxmlLoader.getController();
                    buyerHomepageControllerG.setNameSurnameTF(accountHomepageBean.getFirstName(), accountHomepageBean.getLastName());
                }

            } catch (FailedLoginException e) {
                showErrorMessage(e.getMessage());
            } catch (SQLException e) {
                //not handled
            }

        } else{
            showErrorMessage("Email or password format are not correct. Insert again");
        }
    }

    public void showErrorMessage(String message) {
        lblWrongFormat.setText(message);
        lblWrongFormat.setVisible(true);
    }

    @FXML
    public void registerBuyer(){
        FXMLLoader loader = FxmlLoader.setPage("RegisterPage");
        RegistrationControllerG registrationBuyerControllerG = loader.getController();
        registrationBuyerControllerG.setRole("Buyer");
    }

    @FXML
    public void registerSeller(){
        FXMLLoader loader = FxmlLoader.setPage("RegisterPage");
        RegistrationControllerG registrationControllerG = loader.getController();
        registrationControllerG.setRole("Seller");
    }

    @FXML
    public void enableLogin(){
        lblWrongFormat.setVisible(false);
        btnLogin.setDisable(pfPassword.getText().length() <= 5 || tfEmail.getText().length() <= 5);
    }


}