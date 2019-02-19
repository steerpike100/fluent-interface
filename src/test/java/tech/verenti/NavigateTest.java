package tech.verenti;

import org.testng.annotations.Test;
import tech.verenti.pages.PageBase;
import tech.verenti.utils.DistanceFrom;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.verenti.pages.LocalNewsPage.localNewsSavedDiv;

public class NavigateTest extends PageBase {




    @Test(invocationCount = 3)
    public void basicFilterByTest() throws InterruptedException {

        homePage().clickNewsButton();

        newsPage().clickEnglandButton();

        englandHomePage().clickLocalNewsButton();

        localNewsPage().setAsLocalNews();

        localNewsPage().setDistance(DistanceFrom.FIVEMILES);

        assertThat(isDisplayed(localNewsPage().checkLocationHeader(), 10));
    }

    @Test(invocationCount = 3)
    public void regionListcheck() {
        List<String> regionList = newsPage().categories();
        assertThat(regionList)
                .hasSize(19)
                .contains("News", "Sport", "Weather", "iPlayer", "Sounds");
    }
}
