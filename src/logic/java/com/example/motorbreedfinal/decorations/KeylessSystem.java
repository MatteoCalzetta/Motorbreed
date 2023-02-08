package com.example.motorbreedfinal.decorations;

import com.example.motorbreedfinal.model.Vehicle;

public class KeylessSystem extends VehicleDecorator {
    private int price;

    private int index;

    public KeylessSystem(Vehicle vehicle) {
        super(vehicle);
        this.setPrice(2000);
        this.setIndex(1);

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

    private String applyKeylessSystem(String preliminaryDecorations) {
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
        preliminaryDecorations = this.applyKeylessSystem(preliminaryDecorations);
        return preliminaryDecorations;
    }
}
