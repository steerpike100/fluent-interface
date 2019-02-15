package tech.verenti;

import org.testng.annotations.Test;
import tech.verenti.pages.*;
import tech.verenti.utils.DistanceFrom;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.verenti.pages.AbstractPage.getAbstractPage;
import static tech.verenti.pages.EnglandHomePage.getEnglandHomePage;
import static tech.verenti.pages.HomePage.getHomePage;
import static tech.verenti.pages.LocalNewsPage.*;
import static tech.verenti.pages.NewsPage.getNewsPage;
import static tech.verenti.utils.LocalNewsPageActionController.localNewsSavedDiv;
import static tech.verenti.utils.LocalNewsPageActionController.locationHeader;

public class NavigateTest extends BaseTestClass {

    HomePage home = getHomePage();
    NewsPage news = getNewsPage();
    EnglandHomePage england = getEnglandHomePage();
    LocalNewsPage local = getLocalNewsPage();
    AbstractPage abstractPage = getAbstractPage();


    @Test
    public void basicFilterByTest() throws InterruptedException {

        home.act().clickNewsButton();

        news.act().clickEnglandButton();

        england.act().clickLocalNewsButton();

        local.act().setAsLocalNews();

        abstractPage.verify()
                .verifyIsDisplayed(localNewsSavedDiv());

        local.act().setDistance(DistanceFrom.FIVEMILES);

        abstractPage.verify()
                .verifyIsDisplayed(locationHeader());

    }

    @Test
    public void regionListcheck(){
        List<String> regionList =  news.get().categories();
        assertThat(regionList)
                .hasSize(19)
                .contains("News", "Sport", "Weather", "iPlayer", "Sounds");
    }
}
