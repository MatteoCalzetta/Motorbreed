package com.example.motorbreedfinal.decorations;

import com.example.motorbreedfinal.model.Car;
import com.example.motorbreedfinal.model.Vehicle;

public interface DecorateCar {

    static Vehicle addDecorations(boolean[] decorationsArray, Car car) {
        Vehicle vehicle = car;

        String decorations = "00000";

        int additionalPrice = 0;

        if(decorationsArray[0]) {
           CruiseControl cruiseControl = new CruiseControl(vehicle);
           additionalPrice = cruiseControl.setAdditionalPrice();
           decorations = cruiseControl.setDecorations();

           vehicle = cruiseControl;


        }
        if(decorationsArray[1]) {
            KeylessSystem keylessSystem = new KeylessSystem(vehicle);
            additionalPrice = keylessSystem.setAdditionalPrice();
            decorations = keylessSystem.setDecorations();

            vehicle = keylessSystem;




        }
        if(decorationsArray[2]) {
            HeatedSeats heatedSeats = new HeatedSeats(vehicle);
            additionalPrice = heatedSeats.setAdditionalPrice();
            decorations = heatedSeats.setDecorations();

            vehicle = heatedSeats;



        }
        if(decorationsArray[3]) {
            LedHeadlights ledHeadlights = new LedHeadlights(vehicle);
            additionalPrice = ledHeadlights.setAdditionalPrice();
            decorations = ledHeadlights.setDecorations();

            vehicle = ledHeadlights;


        }
        if(decorationsArray[4]) {
            ParkingSensors parkingSensors =  new ParkingSensors(vehicle);
            additionalPrice = parkingSensors.setAdditionalPrice();
            decorations = parkingSensors.setDecorations();

            vehicle = parkingSensors;
        }

        vehicle.setCarAdditionalPrice(additionalPrice);
        vehicle.setDecorations(decorations);

        return vehicle;
    }
}
