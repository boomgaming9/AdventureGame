public abstract class Weapon extends Item {
    private final int damage;
    public boolean isRanged() { return false; }

    public Weapon(String name, String prefix, String description, int damage) {
        super(name, prefix, description);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public abstract boolean canUse();

    public abstract int uses();

    public abstract int getUses();
}
