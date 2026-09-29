import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Container extends Item {
    private List<Item> contents = new ArrayList<>();

    public Container(String name, String description) {
        super(name, description, Set.of("opens", "contains"));
    }
}
