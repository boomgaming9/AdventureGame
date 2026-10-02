import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Item> inventory = new ArrayList<>();
    private final World world;
    private Room currentRoom;
    private int currentHealth;
    private final int maxHealth;
    private Weapon equippedWeapon;

    public Player(World world, Room currentRoom) {
        this.world = world;
        this.currentRoom = world.getStartRoom();
        this.currentHealth = 100;
        this.maxHealth = 100;
        this.equippedWeapon = null;
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
        Item item = getItem(name);
        if (item == null) {
            return false;
        }
        if (item == equippedWeapon) {       // Handles dropping an equipped item
            equippedWeapon = null;
        }
        inventory.remove(item);
        currentRoom.addItem(item);
        return true;
    }


    public EquipResult equip(String name) {
        Item item = getItem(name);
        if (item == null) {
            return EquipResult.NOT_IN_INVENTORY;
        }
        if (!(item instanceof Weapon newWeapon)) {  // newWeapon declared
            return EquipResult.NOT_A_WEAPON;
        }
        this.equippedWeapon = newWeapon;
        return EquipResult.SUCCESS;
    }
    public boolean unequip() {
        if (equippedWeapon == null) {
            return false;
        }
        equippedWeapon = null;
        return true;
    }


    // Getters
    public World getWorld() {
        return this.world;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getContents() {
        return currentRoom.getItems();
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public int getUses() {
        return equippedWeapon.getUses();
    }

    public String getInventory() {
        String result = "";
        for (Item item : inventory) {
            if (item == equippedWeapon) {
                result += item.getDisplayName() + " (equipped)\n";
            } else {
                result += item.getDisplayName() + "\n";
            }
        }
        return result;
    }

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
    }

    public boolean hasAnyItems() {
        return !inventory.isEmpty();
    }
}
