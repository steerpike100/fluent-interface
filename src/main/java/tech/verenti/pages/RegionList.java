package tech.verenti.pages;

public enum RegionList {

    ENGLAND("England"),
    NIRELAND("N.Ireland"),
    SCOTLAND("Scotland"),
    ALBA("Alba"),
    WALES("Wales"),
    CYMRU("Cymru");

    private String value;

    RegionList(String value){
        this.value=value;
    }

    @Override
    public String toString(){
        return value;
    }
}
