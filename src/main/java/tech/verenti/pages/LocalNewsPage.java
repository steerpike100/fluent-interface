package tech.verenti.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import tech.verenti.utils.DistanceFrom;

public class LocalNewsPage extends PageBase {

    private By locationHeader = By.cssSelector("#skip-to-content");
    private By yesButton = By.cssSelector(".gs-u-display-inline-block > button");

    public LocalNewsPage(WebDriver driver){
        this.driver = driver;
    }

    public LocalNewsPage clickNewsButton() {
        WebElement newsButton = driver.findElement(By.className("orb-nav-news"));
        newsButton.click();
        return this;
    }

//    public LocalNewsPage enterLocality(String value) {
//        WebElement localNews = driver.findElement(By.id("ls-c-search__input-label"));
//        type("", value);
//        localNews.sendKeys(value);
//        return this;
//    }

    public LocalNewsPage setAsLocalNews(){
        waitForIsDisplayed(yesButton, 10);
        click(yesButton);
        return this;
    }

    public static By localNewsSavedDiv() {
        return By.className("js-chosen-region");
    }

    public By checkLocationHeader() {
        return locationHeader;
    }

    public void checkDisplayed() {
        assert (isDisplayed(localNewsPage().checkLocationHeader(), 10));

    }
        public LocalNewsPage setDistance (DistanceFrom value){
            WebElement setDistanceButton = driver.findElement(By.xpath(value.toString()));
            setDistanceButton.click();
            return this;
        }


    }
