public abstract class Weapon extends Item {
    private final int damage;

    public Weapon(String name, String prefix, String description, int damage) {
        super(name, prefix, description);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public abstract boolean canUse();

    public abstract AttackResult uses();

    public abstract int getUses();
}
