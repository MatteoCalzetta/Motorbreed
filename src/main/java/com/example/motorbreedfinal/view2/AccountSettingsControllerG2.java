package com.example.motorbreedfinal.view2;

import com.example.motorbreedfinal.controller.CustomizeProfileController;
import com.example.motorbreedfinal.model.exceptions.FailedProfileCustomizationException;
import com.example.motorbreedfinal.model.users.AccountObserver;
import com.example.motorbreedfinal.model.users.AccountSubject;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view1.fagioli.AccountBean;
import com.example.motorbreedfinal.view2.utility.LinePrinter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccountSettingsControllerG2 implements AccountObserver {
    private String toPrint;


    public void initialize() {
        AccountSubject.attach(this);
        printCredentials();
        try{
            toPrint = "Press 0 to change credentials, anything to go back to My profile: ";
            LinePrinter.getInstance().print(toPrint);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if(reader.readLine().equals("0")) {
                changeCredentials();
            } else {
                BuyerHomepageControllerG2 buyerHomepageControllerG2 = new BuyerHomepageControllerG2();
                buyerHomepageControllerG2.myProfile();
            }
        } catch(IOException e){
            //something wront
        }
        changeCredentials();

    }

    private void printCredentials() {
        toPrint = "Your credentials are: \nFirst name = " + LoggedUser.getInstance().getAccount().getFirstName() +
                ", Last name = " + LoggedUser.getInstance().getAccount().getLastName() + ", email = " + LoggedUser.getInstance().getAccount().getEmail();
        LinePrinter.getInstance().print(toPrint);
    }

    private void changeCredentials() {
        CustomizeProfileController customizeProfileController = new CustomizeProfileController();
        AccountBean accountBean = new AccountBean();
        toPrint = "Press 0 to change first name, 1 to change last name or 2 to change email, anything to quit: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            switch(reader.readLine()){
                case "0":
                    toPrint = "New first name: ";
                    LinePrinter.getInstance().print(toPrint);
                    String firstName = reader.readLine();
                    if(!firstName.equalsIgnoreCase(LoggedUser.getInstance().getAccount().getFirstName()) && !firstName.isEmpty()){
                        firstName = firstName.toLowerCase();
                        String initial = firstName.substring(0,1).toUpperCase();
                        firstName = initial + firstName.substring(1);
                        accountBean.setFirstName(firstName);
                        customizeProfileController.changeFirstName(accountBean);
                    }
                    break;
                case "1":
                    toPrint = "New last name: ";
                    String lastName = reader.readLine();
                    if(!lastName.equalsIgnoreCase(LoggedUser.getInstance().getAccount().getFirstName()) && !lastName.isEmpty()){
                        lastName = lastName.toLowerCase();
                        String initial = lastName.substring(0,1).toUpperCase();
                        lastName = initial + lastName.substring(1);
                        accountBean.setLastName(lastName);
                        customizeProfileController.changeFirstName(accountBean);
                    }
                    break;
                case "2":
                    toPrint = "New email: ";
                    String email = reader.readLine();
                    if(!email.equalsIgnoreCase(LoggedUser.getInstance().getAccount().getEmail()) && !email.isEmpty()){
                        accountBean.setEmail(email.toLowerCase());
                        customizeProfileController.changeEmail(accountBean);
                    }
                    break;
                default:
                    if(LoggedUser.getInstance().getRole().equals("Buyer")){
                        BuyerHomepageControllerG2 buyerHomepageControllerG2 = new BuyerHomepageControllerG2();
                        buyerHomepageControllerG2.myProfile();
                    } else{
                        SellerHomepageControllerG2 sellerHomepageControllerG2 = new SellerHomepageControllerG2();
                        sellerHomepageControllerG2.myProfile();
                    }

            }
        } catch (IOException | FailedProfileCustomizationException e){
            //erore
        }
    }

    @Override
    public void update() {
        printCredentials();
    }
}
