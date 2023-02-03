package com.example.motorbreedfinal.evaluator;

import com.example.motorbreedfinal.controller.InsertionController;
import com.example.motorbreedfinal.model.Vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EvaluatorController {

    EvaluatorControllerG evaluatorControllerG;

    private LocalDateTime dateTime = LocalDateTime.now();

    private String date = dateTime.toString().substring(0,4);

    private int finalPrice = 0;

    private Vehicle myVehicle;

    InsertionController insertionController;


    public void showResult(){
        EvaluatorBoundary evaluatorBoundary = new EvaluatorBoundary();
        evaluatorControllerG = evaluatorBoundary.startEvaluator();
        evaluatorControllerG.setEvaluatorController(this);

        EvaluatorBean evaluatorBean = new EvaluatorBean(myVehicle.getBrand(), myVehicle.getFuelType(), myVehicle.getModel(), String.valueOf(myVehicle.getHorsepower()), String.valueOf(myVehicle.getMileage()), myVehicle.getProductionYear(), finalPrice);

        evaluatorControllerG.setData(evaluatorBean);

    }


    private int evaluateBrandModel(Vehicle vehicle){
        int startingPrice = 0;

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

            default -> {
                finalPrice = 0;
                myVehicle = vehicle;
                showResult();
                return finalPrice;
            }
        }
        return startingPrice;
    }

    private int evaluateFuelType(Vehicle vehicle, int startingPrice){

        if (vehicle.getFuelType().equals("Hybrid-Gas")) {
            startingPrice += 1400;

        } else if (vehicle.getFuelType().equals("Electric")) {
            startingPrice *= 1.25;
        }

        return startingPrice;
    }

    public int calculatePrice(Vehicle vehicle) {

        int startingPrice = 0;

        ArrayList<String> brands = new ArrayList<>();
        brands.add("Fiat");
        brands.add("Mercedes");
        brands.add("BMW");
        brands.add("Alfa Romeo");

        ArrayList<String> models = new ArrayList<>();
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

                startingPrice = evaluateBrandModel(vehicle);

                if (vehicle.getMileage() != 0) {
                    startingPrice -= (int) (startingPrice * 0.000003* vehicle.getMileage());
                }

                if (Integer.parseInt(date) - Integer.parseInt(vehicle.getProductionYear())!= 0) {
                    startingPrice = startingPrice - 250 * (Integer.parseInt(date) - Integer.parseInt(vehicle.getProductionYear()));
                    if(startingPrice < 0){
                        startingPrice = 0;
                    }
                }

                startingPrice += 10 * vehicle.getHorsepower();

                startingPrice = evaluateFuelType(vehicle, startingPrice);

                finalPrice = startingPrice + vehicle.getCarAdditionalPrice();

            }else {
                finalPrice = 0;
            }


        }else {

            finalPrice = 0;

        }
        myVehicle = vehicle;
        return finalPrice;
    }

    public void setPriceOnScreen(String evaluatedPrice) {
        insertionController.setPriceOnScreen(evaluatedPrice);
    }

    public void setInsertionController(InsertionController insertionController){
        this.insertionController = insertionController;
    }
}
