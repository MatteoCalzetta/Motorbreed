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

        return this.price + input;
    }

    private String applyLedHeadlights(String preliminaryDecorations) {
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
        preliminaryDecorations = this.applyLedHeadlights(preliminaryDecorations);
        return preliminaryDecorations;
    }

}
