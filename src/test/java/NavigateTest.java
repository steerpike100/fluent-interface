import org.testng.annotations.Test;
import pluralsight.pages.*;
import pluralsight.utils.DistanceFrom;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pluralsight.pages.AbstractPage.getAbstractPage;
import static pluralsight.pages.EnglandHomePage.getEnglandHomePage;
import static pluralsight.pages.HomePage.getHomePage;
import static pluralsight.pages.LocalNewsPage.*;
import static pluralsight.pages.NewsPage.getNewsPage;
import static pluralsight.utils.LocalNewsPageActionController.localNewsSavedDiv;
import static pluralsight.utils.LocalNewsPageActionController.locationHeader;

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
