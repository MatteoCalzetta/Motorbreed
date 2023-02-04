package com.example.motorbreedfinal.view1.fagioli;

import java.time.LocalDateTime;

public class CarBean {
    private boolean insurance;
    private String brand;
    private String model;
    private int mileage;
    private String productionYear;
    private int horsepower;
    private String fuelType;
    private String transmission;
    private String licencePlate;

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public String getCarBeanBrand() {
        return brand;
    }

    public void setCarBeanBrand(String brand) {
        this.brand = brand;
    }

    public String getCarBeanModel() {
        return model;
    }

    public void setCarBeanModel(String model) {
        this.model = model;
    }

    public int getCarBeanMileage() {
        return mileage;
    }

    public void setCarBeanMileage(int value) {
        mileage =value;
    }

    public String getBeanProductionYear() {
        return productionYear;
    }

    public void setBeanProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public int getBeanHorsepower() {
        return horsepower;
    }

    public void setBeanHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getBeanFuelType() {
        return fuelType;
    }

    public void setBeanFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public void setImmatricolationDate(String immatricolationDate) {
        this.immatricolationYear = immatricolationDate;
    }


    public boolean[] getDecorationsArray() {
        return decorationsArray;
    }

    public void setDecorationsArray(boolean[] decorationsArray) {
        this.decorationsArray = decorationsArray;
    }

    public String getImmatricolationYear() {
        return immatricolationYear;
    }

    public void setImmatricolationYear(String immatricolationYear) {
        this.immatricolationYear = immatricolationYear;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private boolean[] decorationsArray = new boolean[5];

    private String immatricolationYear;

    private LocalDateTime dateTime = LocalDateTime.now();

    private String date = dateTime.toString().substring(0,4);

    public boolean validate() {

        try {
            if (brand.isEmpty() || model.isEmpty() || mileage < 0 || horsepower < 0 || fuelType.isEmpty() || transmission.isEmpty()
                    || licencePlate.isEmpty() || (Integer.parseInt(productionYear)) > (Integer.parseInt(date)) || (Integer.parseInt(immatricolationYear)) > (Integer.parseInt(date)) ||
                    Integer.parseInt(productionYear) > Integer.parseInt(immatricolationYear) || productionYear.length() != 4 || immatricolationYear.length() != 4) {

                return false;
            }

        }catch (Exception e){
            return false;
        }

        return true;
    }
}
