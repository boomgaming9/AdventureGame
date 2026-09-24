public class Adventure {
    private Room currentRoom;
    private final UserInterface UI = new UserInterface();

    public void start() {
        currentRoom = new World().getStartRoom();
        IO.println("You wake up in a strange place. Nine rooms are connected, and one of them hides a secret.");
        IO.println("Type HELP at any time to see your options.\n");
        UI.printRoom(currentRoom);

        boolean isRunning = true;

        while (isRunning) {
            String kommando = UI.readCommand();

            if (kommando.startsWith("go ")) {
                kommando = kommando.substring(3).trim();
            }   // QoL

            switch (kommando) {
                case "north", "n" -> move(currentRoom.getNorth());
                case "east",  "e" -> move(currentRoom.getEast());
                case "south", "s" -> move(currentRoom.getSouth());
                case "west",  "w" -> move(currentRoom.getWest());
                case "look"        -> UI.printRoom(currentRoom);
                case "help" -> {
                    IO.println("Commands:");
                    IO.println(" go north / north / n");
                    IO.println(" go east  / east  / e");
                    IO.println(" go south / south / s");
                    IO.println(" go west  / west  / w");
                    IO.println(" look    - describe the current room");
                    IO.println(" help    - show this list");
                    IO.println(" exit    - quit the game");
                }
                case "exit" -> {
                    IO.println("Goodbye!");
                    isRunning = false;
                }
                default -> IO.println("Unknown command. Type HELP for a list of commands.");
            }
        }
    }

    private void move(Room next) {
        if (next == null) {
            UI.printCannotGo();
        } else {
            currentRoom = next;
            UI.printRoom(currentRoom);
        }
    }
}