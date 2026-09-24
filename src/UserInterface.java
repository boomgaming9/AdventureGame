public class UserInterface {
    private Room currentRoom;
    private final UserInterface UI = new UserInterface();

    public String readCommand() {
        String kommando =  IO.readln().trim().toLowerCase();

        if (kommando.startsWith("go ")) {
            kommando = kommando.substring(3).trim();
        }

        return switch (kommando) {
            case "n" -> "north";
            case "e" -> "east";
            case "s" -> "south";
            case "w" -> "west";
            default  -> kommando;
        };

    }

    public void printRoom(Room room) {
        IO.println("You are in " + room.getName());
        IO.println(room.getDescription());
    }

    public void printCannotGo() {
        IO.println("You cannot go that way");
    }

    public void printHelp() {
        IO.println("Commands:");
        IO.println(" go north / north / n");
        IO.println(" go east  / east  / e");
        IO.println(" go south / south / s");
        IO.println(" go west  / west  / w");
        IO.println(" look    - describe the current room");
        IO.println(" help    - show this list");
        IO.println(" exit    - quit the game");
    }

    public void printGoodbye() {
        IO.println("Goodbye!");
    }

    public void printUnknownCommand() {
        IO.println("Unknown command. Type HELP for a list of commands.");
    }




}