package tech.verenti.pages;

import tech.verenti.utils.LocalNewsPageActionController;

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
