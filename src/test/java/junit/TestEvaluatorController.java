package junit;

import com.example.motorbreedfinal.evaluator.EvaluatorController;
import com.example.motorbreedfinal.model.Car;
import com.example.motorbreedfinal.model.Vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** @author : Luigi Talamo matricola 0272522
 */

/* This test begins with the initialization of a Car Object turned to a Vehicle Object thanks to the Liskov's principle.
    By doing so, given that the evaluatorController method "calculatePrice" takes a Vehicle Object in input, it is
    able to perform the calculation knowing only what is strictly necessary about the original Car Object. The other
    attributes of the Car Object are not considered in the evaluation, so they are useless to the Evaluator.


    The "calculatePrice" method calculates and returns the Price of the Car Object. The test only verifies that the "evaluated"
    Price coincides with the expected one. The test is set up to be successful.
*/

class TestEvaluatorController {

    @org.junit.jupiter.api.Test
    void testCalculatePrice(){
        Vehicle car = new Car();
        car.setBrand("Fiat");
        car.setModel("Panda");
        car.setHorsepower(90);
        car.setFuelType("Hybrid-Gas");
        car.setProductionYear("2020");
        car.setMileage(100000);

        EvaluatorController evaluatorController = new EvaluatorController();
        int evaluatedPrice = evaluatorController.calculatePrice(car);

        int expectedEvaluation = 11840;
        assertEquals(evaluatedPrice, expectedEvaluation);
    }
}
