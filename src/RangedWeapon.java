public class RangedWeapon extends Weapon{
    private int ammunition;

    public RangedWeapon(String name, String prefix, String description, String damageType, int damage) {
        super(name, prefix, description, damageType, damage);
        this.ammunition = ammunition;
    }

    @Override
    public boolean canUse (){
        return ammunition > 0;
    }

    @Override
    public int useLeft (){
        ammunition = ammunition - 1;
        return ammunition;
    }
}
