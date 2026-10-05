import java.util.Collections;

public class Enemy {

    private String shortName;
    private String longName;
    private String description;

    private int health;
    private Weapon weapon;
    private Room room;   // The room the enemy stands in

    public Enemy(String shortName, String longName, String description,
                 int health, Weapon weapon, Room room) {

        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.room = room;
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

    // Enemy is attacked by the player's weapon
    public void hit(int damage) {
        health -= damage;
        IO.println(longName + " takes " + damage + " damage.");

        if (health <= 0) {
            die();
        }
    }

    // Enemy attacks the player
    public void attack(Player player) {
        int dmg = weapon.getDamage();
        IO.println(longName + " attacks you for " + dmg + " damage.");
        player.hit(dmg);
    }

    private void die() {
        IO.println(longName + " dies, dropping its " + weapon.getShortName() + ".");

        // Drop weapon into the room
        room.addItem(weapon);

        // Optionally drop corpse
        Item corpse = new Item(shortName + " corpse",
                "the corpse of " + longName, Collections.singleton("It lies still on the ground."));
        room.addItem(corpse);

        // Remove enemy from room
        room.removeEnemy(this);
    }

    @Override
    public String toString() {
        return longName + " (" + health + " hp)";
    }
}

