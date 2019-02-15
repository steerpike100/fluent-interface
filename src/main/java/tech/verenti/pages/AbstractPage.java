package tech.verenti.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tech.verenti.utils.TestBase;
import tech.verenti.utils.VerifyController;

public class AbstractPage extends PageBase {

    private VerifyController verify;

    public AbstractPage(WebDriver driver){
        this.driver=driver;
    }

    private AbstractPage(VerifyController verify){
        this.verify = verify;
    }

    public VerifyController verify(){
        return verify;
    }

    public static AbstractPage getAbstractPage(WebDriver driver) {
        return new AbstractPage(new VerifyController(driver));
    }


    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }


    public AbstractPage verifyIsDisplayed(By element){
        Assert.assertTrue(driver.findElement(element).isDisplayed());
        return this;
    }



}
