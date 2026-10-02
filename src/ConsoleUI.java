public class ConsoleUI {
    public Adventure adventure;

    public ConsoleUI(Adventure adventure) {
        this.adventure = adventure;
    }

    private Room getCurrentRoom() {
        return adventure.getCurrentRoom();
    }

    private String getWeaponString() {
        return adventure.getWeaponString();
    }

    public String readCommand() {
        String command = IO.readln("> ").trim().toLowerCase();
        String commandObject = "";

        if (command.contains(" ")) {
            commandObject = command.substring(command.indexOf(' ') + 1).trim();
            command = command.substring(0, command.indexOf(' '));
        }

        return switch (command) {
            case "go" -> commandObject;
            case "look", "l" -> "look " + commandObject;
            case "grab", "g" -> "grab " + commandObject;
            case "drop", "d" -> "drop " + commandObject;
            case "eat" -> "eat " + commandObject;
            case "equip" -> "equip " + commandObject;
            case "unequip" -> "unequip";
            case "a" -> "attack " + commandObject;
            case "h" -> "health";
            case "i" -> "inventory";
            case "n" -> "north";
            case "e" -> "east";
            case "s" -> "south";
            case "w" -> "west";
            default -> command;
        };
    }

    public void start() {
        printWelcome();
        printRoom(getCurrentRoom());

        boolean isRunning = true;

        while (isRunning) {
            if (adventure.getCurrentHealth() <= 0) {
                printDeath();
                isRunning = false;
            }
            String command = readCommand();
            String commandObject = "";


            if (command.contains(" ")) {
                commandObject = command.substring(command.indexOf(' ') + 1).trim();
                command = command.substring(0, command.indexOf(' '));
            }
            switch (command) {
                case "north", "east", "south", "west" -> move(command);
                case "look" -> look(commandObject);
                case "help" -> printHelp();
                case "health" -> printHealth();
                case "inventory" -> inventory();
                case "attack" -> attack();
                case "punch" -> punch();
                case "shoot" -> shoot();
                case "eat" -> eat(commandObject);
                case "equip" -> equip(commandObject);
                case "unequip" -> unequip();
                case "grab" -> grab(commandObject);
                case "drop" -> drop(commandObject);
                case "quit" -> {
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

    private void equip(String name) {
        switch (adventure.equip(name)) {
            case SUCCESS -> printEquip(name);
            case NOT_IN_INVENTORY -> printUnknownCommand();
            case NOT_A_WEAPON -> printUnknownCommand();
        }
    }
    private void unequip() {
        if (adventure.unequip()) {
            printUnequip();
        } else {
            printNoWeapon();
        }
    }

    private void grab(String name) {
        Room room = getCurrentRoom();
        Item item = room.getItem(name);

        if (item == null) {
            printUnknownCommand();
        } else if (adventure.grab(name)) {
            printGrab(name);
        }
    }

    private void drop(String name) {
        Item item = adventure.getItem(name);
        if (item == null) {
            printUnknownCommand();
        } else if (adventure.drop(name))
            printDrop(item);
    }

    private void inventory() {
        if (adventure.hasAnyItems()) {
            printInventory();
        } else {
            printEmpty();
        }
    }

    // Attack / Punch / Shoot
    private void attack() {
        switch (adventure.attack()) {
            case SUCCESS -> printAttack(getWeaponString());
            case RANGED_SUCCESS -> printShoot(getWeaponString(), adventure.getUses());
            case NO_RANGED_WEAPON -> printNoRangedWeapon();
            case OUT_OF_AMMO -> printNoAmmo(getWeaponString());
        }
    }
    private void punch() {
        adventure.punch();
        printPunch();
    }
    private void shoot() {
        switch (adventure.shoot()) {
            case RANGED_SUCCESS -> printShoot(getWeaponString(), adventure.getUses());
            case NO_RANGED_WEAPON -> printNoRangedWeapon();
            case OUT_OF_AMMO -> printNoAmmo(getWeaponString());
        }
    }

    private void eat(String name) {
        switch (adventure.eat(name)) {
            case NOT_FOUND -> printUnknownCommand();
            case NOT_EDIBLE -> printNotEdible(name);
            case CONSUMED -> printEat(name);
        }
    }

    // ================[ PRINTS ]================

    // Movement
    public void printRoom(Room room) {
        IO.println("You enter " + room.getName());
        room.printVisited();

        String contents = adventure.getContents();
        if (!contents.isEmpty()) {
            IO.println("You see:");
            IO.print(contents);
        }
    }
    public void printCannotGo() {
        IO.println("You cannot go that way");
    }

    // Look
    public void printRoomDescription(Room room) {
        IO.println(room.getDescription());

        String contents = adventure.getContents();
        if (!contents.isEmpty()) {
            IO.println("You see:");
            IO.print(contents);
        }
    }
    public void printInspect(Item item) {
        IO.println(item.getDescription());
    }

    // Equip / Unequip
    public void printEquip(String name) {
        IO.println("You equip the " + name);
    }
    public void printUnequip() {
        IO.println("You unequip your weapon");
    }
    public void printNoWeapon() {
        IO.println("You have no weapon equipped.");
    }

    // Grab
    public void printGrab(String name) {
        IO.println("You grab the " + name);
    }
    public void printCannotGrab(String name) {
        IO.println("You can't grab the " + name + ".");
    }

    // Drop
    public void printDrop(Item item) {
        IO.println("You drop the " + item.getShortName());
    }

    // Health
    public void printHealth() {
        int hp = adventure.getCurrentHealth();
        int maxHP = adventure.getMaxHealth();
        IO.print("Health: " + hp + " - ");
        if (hp <= 0) {
            IO.println("You are dead...");
        } else if (hp == maxHP) {
            IO.println("In perfect health!");
        } else if (hp >= maxHP / 2) {
            IO.println("In good health.");
        } else if (hp <= maxHP / 4) {
            IO.println("In bad health");
        }
    }

    // Eat
    public void printEat(String name) {
        IO.println("You eat the " + name);
    }
    public void printNotEdible(String name) {
        IO.println("You cannot eat the " + name);
    }

    //Attack / Punch / Shoot
    public void printAttack(String name) {
        IO.println("You attack the empty air with your " + name + ".");
    }
    public void printPunch() {
        IO.println("You punch the empty air");
    }
    public void printShoot(String name, int count) {
        IO.print("You shoot at the empty air with your " + name + ". ");
        IO.println(count + " shots left.");
    }
    public void printNoRangedWeapon() {
        IO.println("You have no ranged weapon with which to shoot.");
    }
    public void printNoAmmo(String name) {
        IO.println("Your " + name + " is out of ammo.");
    }

    // Inventory
    public void printInventory() {
        IO.println("You are carrying:");
        IO.print(adventure.getInventory()); // <---
    }
    public void printEmpty() {
        IO.println("Inventory is empty.");
    }

    // Help
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

    public void printDeath() {
        IO.println();
        IO.println("You have died.");
        IO.println("Thank you for playing.");
        IO.println("...");
    }

    public void printGoodbye() {
        IO.println("Goodbye!");
    }

    public void printUnknownCommand() {
        IO.println("Unknown command. Type HELP for a list of commands.");
    }
}