package tech.verenti.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import tech.verenti.pages.PageBase;

public class VerifyController extends PageBase {

    public VerifyController(WebDriver driver){
        this.driver=driver;
    }
    public VerifyController verifyIsDisplayed(By element){
        Assert.assertTrue(driver.findElement(element).isDisplayed());
        return this;
    }
}
