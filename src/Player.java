import java.util.ArrayList;
import java.util.List;

public class Player {
    //
    private final List<Item> inventory = new ArrayList<>();
    private final World world;
    private Room currentRoom;
    private int currentHealth;
    private final int maxHealth;
    private Weapon equippedWeapon;

    public Player(World world, Room currentRoom) {
        this.world = world;
        this.currentRoom = getWorld().getStartRoom();       // <---
        this.currentHealth = 55;
        this.maxHealth = 100;
    }

    public World getWorld() {
        return this.world;
    }

    public Room getCurrentRoom() { //nyt
        return currentRoom;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    //
    public String getInventory() {
        String result = "";
        for (Item item : inventory) {
            result += item.getDisplayName() + "\n";
        }
        return result;
    }   // Might become its own class later

    public Item getItem(String name) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(name) || item.getDisplayName().equalsIgnoreCase(name))
                return item;
        }
        return null;
    }

    public boolean hasItem(String name) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    } // for later torch integration

    public boolean hasAnyItems() {
        return !inventory.isEmpty();
    }

    public boolean move(String direction) {
        Room next = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "east" -> currentRoom.getEast();
            case "south" -> currentRoom.getSouth();
            case "west" -> currentRoom.getWest();
            default -> null;
        };
        if (next == null) {
            return false;
        }
        currentRoom = next;
        return true;
    }

    public EatResult eat(String name) {
        Item inventoryItem = getItem(name);
        Item roomItem = currentRoom.getItem(name);

        if (inventoryItem == null && roomItem == null) {
            return EatResult.NOT_FOUND;
        }
        if ((roomItem instanceof Food food)) {
            currentRoom.removeItem(name);
            currentHealth = Math.min(currentHealth + food.getHealth(), maxHealth);    //math.min stops health from going over 100
            return EatResult.CONSUMED;
        }
        if ((inventoryItem instanceof Food food)) {
            inventory.remove(food);
            currentHealth = Math.min(currentHealth + food.getHealth(), maxHealth);    //math.min stops health from going over 100
            return EatResult.CONSUMED;
        } else {
            return EatResult.NOT_EDIBLE;
        }
    }

    public EquipResult equip (String name) {
        Item inventoryItem = getItem(name);
        Item roomItem = currentRoom.getItem(name);

        if (inventoryItem == null) {
            return EquipResult.NOT_FOUND;
        }
        if (inventoryItem instanceof Weapon weapon){
             equippedWeapon= weapon;
             return EquipResult.EQUIPPED;
             }
        else
            return EquipResult.NOT_A_WEAPON_EQUIPPED;


        }


    public boolean grab(String name) {
        Item item = currentRoom.getItem(name);
        if (item == null) {
            return false;
        }
        currentRoom.removeItem(name);
        inventory.add(item);
        return true;
    }

    public boolean drop(String name) {
        Item item = currentRoom.getItem(name);
        if (item == null) {
            return true;
        }
        currentRoom.addItem(item);
        inventory.remove(item);
        return false;
    }
}
