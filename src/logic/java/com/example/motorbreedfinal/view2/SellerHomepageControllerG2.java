package com.example.motorbreedfinal.view2;

import com.example.motorbreedfinal.controller.ManageAdsController;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view2.utility.ErrorPrinter;
import com.example.motorbreedfinal.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SellerHomepageControllerG2 {

    private String toPrint;
    public void setNameSurname(String name, String lastName){
        toPrint = "Welcome "+name+ " "+lastName;
        LinePrinter.getInstance().print(toPrint);

        toPrint = "Press 0 to Insert a new Ad, 1 to go to MyProfile";
        LinePrinter.getInstance().print(toPrint);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            switch (reader.readLine()){
                case  "0":
                    insertAd();
                    break;

                case "1":
                    myProfile();
                    break;

                default:
                    setNameSurname(LoggedUser.getInstance().getSeller().getFirstName(), LoggedUser.getInstance().getSeller().getLastName());
            }
        }catch (IOException e){
            LinePrinter.getInstance().print(e.getMessage());
        }


    }

    private void insertAd(){
        InsertionpageControllerG2 insertionpageControllerG2 = new InsertionpageControllerG2();
        insertionpageControllerG2.insertCar();
    }

    public void myProfile(){
        toPrint = "Press 0 to Manage your ads, 1 to Manage and modify your infos";
        LinePrinter.getInstance().print(toPrint);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            switch (reader.readLine()){
                case "0":
                    manageMyAds();
                    break;
                case "1":
                    modifyInfos();
                    break;
                default:
                    myProfile();
                    break;
            }

            
        }catch (IOException e){
            toPrint = "Something went wrong . . . ";
            LinePrinter.getInstance().print(toPrint);
            myProfile();
        }
    }

    private void modifyInfos() {
        AccountSettingsControllerG2 accountSettingsControllerG2 = new AccountSettingsControllerG2();
        accountSettingsControllerG2.initialize();
    }

    private void manageMyAds() {
        ManageAdsController manageAdsController = new ManageAdsController();
        manageAdsController.retrieveMyAds();

        if(!LoggedUser.getInstance().getSeller().getAdList().isEmpty()){
            ModifyAdControllerG2 modifyAdControllerG2 = new ModifyAdControllerG2();
            modifyAdControllerG2.setManageAdsController(manageAdsController);
            modifyAdControllerG2.setBean(manageAdsController.getAdBean());
            modifyAdControllerG2.showAd(0);
        } else{
            toPrint = "No ads to show.";
            ErrorPrinter.getInstance().print(toPrint);
            myProfile();
        }


    }

}
