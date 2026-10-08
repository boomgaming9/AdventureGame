public class Adventure {
    private final Player player;

    public Adventure(Player player) {
        this.player = player;
    }

    // Movement
    public boolean move(String direction) {
        return player.move(direction);
    }

    // Equip
    public EquipResult equip(String weaponName) {
        return player.equip(weaponName);
    }
    public boolean unequip() {
        return player.unequip();
    }

    // Grab / Drop
    public boolean grab(String itemName) {
        return player.grab(itemName);
    }
    public boolean drop(String itemName) {
        return player.drop(itemName);
    }

    // Eat
    public EatResult eat(String itemName) {
        return player.eat(itemName);
    }

    // Attack
    public AttackResult attack(String targetName) {
        return player.attack(targetName);
    }
    public String getWeaponString() {
        return player.getWeaponString();
    }
    public int getUses() {
        return player.getUses();
    }

    // Getters
    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }
    public String getContents() {
        return player.getContents();
    }
    public String getEnemies() {
        return player.getEnemies();
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
    public boolean hasAnyItems() {
        return player.hasAnyItems();
    }
}