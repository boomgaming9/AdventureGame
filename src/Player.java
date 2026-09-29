import java.util.ArrayList;
import java.util.List;

public class Player {
    private World world;
    private Room currentRoom;
    private final List<Item> inventory = new ArrayList<>();
    private int currentHealth;
    private int maxHealth;

    public Player(World world, Room currentRoom) {
        this.world = world;
        this.currentRoom = getWorld().getStartRoom();
        this.currentHealth = 55;
        this.maxHealth = 100;
    }

    public Room getCurrentRoom() { //nyt
        return currentRoom;
    }

    public World getWorld() {
        return this.world;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean move(String direction) { //fra adventure
        Room next = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "east"  -> currentRoom.getEast();
            case "south" -> currentRoom.getSouth();
            case "west"  -> currentRoom.getWest();
            default      -> null;
        };
        if (next == null) { // fra adventure
            return false;
        }
        currentRoom = next;
        return true;
    }

    public EatResult eat(String name) {
        Item item = getItem(name);
        if (item == null) {
            return EatResult.NOT_FOUND;
        }
        if (!(item instanceof Food)) {
            return EatResult.NOT_EDIBLE;
        }
        Food food = (Food) getItem(name);
        inventory.remove(food);
        currentHealth = Math.min(currentHealth + food.getHealth(), maxHealth);    //math.min stops health from going over 100
        return EatResult.CONSUMED;
    }

    public void grab(Item item) {
        inventory.add(item);
    }
    public void drop(Item item) {
        inventory.remove(item);
    }
    public String getInventory() {
        String result = "";
        for (Item item : inventory) {
            result += item.getName() + "\n";
        }
        return result;
    }

    public boolean hasItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    public boolean hasAnyItems() {
        return !inventory.isEmpty();
    }

    public Item getItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name))
                return item;
        }
        return null;
    }
}
