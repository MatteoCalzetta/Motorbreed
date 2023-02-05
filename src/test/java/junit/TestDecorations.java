package junit;

import com.example.motorbreedfinal.decorations.DecorateCar;
import com.example.motorbreedfinal.model.Car;
import com.example.motorbreedfinal.model.Vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** @author : Luigi Talamo matricola 0272522
 */

/* This test verifies that the added value to the original Car Object coincides with the expected one.
   The decoration process adds the value of the decoration applied to the Car Object.

   In this test all decorations are applied: so the additionalPrice at the end of the process is the
   sum of the prices of all decorations (5800).

   The test is set up to be successful.
*/

class TestDecorations {

    @org.junit.jupiter.api.Test
    void getAdditionalCost(){
        Car car = new Car();

        Vehicle decoratedVehicle;

        boolean[] decoration = {true,true,true,true,true};

        decoratedVehicle = DecorateCar.addDecorations(decoration, car);

        assertEquals(5800, decoratedVehicle.setAdditionalPrice());
    }

}
