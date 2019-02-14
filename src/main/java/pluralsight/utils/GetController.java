package pluralsight.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.testng.Assert.assertTrue;
import static pluralsight.DriverFactory.getChromeDriver;

public class GetController {

    WebDriver driver = getChromeDriver();

    private List<String> regions(){
        List<WebElement> regions = driver.findElements(By.xpath("//li[@class='gs-o-list-ui__item--flush gel-long-primer gs-u-display-block nw-c-nav__secondary-menuitem-container']//span"));

        assertTrue(((List) regions).size() !=0, "List is emoty, filed to collect regions");

        return regions.stream()
                .map(WebElement::getText)
                .collect(toList());
    }

}
