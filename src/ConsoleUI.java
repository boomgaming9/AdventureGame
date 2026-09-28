public class ConsoleUI {
    private Adventure adventure;

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    public void start() {
        printWelcome();
        printRoom(adventure.getPlayer().getCurrentRoom());

        boolean isRunning = true;

        while (isRunning) {
            String command = readCommand();

            switch (command) {
                case "north", "east", "south", "west" -> Adventure.move(command);
                case "look" -> printRoom(adventure.getPlayer().getCurrentRoom());
                case "help" -> printHelp();
                case "quit" -> {
                    printGoodbye();
                    isRunning = false;
                }
                default -> {
                    if (command.startsWith("inspect ")) {
                        adventure.inspect(command.substring("inspect ".length()).trim());
                    } else if (command.startsWith("grab ")) {
                        adventure.grab(command.substring("grab ".length()).trim());
                    } else if (command.startsWith("drop ")) {
                        adventure.drop(command.substring("drop ".length()).trim());
                    } else {
                        printUnknownCommand();
                    }
                }
            }
        }
    }

    public String readCommand() {
        String command =  IO.readln("> ").trim().toLowerCase();

        if (command.startsWith("go ")) {
            command = command.substring(3).trim();
        }

        if (command.startsWith("inspect ")) {
            return command;
        }
        if (command.startsWith("i ")) {
            return "inspect " + command.substring(2).trim();
        }

        if (command.startsWith("grab ")) {
            return command;
        }
        if (command.startsWith("g ")) {
            return "grab " + command.substring(2).trim();
        }

        if (command.startsWith("drop ")) {
            return command;
        }
        if (command.startsWith("d ")) {
            return "drop " + command.substring(2).trim();
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
        IO.println("You enter " + room.getName());
        room.printVisited();
    }

    public void printCannotGo() {
        IO.println("You cannot go that way");
    }

    public void printInspect(Item item) {
        IO.println(item.getDescription());
    }

    public void printGrab(Item item) {
        IO.println("You grab the " + item.getName());
    }

    public void printDrop(Item item) {
        IO.println("You drop the " + item.getName());
    }

    public void printHelp() {
        IO.println("Commands:");
        IO.println(" go north / north / n");
        IO.println(" go east  / east  / e");
        IO.println(" go south / south / s");
        IO.println(" go west  / west  / w");
        IO.println(" look             - describe the current room");
        IO.println(" inspect [object] - examine an object closely");
        IO.println(" grab [object]    - pick up an object");
        IO.println(" drop [object]    - drops object in room");
        IO.println(" help             - show this list");
        IO.println(" quit             - quit the game");
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