import java.util.Set;

public class Item {
    private String shortName;
    private String prefix;
    private String description;

    public Item(String name, String description) {
        this(name, name, description);
    } // Overloaded Constructor - allows prefix- skip

    public Item(String name, String prefix, String description) {
        this.shortName = name;
        this.prefix = prefix;
        this.description = description;
    }

    // Getters
    public String getShortName() {
        return shortName;
    }

    public String getDisplayName() {
        if (shortName.equalsIgnoreCase(prefix)) {
            return shortName;
        }
        return prefix + " " + shortName;
    }

    public String getDescription() {
        return description;
    }
}
