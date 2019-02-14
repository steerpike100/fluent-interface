package pluralsight.pages;

import pluralsight.utils.EnglandHomePageActionController;

public class EnglandHomePage {

    private EnglandHomePageActionController act;

    private EnglandHomePage(EnglandHomePageActionController act) {
        this.act = act;
    }

    public EnglandHomePageActionController act() {
        return act;
    }


    public static EnglandHomePage getEnglandHomePage() {

        return new EnglandHomePage(new EnglandHomePageActionController());

    }


}
