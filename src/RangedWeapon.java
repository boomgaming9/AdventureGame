public class RangedWeapon extends Weapon{
    private int ammo;

    @Override
    public boolean isRanged() { return true; }

    public RangedWeapon(String name, String prefix, String description, int damage, int ammo) {
        super(name, prefix, description, damage);
        this.ammo = ammo;
    }

    @Override
    public boolean canUse() {
        return ammo > 0;
    }

    @Override
    public int uses() {
        ammo--;
        return ammo;
    }

    public int getUses() {
        return ammo;
    }
}
