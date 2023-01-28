package com.example.motorbreedfinal.decorations;

import com.example.motorbreedfinal.model.Vehicle;

public abstract class VehicleDecorator extends Vehicle {
    private Vehicle vehicle;

    public VehicleDecorator(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public int setAdditionalPrice() {
        int PriceFromRedirection = this.vehicle.setAdditionalPrice();
        return PriceFromRedirection;
    }

    @Override
    public String setDecorations() {
        String decorationsFromRedirection  = this.vehicle.setDecorations();
        return decorationsFromRedirection;
    }
}
