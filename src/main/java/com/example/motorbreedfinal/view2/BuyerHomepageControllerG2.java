package com.example.motorbreedfinal.view2;

import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view1.fagioli.ResearchBean;
import com.example.motorbreedfinal.view2.utility.LinePrinter;

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
                //myProfile();
                break;
            default:
                break;
        }
    }

   /* private void myProfile() {
        ResearchBean researchBean = new ResearchBean(tfBrand.getText(), tfMinMileage.getText(), tfMaxMileage.getText(),
                tfMinPrice.getText(), tfMaxPrice.getText());
    } */

    private void searchAd() throws IOException {
        String brand = "", minPrice = "", maxPrice = "", minMileage = "", maxMileage = "";
        toPrint = "You can search car with base filters pressing 0, 1 with advanced filters: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        switch(reader.readLine()){
            case "0":
                toPrint = "wanna filter for brands? 0 yes, anything for no ";
                LinePrinter.getInstance().print(toPrint);
                switch(reader.readLine()){
                    case "0":
                        toPrint = "Insert brand: ";
                        LinePrinter.getInstance().print(toPrint);
                        brand = reader.readLine();
                        break;
                    default:
                        break;
                }
                toPrint = "wanna filter for price? 0 yes, anything no";
                LinePrinter.getInstance().print(toPrint);
                switch(reader.readLine()){
                    case "0":
                        toPrint = "if you want a minimum price press 0, anything else to skip: ";
                        LinePrinter.getInstance().print(toPrint);
                        switch(reader.readLine()){
                            case "0":
                                toPrint = "insert minimum price: ";
                                LinePrinter.getInstance().print(toPrint);
                                minPrice = reader.readLine();
                            default:
                                break;
                        }
                        toPrint = "if you want a maximum price press 0, anything else to skip: ";
                        LinePrinter.getInstance().print(toPrint);
                        switch(reader.readLine()){
                            case "0":
                                toPrint = "insert maximum price: ";
                                LinePrinter.getInstance().print(toPrint);
                                maxPrice = reader.readLine();
                            default:
                                break;
                        }
                    toPrint = "wanna filter for mileage? 0 yes, anything no";
                    LinePrinter.getInstance().print(toPrint);
                        switch(reader.readLine()) {
                            case "0":
                                toPrint = "if you want a minimum mileage press 0, anything else to skip: ";
                                LinePrinter.getInstance().print(toPrint);
                                switch (reader.readLine()) {
                                    case "0":
                                        toPrint = "insert minimum mileage: ";
                                        LinePrinter.getInstance().print(toPrint);
                                        minPrice = reader.readLine();
                                    default:
                                        break;
                                }
                                toPrint = "if you want a maximum mileage press 0, anything else to skip: ";
                                LinePrinter.getInstance().print(toPrint);
                                switch (reader.readLine()) {
                                    case "0":
                                        toPrint = "insert maximum mileage: ";
                                        LinePrinter.getInstance().print(toPrint);
                                        maxPrice = reader.readLine();
                                    default:
                                        break;
                                }
                        }
                    default:
                        break;
                }
                break;
            case "1":
                //todo go to controller
            default:
                searchAd();
        }
    }
}
