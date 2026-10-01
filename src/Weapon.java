import java.util.Set;

public class Weapon extends Item {
    private final String damageType;
    private final int damage;

    public Weapon(String name, String prefix, String description, String damageType, int damage) {
        super(name, prefix, description);
        this.damageType = damageType;
        this.damage = damage;
    }

}
