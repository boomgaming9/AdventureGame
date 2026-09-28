import java.util.ArrayList;
import java.util.List;

public class Player {
    private Room currentRoom;
    private final List<Item> inventory = new ArrayList<>();

    public Player(Room startRoom) {
        this.currentRoom = startRoom;
    }

    public Room getCurrentRoom() { //nyt
        return currentRoom;
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
    public List<Item> getInventory() {
        return inventory;
    }
    public boolean hasItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    public Item getItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name))
                return item;
        }
        return null;
    }
}
