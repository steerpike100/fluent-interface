package tech.verenti.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EnglandHomePage extends PageBase {

    public EnglandHomePage(WebDriver driver){
        this.driver = driver;
    }

    public EnglandHomePage clickLocalNewsButton() {
        WebElement newsButton = driver.findElement(By.xpath("\t//a[contains(@class,'navigation-wide-list__link navigation-wide-list__link--first')]//span[contains(text(),'Local News')]"));
        newsButton.click();
        return this;
    }



}