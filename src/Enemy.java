import java.util.Collections;

public class Enemy {

    private String shortName;
    private String longName;
    private String description;

    private int health;
    private Weapon weapon;
    private final Room room;   // The room the enemy stands in

    public Enemy(String shortName, String longName, String description,
                 int health, Weapon weapon, Room room) {

        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.room = room;
    }

    // Enemy is attacked by the player's weapon
    public void hit(int damage) {
        modifyHealth(-damage);
        if (isDead()) {
            die();
        }
    }

    // Enemy attacks the player
    public void attack(Player player) {
        player.modifyHealth(-getDamage());
    }

    public void modifyHealth(int amount) {
        health += amount;
    }

    public boolean isDead() {
        return health <= 0;
    }

    private void die() {;
        room.addItem(weapon);

        // Optionally drop corpse
        Item corpse = new Item("corpse", shortName, "It lies still on the ground."); //passing single string
        room.addItem(corpse);

        // Remove enemy from room
        room.removeEnemy(this);
    }


    // Getters

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getDamage() {
        return weapon.getDamage(); }

    @Override
    public String toString() {
        return longName + " (" + health + " hp)";
    }
}

