package pluralsight.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static pluralsight.DriverFactory.getChromeDriver;
import static pluralsight.DriverFactory.getWebDriverWait;

public class EnglandHomePageActionController {

    WebDriver driver = getChromeDriver();
    WebDriverWait wait = getWebDriverWait();

    public EnglandHomePageActionController clickLocalNewsButton() {
        WebElement newsButton = driver.findElement(By.xpath("//nav[@class='navigation-wide-list navigation-wide-list--secondary']//span"));
        newsButton.click();
        return this;
    }


}
