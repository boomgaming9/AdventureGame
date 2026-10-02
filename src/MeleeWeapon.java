public class MeleeWeapon extends Weapon{

    public MeleeWeapon(String name, String prefix, String description, int damage) {
        super(name, prefix, description, damage);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public int uses() {
        return -1;   // Unlimited uses, doesnt use ammo
    }

    @Override
    public int getUses() {
        return uses();
    }
}
