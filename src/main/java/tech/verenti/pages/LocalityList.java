package tech.verenti.pages;

public enum LocalityList {
    WELLS("Wells"),
    BRISTOL("Bristol"),
    AXBRIDGE("Axbridge"),
    CREWKERNE("Crewkerne"),
    GLASGOW("Glasgow");

    private String value;

    LocalityList(String value){
        this.value=value;
    }

    @Override
    public String toString(){
        return value;
    }
}


