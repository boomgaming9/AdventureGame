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


    public AttackResult attack(String name) {
        return player.attack(name);
    }

    public AttackResult punch(String name) {
        return player.punch(name);
    }

    public AttackResult shoot(String name) {
        Weapon weapon = getWeapon();
        if (weapon == null || !weapon.isRanged()) {
            return AttackResult.NO_RANGED_WEAPON;
        }
        return player.attack(name);   // same sequence as attack, ammo checked with canUse()
    }

    public Enemy findEnemy(String name) {
        return player.findEnemy(name);
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