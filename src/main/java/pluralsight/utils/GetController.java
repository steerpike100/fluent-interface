package pluralsight.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static pluralsight.DriverFactory.getChromeDriver;

public class GetController {

    WebDriver driver = getChromeDriver();

    private List<String> regions(){
        List<WebElement> regions = driver.findElements(By.xpath(""));

        assertTrue(((List) regions).size() !=0, "List is emoty, filed to collect regions");
    }

}
