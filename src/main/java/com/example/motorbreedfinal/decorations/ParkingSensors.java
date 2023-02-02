package com.example.motorbreedfinal.decorations;

import com.example.motorbreedfinal.model.Vehicle;

public class ParkingSensors extends VehicleDecorator {
    private int price;

    private int index;

    public ParkingSensors(Vehicle vehicle) {
        super(vehicle);
        this.setPrice(200);
        this.setIndex(4);

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

    private String applyParkingSensors(String preliminaryDecorations) {
        return preliminaryDecorations.substring(0, index) + '1'
                + preliminaryDecorations.substring(inde
                x + 1);
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
        preliminaryDecorations = this.applyParkingSensors(preliminaryDecorations);
        return preliminaryDecorations;
    }
}
