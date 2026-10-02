public class Adventure {
    private final Player player;

    public Adventure(Player player) {
        this.player = player;
    }

    // Functions
    public boolean move(String direction) {
        return player.move(direction);
    }

    public EquipResult equip(String weaponName) {
        return player.equip(weaponName);
    }
    public boolean unequip() {
        return player.unequip();
    }

    public boolean grab(String itemName) {
        return player.grab(itemName);
    }
    public boolean drop(String itemName) {
        return player.drop(itemName);
    }

    public EatResult eat(String itemName) {
        return player.eat(itemName);
    }

    // Attack
    public AttackResult attack() {
        Weapon weapon = getWeapon();
        if (weapon == null || !weapon.isRanged()){
            return punch();
        }
        return shoot();
    }

    public AttackResult punch () {
        return AttackResult.SUCCESS;   // always succeeds
    }

    public AttackResult shoot () {
        Weapon weapon = getWeapon();
        if (weapon == null || !weapon.isRanged()){
            return AttackResult.NO_RANGED_WEAPON;
        }
        if (!weapon.canUse()) {
            return AttackResult.OUT_OF_AMMO;
        }
        weapon.uses();
        return AttackResult.RANGED_SUCCESS;
    }

    public String getWeaponString() {
        Weapon weapon = getWeapon();
        return weapon == null ? "fist" : weapon.getDisplayName();
    }

    // Getters
    public Player getPlayer() {
        return player;
    }
    public World getWorld() {
        return player.getWorld();
    }
    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }
    public String getContents() {
        return player.getContents();
    }
    public int getCurrentHealth() {
        return player.getCurrentHealth();
    }
    public int getMaxHealth() {
        return player.getMaxHealth();
    }
    public String getInventory() {
        return player.getInventory();
    }
    public Weapon getWeapon() {
        return player.getEquippedWeapon();
    }
    public Item getItem(String name) {
        return player.getItem(name);
    }
    public boolean hasItem(String name) {
        return player.hasItem(name);
    }
    public boolean hasAnyItems() {
        return player.hasAnyItems();
    }
    public int getUses() {
        return player.getUses();
    }
}