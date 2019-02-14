package pluralsight.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pluralsight.pages.AbstractPage;

import static pluralsight.DriverFactory.getChromeDriver;
import static pluralsight.DriverFactory.getWebDriverWait;

public class VerifyController {

    WebDriver driver = getChromeDriver();
    WebDriverWait wait = getWebDriverWait();

    public VerifyController verifyIsDisplayed(By element){
        Assert.assertTrue(driver.findElement(element).isDisplayed());
        return this;
    }
}
