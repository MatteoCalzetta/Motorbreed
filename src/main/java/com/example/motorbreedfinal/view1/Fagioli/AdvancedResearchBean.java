package com.example.motorbreedfinal.view1.fagioli;

public class AdvancedResearchBean extends ResearchBean{

    private String model;
    private String fuelType;
    private String productionYear;
    private String startingHP;
    private String maxHP;
    private String transmission;
    private String decorations;
    public AdvancedResearchBean() {
        super();
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getStartingHP() {
        return startingHP;
    }

    public void setStartingHP(String startingHP) {
        this.startingHP = startingHP;
    }

    public String getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(String maxHP) {
        this.maxHP = maxHP;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDecorations() {
        return decorations;
    }

    public void setDecorations(String decorations) {
        this.decorations = decorations;
    }

    public void setData(String brand, String model, String fuelType, String productionYear, String startingHP, String maxHP, String transmission, String startingPrice, String maxPrice, String startMileage, String maxMileage, String decorations) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.productionYear = productionYear;
        this.startingHP = startingHP;
        this.maxHP = maxHP;
        this.transmission = transmission;
        this.startingPrice = startingPrice;
        this.maxPrice = maxPrice;
        this.startingMileage = startMileage;
        this.maxMileage = maxMileage;
        this.decorations = decorations;
    }

    @Override
    public boolean isDataValid(){
        return (isNotEmptyAndValid(startingPrice) && isNotEmptyAndValid(maxPrice) &&
                isNotEmptyAndValid(startingMileage) && isNotEmptyAndValid(maxMileage) &&
                isNotEmptyAndValid(startingHP) && isNotEmptyAndValid(maxHP) &&
                isNotGreater(startingPrice, maxPrice) && isNotGreater(startingMileage, maxMileage)) &&
                isNotGreater(startingHP, maxHP);
    }
}
