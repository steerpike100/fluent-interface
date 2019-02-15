package tech.verenti.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver = null;
    private static WebDriverWait wait;
    public static Properties config = new Properties();
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static FileInputStream fis;
    public static String browser;

    @BeforeSuite
    public void startUpBrowser() {
        if (driver == null) {
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.debug("Config file loaded!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

                browser = System.getenv("browser");
            } else {

                browser = config.getProperty("browser");

            }

            config.setProperty("browser", browser);


            if (config.getProperty("browser").equals("firefox")) {

                // System.setProperty("webdriver.gecko.driver", "gecko.exe");
                driver = new FirefoxDriver();

            } else if (config.getProperty("browser").equals("chrome")) {

                System.setProperty("webdriver.chrome.driver",
                        System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                log.debug("Chrome Launched !!!");
            } else if (config.getProperty("browser").equals("ie")) {

                System.setProperty("webdriver.ie.driver",
                        System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();

            }

            driver.get(config.getProperty("testsiteurl"));
            log.debug("Navigated to : " + config.getProperty("testsiteurl"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
                    TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 5);
        }
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

        WebElement element = find(By.xpath("//select[@name=\"departurePoint:form:onePicker:0:picker:destination\"]//option[contains(text(),'"+ value  +"')]"));
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
