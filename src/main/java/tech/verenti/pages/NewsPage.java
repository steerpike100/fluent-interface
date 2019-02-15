package tech.verenti.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.testng.Assert.assertTrue;

public class NewsPage extends PageBase {

    public NewsPage(WebDriver driver){
        this.driver=driver;
    }

    public NewsPage clickNewsButton() {
        WebElement newsButton = driver.findElement(By.className("orb-nav-news"));
        newsButton.click();
        return this;
    }

    public NewsPage clickEnglandButton() throws InterruptedException {
        WebElement englandButton = driver.findElement(By.linkText("England"));
        englandButton.click();
        Thread.sleep(2000);
        return this;
    }

    public NewsPage clickNIrelanddButton() {
        WebElement nirelandButton = driver.findElement(By.linkText("N.Ireland"));
        nirelandButton.click();
        return this;
    }

    public NewsPage clickScotlandButton() {
        WebElement scotlandButton = driver.findElement(By.linkText("Scotland"));
        scotlandButton.click();
        return this;
    }

    public NewsPage clickAlbaButton() {
        WebElement albaButton = driver.findElement(By.linkText("Alba"));
        albaButton.click();
        return this;
    }

    public NewsPage clickWalesButton() {
        WebElement walesButton = driver.findElement(By.linkText("Wales"));
        walesButton.click();
        return this;
    }

    public NewsPage clickCymruButton() {
        WebElement cymruButton = driver.findElement(By.linkText("Cymru"));
        cymruButton.click();
        return this;
    }

    public List<String> categories(){
        List<WebElement> regions = driver.findElements(By.xpath("//nav[@class='orb-nav']//ul//li//a"));

        assertTrue(((List) regions).size() !=0, "List is empty, filed to collect categories");

        return regions.stream()
                .map(WebElement::getText)
                .collect(toList());
    }

}
