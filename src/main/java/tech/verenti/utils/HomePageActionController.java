package tech.verenti.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static tech.verenti.DriverFactory.getChromeDriver;

public class HomePageActionController {

    WebDriver driver = getChromeDriver();

    public HomePageActionController clickNewsButton() {
        WebElement newsButton = driver.findElement(By.className("orb-nav-news"));
        newsButton.click();
        return this;
    }


}
