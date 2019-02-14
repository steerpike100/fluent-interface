package pluralsight.utils;

public enum DistanceFrom {

        FIVEMILES("//div[@id='local-news-radius']//div[2]//a[1]"),
        TENMILES("a['.2cr8yg16ohc.2.1.0.2:$10.0.2']"),
        TWENTYMILES("a['.2cr8yg16ohc.2.1.0.2:$20.0']"),
        THIRTYMILES("a['.2cr8yg16ohc.2.1.0.2:$30.0']");


        private String value;

        DistanceFrom(String value){
            this.value=value;
        }

        @Override
        public String toString(){
            return value;
        }
    }

