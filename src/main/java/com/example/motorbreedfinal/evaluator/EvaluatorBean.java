package com.example.motorbreedfinal.evaluator;

public class EvaluatorBean {

    private  String brand;

    private  String model;

    private  String horsepower;

    private String fuelType;

    private  String productionYear;

    private String finalPrice;

    private String mileage;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(String horsepower) {
        this.horsepower = horsepower;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public EvaluatorBean(String brand, String fuelType, String model, String horsepower, String mileage, String productionYear, int finalPrice) {

        this.brand = brand;

        this.fuelType = fuelType;

        this.model = model;

        this.horsepower = horsepower;

        this.mileage = mileage + " km";

        this.productionYear = productionYear;

        if(finalPrice == 0) {
            this.finalPrice = "Price was not Evaluable";
        }else {
            //TODO
            //this.finalPrice = finalPrice + "€";

            this.finalPrice = String.valueOf(finalPrice);
        }

    }
}
