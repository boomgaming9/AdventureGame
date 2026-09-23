public class World {
    private final Room room1, room2, room3,
                       room4, room5, room6,
                       room7, room8, room9;

    /* 3x3 grid:
               room1 - room2 - room3
                 |       |       |
               room4 - room5 - room6
                 |       |       |
               room7 - room8 - room9 */

    public World() {
        room1 = new Room("Room 1", "TBD 1");
        room2 = new Room("Room 2", "TBD 2");
        room3 = new Room("Room 3", "TBD 3");
        room4 = new Room("Room 4", "TBD 4");
        room5 = new Room("Room 5", "TBD 5");
        room6 = new Room("Room 6", "TBD 6");
        room7 = new Room("Room 7", "TBD 7");
        room8 = new Room("Room 8", "TBD 8");
        room9 = new Room("Room 9", "TBD 9");

        // Default, no walls placed
        link(room1, null,  room2, room4, null);
        link(room2, null,  room3, room5, room1);
        link(room3, null,  null,  room6, room2);
        link(room4, room1, room5, room7, null);
        link(room5, room2, room6, room8, room4);
        link(room6, room3, null,  room9, room5);
        link(room7, room4, room8, null,  null);
        link(room8, room5, room9, null,  room7);
        link(room9, room6, null,  null,  room8);
    }

    private void link(Room current, Room north, Room east, Room south, Room west) {
        current.setNorth(north);
        current.setEast(east);
        current.setSouth(south);
        current.setWest(west);
    }

    public Room getStartRoom() {
        return room1;
    }
}