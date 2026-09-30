import java.util.Set;

public class Item {
    private String shortName;
    private String prefix;
    private String description;
    private final Set<String> capabilities;

    public Item(String name, String description, Set<String> capabilities) {
        this(name, name, description, capabilities);
    } // Overloaded Constructor - allows longName skip

    public Item(String name, String prefix, String description, Set<String> capabilities) {
        this.shortName = name;
        this.prefix = prefix;
        this.description = description;
        this.capabilities = capabilities;
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
    public boolean can(String capability) {
        return capabilities.contains(capability);
    }
}
