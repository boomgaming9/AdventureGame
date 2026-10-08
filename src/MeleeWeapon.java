public class MeleeWeapon extends Weapon{

    public MeleeWeapon(String name, String prefix, String description, int damage) {
        super(name, prefix, description, damage);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public AttackResult uses() {
        return AttackResult.SUCCESS;
    }

    @Override
    public int getUses() {
        return -1;  // Unlimited uses, doesnt use ammo
    }
}
