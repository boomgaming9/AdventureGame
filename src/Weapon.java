import java.util.Set;

public class Weapon extends Item {
    private final String damageType;
    private final int damage;

    public Weapon(String name, String description, String damageType, int damage) {
        super(name, description, Set.of("pickup", "attack"));
        this.damageType = damageType;
        this.damage = damage;
    }
}
