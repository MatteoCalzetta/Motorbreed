package com.example.motorbreedfinal.Evaluator;

import com.example.motorbreedfinal.Controller.InsertionController;
import com.example.motorbreedfinal.Model.Vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EvaluatorController {

    EvaluatorControllerG evaluatorControllerG;

    private LocalDateTime dateTime = LocalDateTime.now();

    private String date = dateTime.toString().substring(0,4);

    private int finalPrice = 0;

    private Vehicle myVehicle;

    private EvaluatorBean evaluatorBean;

    InsertionController insertionController;


    public void showResult(){
        EvaluatorBoundary evaluatorBoundary = new EvaluatorBoundary();
        evaluatorControllerG = evaluatorBoundary.startEvaluator();
        evaluatorControllerG.setEvaluatorController(this);

        evaluatorBean = new EvaluatorBean(myVehicle.getBrand(), myVehicle.getFuelType(), myVehicle.getModel(), String.valueOf(myVehicle.getHorsepower()), String.valueOf(myVehicle.getMileage()), myVehicle.getProductionYear(), finalPrice);

        evaluatorControllerG.setData(evaluatorBean);

    }

    public void calculatePrice(Vehicle vehicle) {

        int startingPrice = 0;

        ArrayList<String> brands = new ArrayList<String>();
        brands.add("Fiat");
        brands.add("Mercedes");
        brands.add("BMW");
        brands.add("Alfa Romeo");

        ArrayList<String> models = new ArrayList<String>();
        models.add("Panda");
        models.add("Punto");
        models.add("C class");
        models.add("B class");
        models.add("iX M60");
        models.add("X4");
        models.add("Giulia");
        models.add("Mito");

        if(brands.contains(vehicle.getBrand())) {

            if (models.contains(vehicle.getModel())) {

                switch (vehicle.getBrand()) {
                    case "Fiat" -> {
                        startingPrice = 10000;
                        if ((vehicle.getModel().equals("Panda")) || vehicle.getModel().equals("Punto")) {
                            startingPrice += 4700;
                        }
                    }
                    case "Mercedes" -> {
                        startingPrice = 25000;
                        if (vehicle.getModel().equals("C class")) {

                            startingPrice += 25000;

                        } else if (vehicle.getModel().equals("B class")) {

                            startingPrice += 1000;
                        }
                    }
                    case "BMW" -> {
                        startingPrice = 30000;
                        if (vehicle.getModel().equals("iX M60")) {

                            startingPrice += 100000;

                        } else if (vehicle.getModel().equals("X4")) {
                            startingPrice += 32000;
                        }
                    }
                    case "Alfa Romeo" -> {
                        startingPrice = 10000;
                        if (vehicle.getModel().equals("Mito")) {

                            startingPrice += 3500;

                        } else if (vehicle.getModel().equals("Giulia")) {
                            startingPrice += 35000;
                        }
                    }
                }


                if (vehicle.getMileage() != 0) {
                    startingPrice -= (int) (startingPrice * 0.000003* vehicle.getMileage());
                }

                if (!(Integer.parseInt(date) - Integer.parseInt(vehicle.getProductionYear()) == 0)) {
                    startingPrice = startingPrice - 250 * (Integer.parseInt(date) - Integer.parseInt(vehicle.getProductionYear()));
                    if(startingPrice < 0){
                        startingPrice = 0;
                    }
                }

                startingPrice += 10 * vehicle.getHorsepower();


                if (vehicle.getFuelType().equals("Hybrid-Gas")) {
                    startingPrice += 1400;

                } else if (vehicle.getFuelType().equals("Electric")) {
                    startingPrice *= 1.25;
                }

                finalPrice = startingPrice + vehicle.getCarAdditionalPrice();

                myVehicle = vehicle;

                showResult();

            }else {
                finalPrice = 0;
                myVehicle=vehicle;
                showResult();
            }


        }else {

            finalPrice = 0;

            myVehicle = vehicle;

            showResult();
        }
    }

    public void setPriceOnScreen(String evaluatedPrice) {
        insertionController.setPriceOnScreen(evaluatedPrice);
    }

    public void setInsertionController(InsertionController insertionController){
        this.insertionController = insertionController;
    }
}
