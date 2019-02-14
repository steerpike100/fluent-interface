package pluralsight.pages;

import pluralsight.utils.GetController;
import pluralsight.utils.NewsPageActionController;

public class NewsPage {

    private NewsPageActionController act;
    private GetController get;


    private NewsPage(NewsPageActionController act, GetController get) {
        this.act = act;
        this.get = get;
    }

    public GetController get() {
        return get;
    }

    public NewsPageActionController act() {
        return act;
    }


    public static NewsPage getNewsPage() {

        return new NewsPage(new NewsPageActionController(), new GetController());

    }

}
