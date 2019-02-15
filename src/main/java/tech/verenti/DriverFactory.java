package tech.verenti;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverFactory() {
        //prevent instantiation
    }

    public static WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("profile.managed_default_content_settings.geolocation", 2);
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\pluralsight\\fluent-interface\\src\\main\\drivers\\chromedriver.exe");
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static WebDriverWait getWebDriverWait() {
        if (wait == null) {
            wait = new WebDriverWait(getChromeDriver(), 5);

        }
        return wait;
    }

}
