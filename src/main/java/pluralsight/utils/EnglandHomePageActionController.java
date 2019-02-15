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
        WebElement newsButton = driver.findElement(By.xpath("//a[contains(@class,'navigation-wide-list__link navigation-wide-list__link--first')]//span[contains(text(),'Local News')]"));
        newsButton.click();
        return this;
    }


}
