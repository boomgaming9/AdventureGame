import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Container extends Item {
    private List<Item> contents = new ArrayList<>();

    public Container(String name, String prefix, String description) {
        super(name, prefix, description, Set.of("opens", "contains"));
    }

    public void addContent(Item item) {
        contents.add(item);
    }
}
