package com.example.motorbreedfinal.view2;

import com.example.motorbreedfinal.controller.InsertionController;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.CarBean;
import com.example.motorbreedfinal.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class InsertionpageControllerG2 {

    private String toPrint;
    public void insertCar() {
        toPrint = "Inserting car";
        LinePrinter.getInstance().print(toPrint);

        CarBean carBean = createCarbean();

        if(!carBean.validate()){
            toPrint = "Insertion failed . . . repeating steps";
            LinePrinter.getInstance().print(toPrint);
            insertCar();
        }
        InsertionController insertionController = new InsertionController();
        insertionController.insertCar(carBean);
        int evaluatedPrice = insertionController.startEvaluation();

        toPrint = "Our system has evaluated the car you just inserted, here is the price: ";
        LinePrinter.getInstance().print(toPrint);

        if(evaluatedPrice == 0){
            toPrint = "Price was not evaluable, so it is set to 0";
        }else {
            toPrint = String.valueOf(evaluatedPrice);
        }

        LinePrinter.getInstance().print(toPrint);
        toPrint = "Would you like to use it for your ad? By doing so your ad will recieve a quality certification, press 0 if you dont, 1 otherwise";
        LinePrinter.getInstance().print(toPrint);

        AdBean adBean = new AdBean();

        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        adBean.setInsertionDate(out);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if(reader.readLine().equals("1")) {
                adBean.setPriceCertification(true);
                adBean.setCost(evaluatedPrice);
            }else {
                toPrint = "It seems you didnt use our Evaluator :( , type in the price you want to use";
                LinePrinter.getInstance().print(toPrint);
                adBean.setPriceCertification(false);
                adBean.setCost(Integer.parseInt(reader.readLine()));
            }

            toPrint = "Do you want to add a description of your car? 0 for no, 1 for yes";
            LinePrinter.getInstance().print(toPrint);
            if(reader.readLine().equals("1")){
                toPrint = "Insert your description:";
                LinePrinter.getInstance().print(toPrint);
                adBean.setDescription(reader.readLine());
            }else {
                adBean.setDescription("");
            }

            toPrint = "Insert the location of your vehicle";
            LinePrinter.getInstance().print(toPrint);
            adBean.setLocation(reader.readLine());

            adBean.setNumberOfClicks(0);

            insertionController.insertAd(adBean);

        }catch(IOException e){
            LinePrinter.getInstance().print("Something went wrong");
            insertCar();
        }

        SellerHomepageControllerG2 sellerHomepageControllerG2 = new SellerHomepageControllerG2();
        sellerHomepageControllerG2.setNameSurname(LoggedUser.getInstance().getSeller().getFirstName(), LoggedUser.getInstance().getSeller().getLastName());
    }



    private CarBean createCarbean() {
        CarBean carBean = new CarBean();

        toPrint = "Insert brand...";
        LinePrinter.getInstance().print(toPrint);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            carBean.setBrand(reader.readLine());

            toPrint = "Insert model...";
            LinePrinter.getInstance().print(toPrint);

            carBean.setModel(reader.readLine());

            toPrint = "Insert mileage...";
            LinePrinter.getInstance().print(toPrint);

            carBean.setMileage(Integer.parseInt(reader.readLine()));

            toPrint = "Insert production Year...";
            LinePrinter.getInstance().print(toPrint);

            carBean.setProductionYear(reader.readLine());

            toPrint = "Insert horsepower...";
            LinePrinter.getInstance().print(toPrint);

            carBean.setHorsepower(Integer.parseInt(reader.readLine()));

            toPrint = "Insert transmission...";
            LinePrinter.getInstance().print(toPrint);

            carBean.setTransmission(reader.readLine());

            toPrint = "Insert fuel type...";
            LinePrinter.getInstance().print(toPrint);

            carBean.setFuelType(reader.readLine());

            toPrint = "Insert licence plate...";
            LinePrinter.getInstance().print(toPrint);

            carBean.setLicencePlate(reader.readLine());

            toPrint = "Insert matriculation Year...";
            LinePrinter.getInstance().print(toPrint);

            carBean.setImmatricolationDate(reader.readLine());

            toPrint = "Does your car have insurance ? 0 for no, 1 for yes...";
            LinePrinter.getInstance().print(toPrint);

            carBean.setInsurance(Boolean.parseBoolean(reader.readLine()));

            boolean[] decorationsArray = new boolean[5];

            toPrint = "Does your car have CruiseControl optional ? 0 for no, 1 for yes...";
            LinePrinter.getInstance().print(toPrint);

            decorationsArray[0] = (Boolean.parseBoolean(reader.readLine()));

            toPrint = "Does your car have KeylessSystem optional ? 0 for no, 1 for yes...";
            LinePrinter.getInstance().print(toPrint);

            decorationsArray[1] = (Boolean.parseBoolean(reader.readLine()));

            toPrint = "Does your car have HeatedSeats optional ? 0 for no, 1 for yes...";
            LinePrinter.getInstance().print(toPrint);

            decorationsArray[2] = (Boolean.parseBoolean(reader.readLine()));

            toPrint = "Does your car have LedHeadlights optional ? 0 for no, 1 for yes...";
            LinePrinter.getInstance().print(toPrint);

            decorationsArray[3] = (Boolean.parseBoolean(reader.readLine()));

            toPrint = "Does your car have ParkingSensors optional ? 0 for no, 1 for yes...";
            LinePrinter.getInstance().print(toPrint);

            decorationsArray[4] = (Boolean.parseBoolean(reader.readLine()));

            carBean.setDecorationsArray(decorationsArray);


        }catch (IOException e){
            LinePrinter.getInstance().print("Insertion failed . . . repeating steps");
            insertCar();
        }


        return carBean;
    }
}
