package pluralsight.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pluralsight.utils.VerifyController;

import static pluralsight.DriverFactory.getChromeDriver;

public class AbstractPage {

    protected WebDriver driver = getChromeDriver();
    private VerifyController verify;

    private AbstractPage() {
        // hide it
    }

    private AbstractPage(VerifyController verify){
        this.verify = verify;
    }

    public VerifyController verify(){
        return verify;
    }

    public static AbstractPage getAbstractPage() {
        return new AbstractPage(new VerifyController());
    }


    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }


    public AbstractPage verifyIsDisplayed(By element){
        Assert.assertTrue(driver.findElement(element).isDisplayed());
        return this;
    }

    public AbstractPage TypeInField(WebElement element, String value){
        String val = value;
        element.clear();

        for (int i = 0; i < val.length(); i++){
            char c = val.charAt(i);
            String s = new StringBuilder().append(c).toString();
            element.sendKeys(s);
        }
        return this;
    }

}
