public class Enemy {
    private final String shortName;
    private final String prefix;
    private final String description;
    private int health;
    private final Weapon weapon;
    private Room currentRoom;
    private String color;

    public Enemy(String name, String prefix, String description, int health, Weapon weapon) {
        this.shortName = name;
        this.prefix = prefix;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.color = "\u001B[31m";
    }

    public String getColor() {
        return "\u001B[31m";
    }

    public void modifyHealth(int amount) {
        health += amount;
    }

    public void hit(int damage) {
        modifyHealth(-damage);
        if (isDead()) {
            die();
        }
    }

    public int attack(Player player) {
        int damage = getDamage();
        player.modifyHealth(-damage);
        return damage;
    }

    public boolean isDead() {
        return health <= 0;
    }

    private void die() {
        if (currentRoom == null) return;
        if (weapon != null) currentRoom.addItem(weapon);
        Item corpse = new Item("corpse", shortName,
                "The corpse of a " + getDisplayName() + ". It lies still on the ground.");
        currentRoom.addItem(corpse);
        currentRoom.removeEnemy(this);
    }

    // Getters
    public String getShortName() {
        return shortName;
    }

    public String getDisplayName() {
        String name = shortName.equalsIgnoreCase(prefix) ? shortName : prefix + " " + shortName;
        return color + name + "\u001B[0m";
    }

    public String getDescription() {
        return description;
    }

    public int getDamage() {
        return weapon == null ? 0 : weapon.getDamage();
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }
}

