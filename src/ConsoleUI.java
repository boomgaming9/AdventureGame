public class ConsoleUI {
    public Adventure adventure;

    public ConsoleUI(Adventure adventure) {
        this.adventure = adventure;
    }

    public String readCommand() {
        String command = IO.readln("> ").trim().toLowerCase();
        String commandObject = "";

        if (command.contains(" ")) {
            commandObject = command.substring(command.indexOf(' ') + 1).trim();
            command = command.substring(0, command.indexOf(' '));
        }

        return switch (command) {
            case "go"           -> commandObject;
            case "look", "l"    -> "look " + commandObject;
            case "grab", "g"    -> "grab "    + commandObject;
            case "drop", "d"    -> "drop "    + commandObject;
            case "eat"          -> "eat "     + commandObject;
            case "h"            -> "health";
            case "i"            -> "inventory";
            case "n"            -> "north";
            case "e"            -> "east";
            case "s"            -> "south";
            case "w"            -> "west";
            default             -> command;
        };
    }

    public void start() {
        printWelcome();
        printRoom(getCurrentRoom());

        boolean isRunning = true;

        while (isRunning) {
            String command = readCommand();
            String commandObject = "";



            if (command.contains(" ")) {
                commandObject = command.substring(command.indexOf(' ') + 1).trim();
                command = command.substring(0, command.indexOf(' '));
            }
            switch (command) {
                case "north", "east", "south", "west" -> move(command);
                case "look"      -> look(commandObject);
                case "help"      -> printHelp();
                case "health"    -> printHealth();
                case "inventory" -> inventory();
                case "eat"       -> eat(commandObject);
                case "grab"      -> grab(commandObject);
                case "drop"      -> drop(commandObject);
                case "quit"      -> {
                    printGoodbye();
                    isRunning = false;
                }
                default -> printUnknownCommand();
            }
        }
    }

    private void move(String direction) {
        if (adventure.move(direction)) {
            printRoom(getCurrentRoom());
        } else
            printCannotGo();
    }

    private Room getCurrentRoom() {
        return adventure.getPlayer().getCurrentRoom();
    }

    private void look(String name) {
        if (name.isEmpty() || name.equals("room")) {
            printRoomDescription(getCurrentRoom());
        } else {
            Item item = getCurrentRoom().getItem(name);
            if (item == null) {
                printUnknownCommand();
            } else
                printInspect(item);
        }
    }
    private void grab(String name) {
        Room room = getCurrentRoom();
        Item item = room.getItem(name);

        if (item == null) {
            printUnknownCommand();
        } else if (adventure.grab(name)) {
            printGrab(item);
        } else
            printCannotGrab(item);
    }
    private void drop(String name) {
        Item item = adventure.getPlayer().getItem(name);
        if (item == null)
        {
            printUnknownCommand();
        } else if (adventure.drop(name))
            printDrop(item);
    }
    private void inventory() {
        if (adventure.getPlayer().hasAnyItems()) {
            printInventory();
        } else {
            printEmpty();
        }
    }

    private void eat(String name) {
        switch (adventure.eat(name)) {
            case NOT_FOUND  -> printUnknownCommand();
            case NOT_EDIBLE -> printNotEdible(name);
            case CONSUMED   -> printEat(name);
        }
        /*if (eatResult == EatResult.NOT_FOUND) {
            printUnknownCommand();
        } else if (eatResult == EatResult.NOT_EDIBLE) {
            printNotEdible(item);
        } else if (eatResult == EatResult.CONSUMED) {
            printEat((Food) item);
        }*/
    }

    // Prints
    public void printRoom(Room room) {
        IO.println("You enter " + room.getName());
        room.printVisited();
    }
    public void printRoomDescription(Room room) {
        IO.println(room.getDescription());
    }
    public void printCannotGo() {
        IO.println("You cannot go that way");
    }
    public void printInspect(Item item) {
        IO.println(item.getDescription());
    }

    public void printGrab(Item item) {
        IO.println("You grab the " + item.getDisplayName());
    }
    public void printCannotGrab(Item item) {
        IO.println("You can't grab the " + item.getShortName() + ".");
    }
    public void printDrop(Item item) {
        IO.println("You drop the " + item.getShortName());
    }

    public void printHealth() {
        int hp = adventure.getPlayer().getCurrentHealth();
        int maxHP = adventure.getPlayer().getMaxHealth();
        IO.print("Health: " + hp + " - ");
        if (hp == maxHP) {
            IO.println("In perfect health!");
        } else if (hp >= maxHP/2) {
            IO.println("In good health.");
        } else if (hp <= maxHP/4) {
            IO.println("In bad health");
        } else if (hp <= 0) {
            IO.println("You are dead...");
        }
    }
    public void printEat(String name) {
        IO.println("You eat the " + name);
    }
    public void printNotEdible(String name) {
        IO.println("You cannot eat the " + name);
    }

    public void printInventory() {
        IO.println("You are carrying:");
        IO.print(adventure.getPlayer().getInventory()); // <---
    }
    public void printEmpty() {
        IO.println("Inventory is empty.");
    }

    public void printHelp() {
        IO.println("Commands:");
        IO.println(" go north / north / n");
        IO.println(" go east  / east  / e");
        IO.println(" go south / south / s");
        IO.println(" go west  / west  / w");
        IO.println(" look             - describe the current room");
        IO.println(" look [object]    - inspect nearby item");
        IO.println(" grab [object]    - pick up object");
        IO.println(" drop [object]    - drop object in room");
        IO.println(" inventory        - open player inventory");
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