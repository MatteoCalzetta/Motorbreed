package com.example.motorbreedfinal.decorations;

import com.example.motorbreedfinal.model.Vehicle;

public abstract class VehicleDecorator extends Vehicle {
    private Vehicle vehicle;

    protected VehicleDecorator(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public int setAdditionalPrice() {
        return this.vehicle.setAdditionalPrice();
    }

    @Override
    public String setDecorations() {
        return this.vehicle.setDecorations();
    }
}
