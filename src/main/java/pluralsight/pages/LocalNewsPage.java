package pluralsight.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pluralsight.utils.HomePageActionController;
import pluralsight.utils.LocalNewsPageActionController;
import pluralsight.utils.NewsPageActionController;

import static pluralsight.DriverFactory.getChromeDriver;
import static pluralsight.DriverFactory.getWebDriverWait;

public class LocalNewsPage {

    private LocalNewsPageActionController act;

    private LocalNewsPage(LocalNewsPageActionController act){
        this.act = act;
    }


    public LocalNewsPageActionController act(){
        return act;
    }

    public static LocalNewsPage getLocalNewsPage(){
        return new LocalNewsPage(new LocalNewsPageActionController()    );
    }


}
