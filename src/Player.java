import java.util.ArrayList;
import java.util.List;

public class Player {
    private Room currentRoom;
    private final List<Item> inventory = new ArrayList<>();
    private World world;
    private int health = 100;

    public Player(Room currentRoom, World world) {
        this.world = world;
        this.currentRoom = getWorld().getStartRoom();
    }

    public Player(Room startRoom) {
        this.currentRoom = getWorld().getStartRoom();
    }

    public Room getCurrentRoom() { //nyt
        return currentRoom;
    }

    public World getWorld() {
        return this.world;
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

    public int getHealth (){
        return health;
    }
}
