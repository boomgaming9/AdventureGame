import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Container extends Item {
    private final List<Item> contents = new ArrayList<>();

    public Container(String name, String prefix, String description) {
        super(name, prefix, description);
    }

    public void addContent(Item item) {
        contents.add(item);
    }
}
