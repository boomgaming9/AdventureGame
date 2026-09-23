public class Room {
    private String name;
    private String description;
    private Room north;
    private Room east;
    private Room south;
    private Room west;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room room) {
        this.north = room;
    }

    public Room getEast() {
        return east;
    }
    public void setEast(Room room) {
        this.east = room;
    }

    public Room getSouth() {
        return south;
    }
    public void setSouth(Room room) {
        this.south = room;
    }

    public Room getWest() {
        return west;
    }
    public void setWest(Room room) {
        this.west = room;
    }
}
