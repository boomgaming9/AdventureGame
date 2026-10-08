public class RangedWeapon extends Weapon{
    private int ammo;

    public RangedWeapon(String name, String prefix, String description, int damage, int ammo) {
        super(name, prefix, description, damage);
        this.ammo = ammo;
    }

    @Override
    public boolean canUse() {
        return ammo > 0;
    }

    @Override
    public AttackResult uses() {
        ammo--;
        return AttackResult.SUCCESS;
    }

    public int getUses() {
        return ammo;
    }
}
