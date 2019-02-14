package pluralsight.pages;

import pluralsight.utils.NewsPageActionController;

public class NewsPage {

    private NewsPageActionController act;

    private NewsPage(NewsPageActionController act){
        this.act = act;
    }

    public NewsPageActionController act(){
    return act;
    }


    public static NewsPage getNewsPage() {

        return new NewsPage(new NewsPageActionController());

    }

}
