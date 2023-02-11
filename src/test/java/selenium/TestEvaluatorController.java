package selenium;

import com.example.motorbreedfinal.evaluator.EvaluatorController;
import com.example.motorbreedfinal.model.Car;
import com.example.motorbreedfinal.model.Vehicle;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** @author : Luigi Talamo matricola 0272522
 */

/* The test verifies the accuracy of the Evaluator class. What happens here is that we have a car whose value is calculated
    by the evaluator class, and then this value is confronted with the real one of a car ad with the exact same
    specs. As we can see the difference between these two prices is less than 500, which is pretty accurate
 */


public class TestEvaluatorController {

    @Test
    void testCalculatePrice() {
        Vehicle car = new Car();
        car.setBrand("Fiat");
        car.setModel("Panda");
        car.setHorsepower(69);
        car.setFuelType("Hybrid-Gas");
        car.setProductionYear("2020");
        car.setMileage(31200);

        EvaluatorController evaluatorController = new EvaluatorController();
        int evaluatedPrice = evaluatorController.calculatePrice(car);

        System.setProperty("webdriver.chrome.driver", "src/test/java/selenium/Driver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.autoscout24.it/lst/fiat/panda/ft_gpl?fregfrom=2020&fregto=2021&sort=standard&desc=0&atype=C&ustate=N%2CU&damaged_listing=exclude&eq=140%2C38&fuel=L&powertype=kw&search_id=1cgs410pu4x#3b93ba16-b829-45bf-af34-a8aaf3155867");
        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"3b93ba16-b829-45bf-af34-a8aaf3155867\"]"));

        int actualPrice = Integer.parseInt(String.valueOf(webElement.getAttribute("data-price")));

        assertTrue(Math.abs(actualPrice-evaluatedPrice)<500);
        driver.close();
    }
}
