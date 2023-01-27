package com.example.motorbreedfinal.Decorations;

import com.example.motorbreedfinal.Model.Vehicle;

public class ParkingSensors extends VehicleDecorator {
    private int Price;

    private int index;

    public ParkingSensors(Vehicle vehicle) {
        super(vehicle);
        this.setPrice(200);
        this.setIndex(4);

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
