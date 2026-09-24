public class Player {
    private Room currentRoom;

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



}
