public class UserInterface {

    public String readCommand() {
        String command =  IO.readln("> ").trim().toLowerCase();

        if (command.startsWith("go ")) {
            command = command.substring(3).trim();
        }

        return switch (command) {
            case "n" -> "north";
            case "e" -> "east";
            case "s" -> "south";
            case "w" -> "west";
            default  -> command;
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

    public void printWelcome() {
        IO.println("You wake up in a strange place. Nine rooms are connected, and one of them hides a secret.");
        IO.println("Type HELP at any time to see your options.\n");
    }

    public void printGoodbye() {
        IO.println("Goodbye!");
    }

    public void printUnknownCommand() {
        IO.println("Unknown command. Type HELP for a list of commands.");
    }




}