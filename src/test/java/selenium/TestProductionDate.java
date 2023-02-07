package selenium;

import com.example.motorbreedfinal.model.Car;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.jupiter.api.Assertions.assertTrue;

/** @author : Matteo Calzetta matricola 0273429
 */

public class TestProductionDate {

    @Test
    void testDate(){
        Car car = new Car();
        car.setBrand("Dodge");
        car.setModel("Challenger");
        car.setHorsepower(335);
        car.setFuelType("Gasoline");
        car.setProductionYear("1970");

        System.setProperty("webdriver.chrome.driver", "src/test/java/selenium/Driver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://it.wikipedia.org/wiki/Dodge_Challenger");
        WebElement webElement = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[5]/div[1]/p[1]/a[3]"));

        int actualProductionYear= Integer.parseInt(String.valueOf(webElement.getAttribute("title")));

        assertTrue(Math.abs(Integer.parseInt(car.getProductionYear())-actualProductionYear)<1);
        driver.close();
    }
}
