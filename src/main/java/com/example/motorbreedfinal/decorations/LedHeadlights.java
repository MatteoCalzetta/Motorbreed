package com.example.motorbreedfinal.decorations;

import com.example.motorbreedfinal.model.Vehicle;

public class LedHeadlights extends VehicleDecorator {
    private int price;

    private int index;

    public LedHeadlights(Vehicle vehicle) {
        super(vehicle);
        this.setPrice(500);
        this.setIndex(3);

    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIndex(int index){
        this.index = index;
    }

    protected int applyPrice(int input) {

        int output = this.price + input;
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
