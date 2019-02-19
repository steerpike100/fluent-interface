package tech.verenti.webdriver;

import org.openqa.selenium.Platform;

import java.io.Serializable;

public class WebDriverConfigBean implements Serializable {

    private String deploymentEnvironment;
    private String browserName;
    private Platform platformName;
    private String seleniumMode;

    public static WebDriverConfigBean aWebDriverConfig() {
        return new WebDriverConfigBean()
                .withSeleniumMode("SINGLE")
                .withSeleniumServer("local");
    }

    private WebDriverConfigBean() {

    }

    public WebDriverConfigBean withBrowser(String browser) {
        this.browserName = browser;
        return this;
    }

    public WebDriverConfigBean withPlatform(Platform platform) {
        this.platformName = platform;
        return this;
    }

    public WebDriverConfigBean withDeploymentEnvironment(String env) {
        this.deploymentEnvironment = env;
        return this;
    }

    private WebDriverConfigBean withSeleniumServer(String server) {
        String seleniumServer = server;
        return this;
    }

    public WebDriverConfigBean withSeleniumMode(String mode) {
        this.seleniumMode = mode;
        return this;
    }

    public String getBrowserName() {
        return browserName;

    }

    public Platform getPlatformName() {
        return platformName;
    }

    public String getEnvironment() {
        return deploymentEnvironment;
    }

    public String getSeleniumMode() {
        return seleniumMode;
    }
}