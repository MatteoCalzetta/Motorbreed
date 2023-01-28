package com.example.motorbreedfinal.model;

// Vehicle in Decorator è l interfaccia comune

public abstract class Vehicle {
    protected String brand;

    protected String model;
    protected int Mileage;
    protected String productionYear;

    protected int horsepower;
    protected String fuelType;
    protected int additionalPrice;

    protected String decorations = "00000";

    public String getDecorations() {
        return decorations;
    }

    public void setDecorations(String decorations) {
        this.decorations = decorations;
    }

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

    public int getMileage() {
        return Mileage;
    }

    public void setMileage(int mileage) {
        Mileage = mileage;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getCarAdditionalPrice() {
        return additionalPrice;
    }

    public void setCarAdditionalPrice(int additionalPrice) {
        this.additionalPrice = additionalPrice;
    }

    public abstract int setAdditionalPrice();

    public abstract String setDecorations();

    public void setStatus(Vehicle tempVehicle) {
        this.brand = tempVehicle.getBrand();
        this.model = tempVehicle.getModel();
        this.fuelType = tempVehicle.getFuelType();
        this.horsepower = tempVehicle.getHorsepower();;
        this.productionYear = tempVehicle.getProductionYear();
        this.Mileage = tempVehicle.getMileage();
    }
}
