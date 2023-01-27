package com.example.motorbreedfinal.view1.Fagioli;

public class ResearchBean {

    protected String brand;
    protected String startingPrice;
    protected String maxPrice;
    protected String startingMileage;
    protected String maxMileage;

    public ResearchBean() {
    }

    public ResearchBean(String brand, String startingMileage, String maxMileage, String startingPrice, String maxPrice) {
        this.brand = brand;
        this.startingPrice = startingPrice;
        this.maxPrice = maxPrice;
        this.startingMileage = startingMileage;
        this.maxMileage = maxMileage;
    }

    public ResearchBean(String startingPrice, String maxPrice, String startingMileage, String maxMileage) {
        this.startingPrice = startingPrice;
        this.maxPrice = maxPrice;
        this.startingMileage = startingMileage;
        this.maxMileage = maxMileage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(String startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getStartingMileage() {
        return startingMileage;
    }

    public void setStartingMileage(String startingMileage) {
        this.startingMileage = startingMileage;
    }

    public String getMaxMileage() {
        return maxMileage;
    }

    public void setMaxMileage(String maxMileage) {
        this.maxMileage = maxMileage;
    }

    public boolean isDataValid() {
        return (isNotEmptyAndValid(startingPrice) && isNotEmptyAndValid(maxPrice) &&
                isNotEmptyAndValid(startingMileage) && isNotEmptyAndValid(maxMileage) &&
                isNotGreater(startingPrice, maxPrice) && isNotGreater(startingMileage, maxMileage));
    }



    protected boolean isNotEmptyAndValid(String input){
        try{
            if(!input.isEmpty()){
                return  Float.parseFloat(input) > 0;
            }
        } catch(Exception e){
            return false;
        }
        return true;
    }

    protected boolean isNotGreater(String startingValue, String maxValue){
        try{
            if(!startingValue.isEmpty() && !maxValue.isEmpty()){
                return Float.parseFloat(startingValue) <= Float.parseFloat(maxValue);
            }
        } catch(Exception e){
            return false;
        }
        return true;
    }

}
