package tech.verenti.pages;

import org.openqa.selenium.support.PageFactory;
import tech.verenti.utils.TestBase;

public class PageBase extends TestBase {

    protected EnglandHomePage englandHomePage() {
        return PageFactory.initElements(driver, EnglandHomePage.class);
    }

    protected HomePage homePage() {
        return PageFactory.initElements(driver, HomePage.class);
    }

    protected NewsPage newsPage() {
        return PageFactory.initElements(driver, NewsPage.class);
    }

    protected LocalNewsPage localNewsPage() {
        return PageFactory.initElements(driver, LocalNewsPage.class);
    }

    protected AbstractPage abstractPage() {
        return PageFactory.initElements(driver, AbstractPage.class);
    }

}
