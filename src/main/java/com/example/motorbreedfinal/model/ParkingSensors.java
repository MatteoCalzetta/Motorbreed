package com.example.motorbreedfinal.model;

import com.example.motorbreedfinal.decorations.VehicleDecorator;

public class ParkingSensors extends VehicleDecorator {
    private String name = "parkingSensors Optional";
    private int Price;

    public ParkingSensors(Vehicle vehicle) {
        super(vehicle);
        this.setPrice(200);

    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    protected int applyPrice(int input) {

        int output = this.Price + input;
        return output;
    }

    @Override
    public int setAdditionalPrice() {
        int preliminaryResults = super.setAdditionalPrice();
        preliminaryResults = this.applyPrice(preliminaryResults);
        return preliminaryResults;
    }
}
