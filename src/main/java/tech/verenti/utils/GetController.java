package tech.verenti.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.testng.Assert.assertTrue;
import static tech.verenti.DriverFactory.getChromeDriver;

public class GetController {

    WebDriver driver = getChromeDriver();

    public List<String> categories(){
        List<WebElement> regions = driver.findElements(By.xpath("//nav[@class='orb-nav']//ul//li//a"));

        assertTrue(((List) regions).size() !=0, "List is empty, filed to collect categories");

        return regions.stream()
                .map(WebElement::getText)
                .collect(toList());
    }

}
