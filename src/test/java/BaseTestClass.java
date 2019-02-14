import jdk.nashorn.internal.runtime.JSONFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.json.JsonInput;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

import static pluralsight.DriverFactory.getChromeDriver;
import static pluralsight.DriverFactory.getWebDriverWait;


public class BaseTestClass {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void startUpBrowser() {

        driver = getChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        wait = getWebDriverWait();
    }

    @BeforeMethod
    public void goToHome(){
        driver.get("https://www.bbc.co.uk");
    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        driver.close();
    }


}
