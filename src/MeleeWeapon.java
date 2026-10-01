public class MeleeWeapon extends Weapon{

    public MeleeWeapon(String name, String prefix, String description, String damageType, int damage) {
        super(name, prefix, description, damageType, damage);
    }

    @Override
    public boolean canUse (){
        return true;
    }

    @Override
    public int remainingUses(){
        return -1 ;
    }
}
