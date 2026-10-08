import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String name;
    private final String description;
    private Room north;
    private Room east;
    private Room south;
    private Room west;
    private List<Item> items = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private boolean isVisited;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.isVisited = false;
    }

    public boolean getVisited() {
        return isVisited;
    }
    public void setVisited(boolean isVisited) {
        this.isVisited = false;
    }

    // ? description : "You've already been here...");

    public void addItem(Item item) {
        items.add(item);
    }
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        enemy.setCurrentRoom(this);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public boolean removeItem(String name) {
        Item item = getItem(name);
        if (item == null)
            return false;
        items.remove(item);
        return true;
    }

    // Getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public Room getNorth() {
        return north;
    }
    public Room getEast() {
        return east;
    }
    public Room getSouth() {
        return south;
    }
    public Room getWest() {
        return west;
    }

    public String getItems() {
        String result = "";
        for (Item item : items) {
            result += item.getDisplayName() + "\n";
        }
        return result;
    }
    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getShortName().equalsIgnoreCase(name) || item.getDisplayName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public String getEnemies(){
        String result = "";
        for (Enemy enemy : enemies) {
            result += enemy.getDisplayName() + "\n";
        }
        return result;
    }
    public Enemy getEnemy(String name) {
        for (Enemy enemy : enemies) {
            if (enemy.getShortName().equalsIgnoreCase(name) || enemy.getDisplayName().equalsIgnoreCase(name)) {
                return enemy;
            }
        }
        return null;
    }
    public Enemy getFirstEnemy() {
        return enemies.isEmpty() ? null : enemies.get(0);
    }
    public boolean hasEnemies() {
        return !enemies.isEmpty();
    }

    // Setters
    public void setNorth(Room room) {
        this.north = room;
    }
    public void setEast(Room room) {
        this.east = room;
    }
    public void setSouth(Room room) {
        this.south = room;
    }
    public void setWest(Room room) {
        this.west = room;
    }
}