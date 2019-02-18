package tech.verenti.webdriver;

import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.verenti.framework.DriverPaths;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.lang.reflect.Proxy.newProxyInstance;

public class WebDriverManager {

    private static final Class<?>[] WEBDRIVER_INTERFACES = {
            WebDriver.class, JavascriptExecutor.class,
            FindBy.class, FindAll.class, FindBys.class,
            HasInputDevices.class, HasCapabilities.class, TakesScreenshot.class,
    };

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(WebDriverManager.class);
    private static final WebDriverManager INSTANCE = new WebDriverManager();

    public static WebDriver openBrowser(WebDriverConfigBean config, Class<?> invokingClass) {
        return INSTANCE.createBrowser(config, invokingClass);
    }

    private WebDriver createBrowser(WebDriverConfigBean config, Class<?> invokingClass) {
        log.debug("Running the test in " + config.getEnvironment() + " environment - opening browser" +
                config.getBrowserType());
        WebDriver underlyingWebDriver = createUnderlyingBrowser(config, invokingClass);
        threadDriver.set(underlyingWebDriver);

        WebDriver proxy = createProxy();
        log.debug("Cearted threadsafe WebDriver proxy " + proxy.toString());
        return underlyingWebDriver;
    }

    private WebDriver createProxy() {
        return (WebDriver) newProxyInstance(WebDriverManager.class.getClassLoader(),
                WEBDRIVER_INTERFACES,
                new WebDriverInvoker(threadDriver));
    }

    private WebDriver createUnderlyingBrowser(WebDriverConfigBean config, Class<?> invokingClass) {
        WebDriver driver;
        switch (config.getSeleniumMode().toUpperCase()) {
            case "SINGLE":
                driver = openLocalBrowser(config);
                driver.manage().window().maximize();
                break;
            case "GRID":
                driver = initialiseGrid(config);
                break;
            default:
                throw new IllegalArgumentException("Unknown operation mode: " + config.getSeleniumMode());
        }
        return driver;
    }

    private WebDriver openLocalBrowser(WebDriverConfigBean config) {
        try {

            switch (config.getBrowserType().toUpperCase()) {
                case "CHROME":
                    System.setProperty("webdriver.chrome.driver", DriverPaths.CHROMEPATH);
                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("disable-infobars");
                    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-web-security");
                    options.addArguments("--no-proxy-server");
                    options.addArguments("--enable-automation");
                    options.addArguments("--disable-save-password-bubble");
                    options.addArguments("--allow-http-screen-capture");
                    options.addArguments("--enable-screenshot-testing-with-mode");
                    options.addArguments("--artifacts-dir", System.getProperty("user.dir") + "\\target\\reports\\screenshots\\");
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    options.setExperimentalOption("prefs", prefs);
//                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    return new ChromeDriver();
                case "FIREFOX":
                    System.setProperty("webdriver.gecko.driver", DriverPaths.FIREFOXPATH);
                    return new FirefoxDriver();
                default:
                    throw new IllegalArgumentException("Unsupported browser type: " + config.getBrowserType() +
                            "expected 'chrome' or 'firefox' or 'IE'");
            }

        } catch (Exception e) {
            log.error("Failed to open browser " + config.getBrowserType());
            throw new IllegalStateException(e);
        }
    }

    private WebDriver initialiseGrid(WebDriverConfigBean config) {
        String hubUrl = "http://localhost:4444/wd/hub";
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(CapabilityType.BROWSER_NAME, config.getBrowserType());
            return new RemoteWebDriver(new URL(hubUrl), caps);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Malformed Selenium hub URL " + hubUrl, e);
        }
    }
}
