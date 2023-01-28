package com.example.motorbreedfinal.model;


// Car nel pattern Decorator è ConcreteComponent

public class Car extends Vehicle{
    private String idCar;
    private String licencePlate;
    private String transmission;
    private boolean insurance;
    private String immatricolationYear;

    public String getIdCar() {
        return idCar;
    }

    public void setIdCar(String idCar) {
        this.idCar = idCar;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getImmatricolationYear() {
        return immatricolationYear;
    }

    public void setImmatricolationYear(String immatricolationYear) {
        this.immatricolationYear = immatricolationYear;
    }

    @Override
    public int setAdditionalPrice() {
        return this.additionalPrice;
    }

    @Override
    public String setDecorations() {
        return this.decorations;
    }

    public void setCarDecorations(String decorations){
        this.decorations = decorations;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    @Override
    public String toString() {
        return "Car{" +
                "idCar='" + idCar + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", transmission='" + transmission + '\'' +
                ", insurance=" + insurance +
                ", immatricolationYear='" + immatricolationYear + '\'' +
                ", decorations='" + decorations + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", Mileage=" + Mileage +
                ", productionYear='" + productionYear + '\'' +
                ", horsepower=" + horsepower +
                ", fuelType='" + fuelType + '\'' +
                ", additionalPrice=" + additionalPrice +
                '}';
    }
}
