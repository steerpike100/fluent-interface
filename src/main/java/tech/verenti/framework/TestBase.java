package tech.verenti.framework;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import tech.verenti.webdriver.WebDriverConfigBean;
import tech.verenti.webdriver.WebDriverManager;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import static tech.verenti.webdriver.WebDriverConfigBean.aWebDriverConfig;

public class TestBase {

    protected WebDriver driver = null;
    private static Logger log = Logger.getLogger("devpinoyLogger");

    @BeforeSuite
    @Parameters({"env", "browserName", "platformName","mode"})
    public void startUpBrowser(String env, String browserName, Platform platformName, String mode) {
        EnvironmentConfiguration.populate(env);
        WebDriverConfigBean webDriverConfig = aWebDriverConfig()
                .withDeploymentEnvironment(env)
                .withBrowser(browserName)
                .withPlatform(platformName)
                .withSeleniumMode(mode);

        driver = WebDriverManager.openBrowser(webDriverConfig, getClass());
        String baseURL = EnvironmentConfiguration.getBaseURL();
        log.debug("Using base URL: " + baseURL);

        navigateTo(baseURL);

        waitForPageToLoad();
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        log.debug("test execution completed !!!");
    }

    protected void visit(String url) {
        driver.get(url);
    }

    private void navigateTo(String url) {
        driver.navigate().to(url);
    }


    public String getPageTitle() {
        return driver.getTitle();
    }

    protected void clickOnMenuOption(WebElement element, By locator) {
        Actions builder = new Actions(driver);
        builder.moveToElement(element).build().perform();
        waitForIsDisplayed(locator, 5);
        find(locator).click();
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    protected void click(By locator) {
        find(locator).click();
    }

    protected void type(String inputText, By locator) {
        find(locator).sendKeys(inputText);
    }

    protected void select(By locator, String text) {
        Select dropdown = new Select(find(locator));
        click(locator);
        dropdown.selectByVisibleText(text);
    }

    public void selectByPartOfVisibleText(By locator, String value) throws InterruptedException {

//        List<WebElement> list = findAll(By.name("departurePoint:form:onePicker:0:picker:destination"));
        WebElement dropDown = find(By.name("departurePoint:form:onePicker:0:picker:destination"));
        dropDown.click();

        WebElement element = find(By.xpath("//select[@name=\"departurePoint:form:onePicker:0:picker:destination\"]//option[contains(text(),'" + value + "')]"));
        element.click();

    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void pressDownKey(By locator, Long waitTime) {
        find(locator).sendKeys(Keys.DOWN);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void pressReturnKey(By locator, Long waitTime) {
        find(locator).sendKeys(Keys.RETURN);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void pressSpaceKey(By locator, Long waitTime) {
        find(locator).sendKeys(Keys.SPACE);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pressTab(By locator, Long waitTime) {
        type(Keys.chord(Keys.TAB), locator);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    protected void selectByIndex(By locator) {
        Select select = new Select(find(locator));
        select.selectByIndex(1);
    }

    protected void emptyBeforeType(String inputText, By locator) {
        //Some text boxes require you to empty the current text, otherwise you will just be appending to the content
        find(locator).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        find(locator).sendKeys(inputText);
        find(locator).sendKeys(Keys.RETURN);
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected void clear(By locator) {
        find(locator).clear();
    }

    protected void refresh() {
        driver.navigate().refresh();
    }

    protected Boolean isDisplayed(By locator, Integer... timeout) {
        try {
            return find(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
        timeout = timeout != null ? timeout : 5;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    protected Boolean waitForIsDisplayed(By locator, Integer... timeout) {
        try {
            waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                    (timeout.length > 0 ? timeout[0] : null));
        } catch (org.openqa.selenium.TimeoutException exception) {
            System.out.println("The expected element: " + locator + "was not found on the page");
            return false;
        }
        return true;
    }

    private void waitForBool(ExpectedCondition<Boolean> condition, Integer timeout) {
        timeout = timeout != null ? timeout : 5;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    protected Boolean waitForIsNotDisplayed(By locator, Integer... timeout) {
        try {
            waitForBool(ExpectedConditions.invisibilityOfElementLocated(locator),
                    (timeout.length > 0 ? timeout[0] : null));
        } catch (org.openqa.selenium.TimeoutException exception) {
            System.out.println("The expected element: " + locator + "was found on the page");
            return false;
        }
        return true;
    }

    protected void waitForPageToLoad() {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    private void waitForIs(ExpectedCondition<Boolean> condition, Integer timeout) {
        timeout = timeout != null ? timeout : 5;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    public Boolean waitForUrlToBe(String url, Integer... timeout) {
        try {
            waitForIs(ExpectedConditions.urlToBe(url),
                    (timeout.length > 0 ? timeout[0] : null));
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
        return true;
    }

    protected Boolean waitForUrlToContain(String url, Integer... timeout) {
        try {
            waitForIs(ExpectedConditions.urlContains(url),
                    (timeout.length > 0 ? timeout[0] : null));
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
        return true;
    }

    protected Boolean waitForIsClickable(By locator, Integer... timeout) {
        try {
            waitFor(ExpectedConditions.elementToBeClickable(locator),
                    (timeout.length > 0 ? timeout[0] : null));
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
        return true;
    }

    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }
}
