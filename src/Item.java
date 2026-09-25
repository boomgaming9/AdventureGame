import java.util.ArrayList;

public class Item {
    private final String ShortName;
    private final String LongName;

    public Item(String ShortName, String LongName){
        this.ShortName = ShortName;
        this.LongName = LongName;
    }

    public String getShortName() {
        return ShortName;
    }

    public String getLongName() {
        return LongName;
    }

    public String getDefiniteName() {
        String[] articles = {"a", "an", "this"};
        for (String article : articles) {
            if (LongName.startsWith(article)) {
                return "the " + LongName.substring(article.length());
            }
        }
        return LongName;
    }

    @Override
    public String toString() {
        return LongName;
    }





}
