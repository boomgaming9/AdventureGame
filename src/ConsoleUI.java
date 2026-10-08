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
            case "a", "attack" -> "attack " + commandObject;
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
                case "attack" -> attack(commandObject);
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

    // Equip
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

    // Grab / Drop
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
        if (adventure.drop(name)) {
            printDrop(name);
        } else {
            printUnknownCommand();
        }
    }
    private void inventory() {
        if (adventure.hasAnyItems()) {
            printInventory();
        } else {
            printEmpty();
        }
    }

    // Eat
    private void eat(String name) {
        switch (adventure.eat(name)) {
            case NOT_FOUND -> printUnknownCommand();
            case NOT_EDIBLE -> printNotEdible(name);
            case CONSUMED -> printEat(name);
        }
    }

    // Attack
    private void attack(String name) {
        switch (adventure.attack(name)) {
            case NO_WEAPON -> printNoWeapon();
            case NO_AMMO   -> printNoAmmo(adventure.getWeaponString());
            case NO_ENEMY  -> printNoEnemy(name);
            case SUCCESS   -> {
                printAttack(adventure.getWeaponString(), adventure.getUses(),
                        name.isEmpty() ? "empty air" : name);
                if (!name.isEmpty()) {
                    IO.println("\u001B[31mThe troll strikes back!\u001B[0m");
                    printHealth();
                }
            }
            case KILL      -> printKill(adventure.getWeaponString(), adventure.getUses(), name);
        }
    }

    // ================[ PRINTS ]================

    // Movement
    public void printRoom(Room room) {
        IO.println("You enter " + room.getName());
        if (room.getVisited()) {
            IO.println("You've already been here...");
        } else {
            IO.println(room.getDescription());
            room.setVisited(true);
        }

        String contents = adventure.getContents();
        String enemies = adventure.getEnemies();
        if (!contents.isEmpty() || !enemies.isEmpty()) {
            IO.println("You see:");
            IO.print(contents);
            IO.print(enemies);
        }
    }
    public void printCannotGo() { IO.println("You cannot go that way"); }



    // Look
    public void printRoomDescription(Room room) {
        IO.println(room.getDescription());

        String contents = adventure.getContents();
        String enemies = adventure.getEnemies();
        if (!contents.isEmpty() || !enemies.isEmpty()) {
            IO.println("You see:");
            IO.print(contents);
            IO.println(enemies);
        }
    }
    public void printInspect(Item item) { IO.println(item.getDescription()); }

    // Equip / Unequip
    public void printEquip(String name) { IO.println("You equip the " + name); }
    public void printUnequip() { IO.println("You unequip your weapon"); }
    public void printNoWeapon() { IO.println("You have no weapon equipped."); }

    // Grab
    public void printGrab(String name) { IO.println("You grab the " + name); }

    // Drop
    public void printDrop(String name) { IO.println("You drop the " + name); }

    // Health
    public void printHealth() {
        int hp = adventure.getCurrentHealth();
        int maxHP = adventure.getMaxHealth();
        IO.print("Health: " + hp + " - ");
        if (hp <= 0) IO.println("You are dead...");
        else if (hp == maxHP) IO.println("In perfect health!");
        else if (hp >= maxHP / 2) IO.println("In good health.");
        else if (hp <= maxHP / 4) IO.println("In bad health");
    }

    // Eat
    public void printEat(String name) { IO.println("You eat the " + name); }
    public void printNotEdible(String name) { IO.println("You cannot eat the " + name); }

    //Attack
    public void printNoEnemy(String name) {
        IO.println("There is no " + name + " here to attack.");
    }

    public void printNoAmmo(String weapon) {
        IO.println("Your " + weapon + " is out of ammo.");
    }

    public void printAttack(String weapon, int ammo, String target) {
        IO.println("You " + verb(ammo) + " the " + target + " with your " + weapon + ".");
        printAmmo(weapon, ammo);
    }

    public void printKill(String weapon, int ammo, String target) {
        IO.println("You " + verb(ammo) + " the " + target + " with your " + weapon + ".");
        IO.println("The " + target + " drops dead.");
        printAmmo(weapon, ammo);
    }

    private String verb(int ammo) {
        return ammo < 0 ? "attack" : "shoot";
    }

    private void printAmmo(String weapon, int count) {
        switch (count) {
            case -1 -> { }
            case 0  -> printNoAmmo(weapon);
            case 1  -> IO.println("1 shot left.");
            default -> IO.println(count + " shots left.");
        }
    }

    // Inventory
    public void printInventory() {
        IO.println("You are carrying:");
        IO.print(adventure.getInventory());
    }
    public void printEmpty() { IO.println("Inventory is empty."); }

    // Help
    public void printHelp() {
        IO.println("Commands:");
        IO.println(" go north / north / n");
        IO.println(" go east  / east  / e");
        IO.println(" go south / south / s");
        IO.println(" go west  / west  / w");
        IO.println(" look             - describe the current room");
        IO.println(" look [object]    - inspect nearby item or enemy");
        IO.println(" attack [target]  - attack an enemy");
        IO.println(" grab [object]    - pick up an object");
        IO.println(" drop [object]    - drop an object in room");
        IO.println(" eat [food]       - consume a food item");
        IO.println(" equip [weapon]   - equip a weapon");
        IO.println(" unequip          - unequip your weapon");
        IO.println(" inventory        - open player inventory");
        IO.println(" health           - show health");
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

    public void printGoodbye() { IO.println("Goodbye!"); }

    public void printUnknownCommand() { IO.println("Unknown command. Type HELP for a list of commands."); }
}