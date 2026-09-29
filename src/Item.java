import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Item {
    private String name;
    private String description;
    private final Set<String> capabilities;

    // States
    private boolean lit;
    private boolean locked;
    private boolean broken;
    private boolean open;

    public Item(String name, String description, Set<String> capabilities) {
        this.name = name;
        this.description = description;
        this.capabilities = capabilities;
    }

    // Getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean can(String capability) {
        return capabilities.contains(capability);
    }
    /*public List<Item> getContents() {
        return contents;
    } */
    //
    /*public boolean isLit() {
        return lit;
    }
    public boolean isLocked() {
        return locked;
    }
    public boolean isBroken() {
        return broken;
    }
    public boolean isOpen() {
        return open;
    }

    // Setters
    public void setLit(boolean lit) {
        this.lit = lit;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public void setBroken(boolean broken) {
        this.broken = broken;
    }
    public void setOpen(boolean open) {
        this.open = open;
    } */
    //
}
