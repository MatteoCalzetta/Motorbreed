package com.example.motorbreedfinal.view2;

import com.example.motorbreedfinal.controller.LoginController;
import com.example.motorbreedfinal.controller.RegistrationController;
import com.example.motorbreedfinal.model.exceptions.FailedRegistrationException;
import com.example.motorbreedfinal.view1.FxmlLoader;
import com.example.motorbreedfinal.view1.fagioli.AccountHomepageBean;
import com.example.motorbreedfinal.view1.fagioli.LoginBean;
import com.example.motorbreedfinal.view1.fagioli.RegistrationBean;
import com.example.motorbreedfinal.view2.utility.ErrorPrinter;
import com.example.motorbreedfinal.view2.utility.LinePrinter;

import javax.security.auth.login.FailedLoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class LoginControllerG2 {

    private String toPrint;
    private LoginController loginController = new LoginController();



    private void login(){
        LoginBean loginBean = new LoginBean();
        toPrint = "Logging in. . . Insert your credentials.\n Email: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            loginBean.setEmail(reader.readLine());
            toPrint = "\nPassword: ";
            LinePrinter.getInstance().print(toPrint);
            loginBean.setPassword(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(loginBean.Validation()){
            try {
                AccountHomepageBean accountHomepageBean = loginController.Login(loginBean);
                LinePrinter.getInstance().clearConsole();

                if (accountHomepageBean.getRole().equals("Seller")) {
                    SellerHomepageControllerG2 sellerHomepageControllerG2 = new SellerHomepageControllerG2();
                    sellerHomepageControllerG2.setNameSurname(accountHomepageBean.getFirstName(), accountHomepageBean.getLastName());

                }else if(accountHomepageBean.getRole().equals("Buyer")) {
                    BuyerHomepageControllerG2 buyerHomepageControllerG2 = new BuyerHomepageControllerG2();
                    buyerHomepageControllerG2.setNameSurname(accountHomepageBean.getFirstName(), accountHomepageBean.getLastName());
                }

            } catch (FailedLoginException e) {
                ErrorPrinter.getInstance().print(e.getMessage());
            } catch (SQLException | IOException e) {
                //towrite
            }

        } else{
            ErrorPrinter.getInstance().print("Email or password format are not correct. Insert again");
            getRole();
        }
    }


    private void registration(){
        RegistrationBean registrationBean = new RegistrationBean();
        toPrint = "Registration begins, insert email: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            registrationBean.setEmail(reader.readLine());
            System.out.println(registrationBean.getEmail());
            toPrint = "Insert password: ";
            LinePrinter.getInstance().print(toPrint);
            registrationBean.setPassword(reader.readLine());
            toPrint = "Confirm password: ";
            LinePrinter.getInstance().print(toPrint);
            registrationBean.setConfirmationPassword(reader.readLine());
            toPrint = "First name: ";
            LinePrinter.getInstance().print(toPrint);
            registrationBean.setFirstName(reader.readLine());
            toPrint = "Last name: ";
            LinePrinter.getInstance().print(toPrint);
            registrationBean.setLastName(reader.readLine());
            toPrint = "Press 0 if you want to register as a buyer, 1 as a seller";
            LinePrinter.getInstance().print(toPrint);
            Integer role = Integer.valueOf(reader.readLine());
                switch(role){
                    case 0:
                        registrationBean.setRole("Buyer");
                        break;
                    case 1:
                        registrationBean.setRole("Seller");
                        break;
                    default:
                        toPrint = "Value not recognized. restarting registration. . .";
                        LinePrinter.getInstance().print(toPrint);
                        registration();
                }

        } catch (IOException e) {
            //towrite
        }
        if(registrationBean.Validation() && registrationBean.checkPasswords()){
            try{
                RegistrationController registrationController = new RegistrationController();
                registrationController.Registration(registrationBean);
                toPrint = "Registration succesful, now redirecting to login. . .";
                LinePrinter.getInstance().print(toPrint);
                getRole();
            } catch (FailedRegistrationException e){
                ErrorPrinter.getInstance().print(e.getMessage());
            }
        }

    }
    public void getRole(){
        toPrint = "LOGINPAGE\nPress 0 if you want to login, 1 if you want to register: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String role = null;
        try {
            role = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(role){
            case"0":
                login();
                break;
            case"1":
                registration();
                break;
            default:
                toPrint = "The number you insered is not valid";
                ErrorPrinter.getInstance().print(toPrint);
                getRole();
                break;
        }

    }
}
