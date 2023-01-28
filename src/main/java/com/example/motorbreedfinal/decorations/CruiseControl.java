package com.example.motorbreedfinal.decorations;

import com.example.motorbreedfinal.model.Vehicle;

public class CruiseControl extends VehicleDecorator {

    private int index;

    private int Price;

    public CruiseControl(Vehicle vehicle) {
        super(vehicle);
        this.setPrice(600);
        this.setIndex(0);

    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public void setIndex(int index){
        this.index = index;
    }


    protected int applyPrice(int input) {

        int output = this.Price + input;
        return output;
    }

    private String applyDecorations(String preliminaryDecorations) {
        String outputString = preliminaryDecorations.substring(0, index) + '1'
                + preliminaryDecorations.substring(index + 1);
        return outputString;
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
