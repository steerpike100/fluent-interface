package tech.verenti.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends PageBase{

    public HomePage(WebDriver driver){
        this.driver = driver;
    }


    public HomePage clickNewsButton() {
        WebElement newsButton = driver.findElement(By.className("orb-nav-news"));
        newsButton.click();
        return this;
    }




}
