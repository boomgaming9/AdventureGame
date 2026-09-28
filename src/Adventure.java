public class Adventure {
    private final ConsoleUI UI = new ConsoleUI();
    private Player player;
    private World world;

    public Adventure(Player player, World world) {
        this.player = player;
        this.world = world;
        UI.setAdventure(this);
    }

    public Player getPlayer() {
        return player;
    }

    private void move(String direction) {
        if (player.move(direction)) {
            UI.printRoom(player.getCurrentRoom());
        } else {
            UI.printCannotGo();
        }
    }

    private void inspect(String itemName) {
        Room room = player.getCurrentRoom();
        Item item = room.getItem(itemName);

        if (item == null) {
            UI.printUnknownCommand();
            return;
        }
        UI.printInspect(item);

        for (Item content : item.getContents()) {
            if (!room.hasItem(content.getName()) && !player.hasItem(content.getName())) {
                room.addItem(content);
            }
        }
    }

    private void grab(String itemName) {
        Room room = player.getCurrentRoom();
        Item item = room.getItem(itemName);
        if (item == null) {
            UI.printUnknownCommand();
            return;
        }
        if (!item.can("pickup")) {
            IO.println("You can't grab the " + item.getName() + ".");
            return;
        }
        room.removeItem(itemName);
        player.grab(item);
        UI.printGrab(item);
    }

    private void drop(String itemName) {
        Room room = player.getCurrentRoom();
        Item item = player.getItem(itemName);
        if (item == null) {
            UI.printUnknownCommand();
            return;
        }
        room.addItem(item);
        player.drop(item);
        UI.printDrop(item);
    }
}