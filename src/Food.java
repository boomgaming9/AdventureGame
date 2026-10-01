import java.util.Set;

public class Food extends Item {
    private final int health;

    public Food(String name, String prefix, String description, int health) {
        super(name, prefix, description);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
