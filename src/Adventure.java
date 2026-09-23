public class Adventure {
    private Room currentRoom;

    public Adventure() {
        Room room1 = new Room("Room 1", "A room with no distinct features, except two doors.");
        currentRoom = room1;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}