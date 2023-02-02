package com.example.motorbreedfinal.decorations;

import com.example.motorbreedfinal.model.Vehicle;

public class CruiseControl extends VehicleDecorator {

    private int index;

    private int price;

    public CruiseControl(Vehicle vehicle) {
        super(vehicle);
        this.setPrice(600);
        this.setIndex(0);
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public void setIndex(int index){
        this.index = index;
    }


    protected int applyPrice(int input) {

        return this.price + input;
    }

    private String applyDecorations(String preliminaryDecorations) {
        return preliminaryDecorations.substring(0, index) + '1'
                + preliminaryDecorations.substring(index + 1);
    }

    @Override
    public int setAdditionalPrice() {
        int preliminaryResults = super.setAdditionalPrice();
        preliminaryResults = this.applyPrice(preliminaryResults);
        return preliminaryResults;
    }

    @Override
    public String setDecorations() {
        String preliminaryDecorations = super.setDecorations();
        preliminaryDecorations = this.applyDecorations(preliminaryDecorations);
        return preliminaryDecorations;
    }
}
