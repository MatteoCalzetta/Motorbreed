package com.example.motorbreedfinal.view2;

import com.example.motorbreedfinal.controller.ResearchController;
import com.example.motorbreedfinal.model.exceptions.FailedResearchException;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.AdvancedResearchBean;
import com.example.motorbreedfinal.view1.fagioli.ResearchBean;
import com.example.motorbreedfinal.view2.utility.ErrorPrinter;
import com.example.motorbreedfinal.view2.utility.LinePrinter;
import com.mysql.cj.x.protobuf.Mysqlx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuyerHomepageControllerG2 {

    String toPrint;

    public void setNameSurname(String name, String lastName) throws IOException {
        toPrint = "Welcome "+name+ " "+lastName + " to Homepage. Press 0 to buy a car or 1 to go to My profile: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
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
                    break;
                default:
                    myProfile();
            }

        } catch (IOException e){
            //qualcosa è andato stuorrt
        }
    }

    private void searchAd() throws IOException {
        String brand = "";
        String minPrice = "";
        String maxPrice = "";
        String minMileage = "";
        String maxMileage = "";
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
                    minPrice = getMinPrice(minPrice, reader);
                    maxPrice = getMaxPrice("if you want a maximum price press 0, anything else to skip: ", reader, "insert maximum price: ", maxPrice);
                }
                toPrint = "wanna filter for mileage? 0 yes, anything no";
                LinePrinter.getInstance().print(toPrint);
                if (reader.readLine().equals("0")) {
                    minMileage = getValue("if you want a minimum mileage press 0, anything else to skip: ", reader, "insert minimum mileage: ", minMileage);
                }
                maxMileage = getValue("if you want a maximum mileage press 0, anything else to skip: ", reader, "insert maximum mileage: ", maxMileage);
                break;
            case "1":
                advancedResearch();
                break;
            default:
                searchAd();
                break;
        }
            searchCar(brand,minMileage,maxMileage,minPrice,maxPrice);


    }

    private String getMaxPrice(String toPrint, BufferedReader reader, String toPrint1, String maxPrice) throws IOException {
        LinePrinter.getInstance().print(toPrint);
        if (reader.readLine().equals("0")) {
            toPrint = toPrint1;
            LinePrinter.getInstance().print(toPrint);
            maxPrice = reader.readLine();
        }
        return maxPrice;
    }

    private String getMinPrice(String minPrice, BufferedReader reader) throws IOException {
        minPrice = getValue("if you want a minimum price press 0, anything else to skip: ", reader, "insert minimum price: ", minPrice);
        return minPrice;
    }

    private void advancedResearch() throws IOException {
        String brand = "";
        String model = "";
        String fuelType = "";
        String productionYear = "";
        String startingHP = "";
        String maxHP = "";
        String transmission = "";
        String minPrice = "";
        String maxPrice = "";
        String minMileage = "";
        String maxMileage = "";
        String decorations = "";

        toPrint = "This is the advanced research.";
        LinePrinter.getInstance().print(toPrint);
        toPrint = " 0 to insert brand (mandatory), anything else to skip advanced research ";
        LinePrinter.getInstance().print(toPrint);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if (reader.readLine().equals("0")) {
                toPrint = "Brand: ";
                LinePrinter.getInstance().print(toPrint);
                brand = reader.readLine();

                model = getValue(" 0 to insert model, anything to skip: ", reader, "Model: ", model);

                fuelType = getValue(" 0 to insert fuel type, anything to skip: ", reader, "Fuel type: ", fuelType);

                productionYear = getValue(" 0 to insert production year, anything to skip: ", reader, "Production year: ", productionYear);

                startingHP = getValue(" 0 to insert starting horse power, anything to skip: ", reader, "Starting hp: ", startingHP);

                maxHP = getValue(" 0 to insert max horse power, anything to skip: ", reader, "Max hp: ", maxHP);

                transmission = getValue(" 0 to insert transmission, anything to skip: ", reader, "Transmission: ", transmission);

                minPrice = getValue(" 0 to insert minimum price, anything to skip: ", reader, "Min price: ", minPrice);

                maxPrice = getValue(" 0 to insert maximum price, anything to skip: ", reader, "Max price: ", maxPrice);

                minMileage = getValue(" 0 to insert minumum mileage, anything to skip: ", reader, "Min mileage: ", minMileage);

                maxMileage = getValue(" 0 to insert maximum mileage, anything to skip: ", reader, "Max mileage: ", maxMileage);

                transmission = getValue(" 0 to insert transmission, anything to skip: ", reader, "Transmission: ", transmission);

                decorations = getDecorations(" 0 if car has cruise control, 1 if doesn't have", reader, decorations);

                decorations = getDecorations(" 0 if car has keyless system, 1 if doesn't have", reader, decorations);

                decorations = getDecorations(" 0 if car has heated seats, 1 if doesn't have", reader, decorations);

                decorations = getDecorations(" 0 if car has led headlights, 1 if doesn't have", reader, decorations);

                decorations = getDecorations(" 0 if car has parking sensors, 1 if doesn't have", reader, decorations);

                AdvancedResearchBean advancedResearchBean = new AdvancedResearchBean();
                advancedResearchBean.setData(brand, model, fuelType,
                        productionYear, startingHP, maxHP, transmission,
                        minPrice, maxPrice, minMileage, maxMileage, decorations);

                if (advancedResearchBean.isDataValid()) {
                    searchCar(advancedResearchBean);
                }
            }
        } catch(IOException io){
                toPrint = "Something went wrong. closing app";
                ErrorPrinter.getInstance().print(toPrint);
        }
    }

    private static void searchCar(AdvancedResearchBean advancedResearchBean) {
        try {
            research(advancedResearchBean);
        } catch (FailedResearchException e) {
            ErrorPrinter.getInstance().print(e.getMessage());
        }
    }

    private String getDecorations(String toPrint, BufferedReader reader, String decorations) throws IOException {
        LinePrinter.getInstance().print(toPrint);
        if (reader.readLine().equals("0")) {
            decorations += "1";
        } else {
            decorations += "0";
        }
        return decorations;
    }

    private String getValue(String toPrint, BufferedReader reader, String toPrint1, String model) throws IOException {
        LinePrinter.getInstance().print(toPrint);
        if (reader.readLine().equals("0")) {
            toPrint = toPrint1;
            LinePrinter.getInstance().print(toPrint);
            model = reader.readLine();
        }
        return model;
    }

    private static void research(AdvancedResearchBean advancedResearchBean) throws FailedResearchException {
        AdBean adBean = ResearchController.getInstance().advancedResearch(advancedResearchBean);
        if (!adBean.getAds().isEmpty()) {
            ResultsPageControllerG2 resultsPageControllerG2 = new ResultsPageControllerG2();
            resultsPageControllerG2.setAds(adBean);
        } else {
            ErrorPrinter.getInstance().print("No ads retrieved");
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
