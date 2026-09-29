import java.util.Set;

public class Food extends Item {
    private final int health;

    public Food(String name, String description, int health) {
        super(name, description, Set.of("pickup", "eat"));
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
