package com.example.motorbreedfinal.view2;

import com.example.motorbreedfinal.controller.ResearchController;
import com.example.motorbreedfinal.model.exceptions.FailedResearchException;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.AdvancedResearchBean;
import com.example.motorbreedfinal.view1.fagioli.ResearchBean;
import com.example.motorbreedfinal.view2.utility.ErrorPrinter;
import com.example.motorbreedfinal.view2.utility.LinePrinter;
import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuyerHomepageControllerG2 {

    String toPrint;

    public void setNameSurname(String name, String lastName) throws IOException {
        toPrint = "Welcome "+name+ " "+lastName + " to Homepage. Press 0 to buy a car or 1 to go to My profile: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = null;
        try {
            choice = reader.readLine();
        } catch (IOException e) {
            //towrite
        }
        switch(choice){
            case("0"):
                searchAd();
                break;
            case("1"):
                myProfile();
                break;
            default:
                break;
        }
    }

    public void myProfile() {
        toPrint = "This is your profile page. Press 0 to see your favorite ads, 1 to see your past orders, 2 to view or modify your account data: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            switch(reader.readLine()){
                case "0":
                    if(!LoggedUser.getInstance().getBuyer().getFavourites().isEmpty()){
                        AdBean adBean = new AdBean();
                        adBean.setAds(LoggedUser.getInstance().getBuyer().getFavourites());
                        ResultsPageControllerG2 resultsPageControllerG2 = new ResultsPageControllerG2();
                        resultsPageControllerG2.setAds(adBean);
                    } else{
                        toPrint = "Nothing in your favorites. Empty or the Ad are now sold!";
                        LinePrinter.getInstance().print(toPrint);
                        myProfile();
                    }
                    break;
                case "1":
                    if(!LoggedUser.getInstance().getBuyer().getOrders().isEmpty()){
                        AdBean adBean = new AdBean();
                        adBean.setAds(LoggedUser.getInstance().getBuyer().getOrders());
                        ResultsPageControllerG2 resultsPageControllerG2 = new ResultsPageControllerG2();
                        resultsPageControllerG2.setAds(adBean);
                    } else{
                        toPrint = "No orders have been made.";
                        LinePrinter.getInstance().print(toPrint);
                        myProfile();
                    }
                    break;
                case "2":
                    AccountSettingsControllerG2 accountSettingsControllerG2 = new AccountSettingsControllerG2();
                    accountSettingsControllerG2.initialize();
            }
        } catch (IOException e){
            //qualcosa è andato stuorrt
        }
    }

    private void searchAd() throws IOException {
        String brand = "", minPrice = "", maxPrice = "", minMileage = "", maxMileage = "";
        toPrint = "You can search car with base filters pressing 0, 1 with advanced filters: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        switch (reader.readLine()) {
            case "0":
                toPrint = "wanna filter for brands? 0 yes, anything for no ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Insert brand: ";
                    LinePrinter.getInstance().print(toPrint);
                    brand = reader.readLine();
                }
                toPrint = "wanna filter for price? 0 yes, anything no";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "if you want a minimum price press 0, anything else to skip: ";
                    LinePrinter.getInstance().print(toPrint);
                    if (reader.readLine().equals("0")) {
                        toPrint = "insert minimum price: ";
                        LinePrinter.getInstance().print(toPrint);
                        minPrice = reader.readLine();
                    }
                    toPrint = "if you want a maximum price press 0, anything else to skip: ";
                    LinePrinter.getInstance().print(toPrint);
                    if (reader.readLine().equals("0")) {
                        toPrint = "insert maximum price: ";
                        LinePrinter.getInstance().print(toPrint);
                        maxPrice = reader.readLine();
                    }
                }
                toPrint = "wanna filter for mileage? 0 yes, anything no";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "if you want a minimum mileage press 0, anything else to skip: ";
                    LinePrinter.getInstance().print(toPrint);
                    switch (reader.readLine()) {
                        case "0":
                            toPrint = "insert minimum mileage: ";
                            LinePrinter.getInstance().print(toPrint);
                            minPrice = reader.readLine();
                            break;
                        default:
                            break;
                    }
                    toPrint = "if you want a maximum mileage press 0, anything else to skip: ";
                    LinePrinter.getInstance().print(toPrint);
                    if (reader.readLine().equals("0")) {
                        toPrint = "insert maximum mileage: ";
                        LinePrinter.getInstance().print(toPrint);
                        maxMileage = reader.readLine();
                    }
                }
            case "1":
                advancedResearch();
            default:
                //no behavior
        }
            searchCar(brand,minMileage,maxMileage,minPrice,minPrice);


    }
    private void advancedResearch() throws IOException {
        String brand = "", model = "", fuelType = "", productionYear = "", startingHP = "", maxHP = "", transmission = "",
                minPrice = "", maxPrice = "", minMileage = "", maxMileage = "", decorations = "";

        toPrint = "This is the advanced research.";
        LinePrinter.getInstance().print(toPrint);
        toPrint = " 0 to insert brand (mandatory), anything else to skip advanced research ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            if (reader.readLine().equals("0")) {
                toPrint = "Brand: ";
                LinePrinter.getInstance().print(toPrint);
                brand = reader.readLine();

                toPrint = " 0 to insert model, anything to skip: ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Model: ";
                    LinePrinter.getInstance().print(toPrint);
                    model = reader.readLine();
                }

                toPrint = " 0 to insert fuel type, anything to skip: ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Fuel type: ";
                    LinePrinter.getInstance().print(toPrint);
                    fuelType = reader.readLine();
                }

                toPrint = " 0 to insert production year, anything to skip: ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Production year: ";
                    LinePrinter.getInstance().print(toPrint);
                    productionYear = reader.readLine();
                }

                toPrint = " 0 to insert starting horse power, anything to skip: ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Starting hp: ";
                    LinePrinter.getInstance().print(toPrint);
                    startingHP = reader.readLine();
                }

                toPrint = " 0 to insert max horse power, anything to skip: ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Max hp: ";
                    LinePrinter.getInstance().print(toPrint);
                    maxHP = reader.readLine();
                }

                toPrint = " 0 to insert transmission, anything to skip: ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Transmission: ";
                    LinePrinter.getInstance().print(toPrint);
                    transmission = reader.readLine();
                }

                toPrint = " 0 to insert minimum price, anything to skip: ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Min price: ";
                    LinePrinter.getInstance().print(toPrint);
                    minPrice = reader.readLine();
                }

                toPrint = " 0 to insert maximum price, anything to skip: ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Max price: ";
                    LinePrinter.getInstance().print(toPrint);
                    maxPrice = reader.readLine();
                }

                toPrint = " 0 to insert minumum mileage, anything to skip: ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Min mileage: ";
                    LinePrinter.getInstance().print(toPrint);
                    minMileage = reader.readLine();
                }

                toPrint = " 0 to insert maximum mileage, anything to skip: ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Max mileage: ";
                    LinePrinter.getInstance().print(toPrint);
                    maxMileage = reader.readLine();
                }

                toPrint = " 0 to insert transmission, anything to skip: ";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    toPrint = "Transmission: ";
                    LinePrinter.getInstance().print(toPrint);
                    transmission = reader.readLine();
                }

                toPrint = " 0 if car has cruise control, 1 if doesn't have";
                LinePrinter.getInstance().print(toPrint);
                switch (reader.readLine()) {
                    case "0":
                        decorations += "1";
                        break;
                    default:
                        decorations += "0";
                        break;
                }

                toPrint = " 0 if car has keyless system, 1 if doesn't have";
                LinePrinter.getInstance().print(toPrint);
                switch (reader.readLine()) {
                    case "0":
                        decorations += "1";
                        break;
                    default:
                        decorations += "0";
                        break;
                }

                toPrint = " 0 if car has heated seats, 1 if doesn't have";
                LinePrinter.getInstance().print(toPrint);
                switch (reader.readLine()) {
                    case "0":
                        decorations += "1";
                        break;
                    default:
                        decorations += "0";
                        break;
                }

                toPrint = " 0 if car has led headlights, 1 if doesn't have";
                LinePrinter.getInstance().print(toPrint);
                switch (reader.readLine()) {
                    case "0":
                        decorations += "1";
                        break;
                    default:
                        decorations += "0";
                        break;
                }

                toPrint = " 0 if car has parking sensors, 1 if doesn't have";
                LinePrinter.getInstance().print(toPrint);
                switch (reader.readLine()) {
                    case "0":
                        decorations += "1";
                        break;
                    default:
                        decorations += "0";
                        break;
                }

                AdvancedResearchBean advancedResearchBean = new AdvancedResearchBean();
                advancedResearchBean.setData(brand, model, fuelType,
                        productionYear, startingHP, maxHP, transmission,
                        minPrice, maxPrice, minMileage, maxMileage, decorations);

                if (advancedResearchBean.isDataValid()) {
                    try {
                        AdBean adBean = ResearchController.getInstance().advancedResearch(advancedResearchBean);
                        if (!adBean.getAds().isEmpty()) {
                            ResultsPageControllerG2 resultsPageControllerG2 = new ResultsPageControllerG2();
                            resultsPageControllerG2.setAds(adBean);
                        } else {
                            //TODO CREARE LABEL ERRORE DI PRIMA
                        }
                    } catch (FailedResearchException e) {
                        //TODO CREARE LABEL ERRORE IN SCENE E SETTARLA
                    }
                }
            }
        } catch(IOException io){
                toPrint = "Something went wrong. closing app";
                ErrorPrinter.getInstance().print(toPrint);
        }
    }



    private void searchCar(String brand, String minMileage, String maxMileage, String minPrice, String maxPrice) {
        ResearchBean researchBean = new ResearchBean(brand, minMileage, maxMileage,
                minPrice, maxPrice);
        if(researchBean.isDataValid()){
            AdBean adBean = null;
            try {
                adBean = ResearchController.getInstance().baseResearch(researchBean);
                if(!adBean.getAds().isEmpty()){
                    ResultsPageControllerG2 resultsPageControllerG2 = new ResultsPageControllerG2();
                    resultsPageControllerG2.setAds(adBean);
                } else{
                    toPrint = "No results for the insered data.";
                    ErrorPrinter.getInstance().print(toPrint);
                }
            } catch (FailedResearchException e) {
                toPrint = "The error " + e.getMessage() + "occured during query. try again";
                LinePrinter.getInstance().print(toPrint);
            }

        } else {
            toPrint = "Format data is not valid, start again";
            LinePrinter.getInstance().print(toPrint);
            try {
                searchAd();
            } catch (IOException e) {
                //to write
            }
        }
    }
}
