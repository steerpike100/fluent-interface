package tech.verenti.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import tech.verenti.pages.AbstractPage;

import static tech.verenti.DriverFactory.getChromeDriver;
import static tech.verenti.DriverFactory.getWebDriverWait;
import static tech.verenti.pages.AbstractPage.getAbstractPage;

public class LocalNewsPageActionController {

    WebDriver driver = getChromeDriver();
    WebDriverWait wait = getWebDriverWait();
    AbstractPage abstractPage = getAbstractPage();

    public LocalNewsPageActionController clickNewsButton() {
        WebElement newsButton = driver.findElement(By.className("orb-nav-news"));
        newsButton.click();
        return this;
    }

    public LocalNewsPageActionController enterLocality(String value) {
        WebElement localNews = driver.findElement(By.id("ls-c-search__input-label"));
        abstractPage.TypeInField(localNews, value);
        localNews.sendKeys(value);
        return this;
    }

    public LocalNewsPageActionController setAsLocalNews(){
        WebElement yesButton = driver.findElement(By.cssSelector(".gs-u-display-inline-block > button"));
        yesButton.click();
        return this;
    }

    public static By localNewsSavedDiv() {
        return By.className("js-chosen-region");
    }

    public static By locationHeader() {
        return By.cssSelector("#skip-to-content");
    }


    public LocalNewsPageActionController setDistance(DistanceFrom value) {
        WebElement setDistanceButton = driver.findElement(By.xpath(value.toString()));
        setDistanceButton.click();
        return this;
    }


}
