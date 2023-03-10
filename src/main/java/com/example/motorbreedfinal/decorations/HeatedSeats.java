package com.example.motorbreedfinal.decorations;

import com.example.motorbreedfinal.model.Vehicle;

public class HeatedSeats extends VehicleDecorator {
    private int price;

    private int index;

    public HeatedSeats(Vehicle vehicle) {
        super(vehicle);
        this.setPrice(2500);
        this.setIndex(2);

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

    private String applyHeatedSeats(String preliminaryDecorations) {
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
        preliminaryDecorations = this.applyHeatedSeats(preliminaryDecorations);
        return preliminaryDecorations;
    }
}
