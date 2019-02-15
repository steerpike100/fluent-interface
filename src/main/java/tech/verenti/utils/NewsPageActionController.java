package tech.verenti.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static tech.verenti.DriverFactory.getChromeDriver;

public class NewsPageActionController {

    WebDriver driver = getChromeDriver();

    public NewsPageActionController clickNewsButton() {
        WebElement newsButton = driver.findElement(By.className("orb-nav-news"));
        newsButton.click();
        return this;
    }

    public NewsPageActionController clickEnglandButton() throws InterruptedException {
        WebElement englandButton = driver.findElement(By.linkText("England"));
        englandButton.click();
        Thread.sleep(2000);
        return this;
    }

    public NewsPageActionController clickNIrelanddButton() {
        WebElement nirelandButton = driver.findElement(By.linkText("N.Ireland"));
        nirelandButton.click();
        return this;
    }

    public NewsPageActionController clickScotlandButton() {
        WebElement scotlandButton = driver.findElement(By.linkText("Scotland"));
        scotlandButton.click();
        return this;
    }

    public NewsPageActionController clickAlbaButton() {
        WebElement albaButton = driver.findElement(By.linkText("Alba"));
        albaButton.click();
        return this;
    }

    public NewsPageActionController clickWalesButton() {
        WebElement walesButton = driver.findElement(By.linkText("Wales"));
        walesButton.click();
        return this;
    }

    public NewsPageActionController clickCymruButton() {
        WebElement cymruButton = driver.findElement(By.linkText("Cymru"));
        cymruButton.click();
        return this;
    }


}
