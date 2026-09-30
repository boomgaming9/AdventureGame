public class Adventure {
    private Player player;

    public Adventure(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean move(String direction) {
       return player.move(direction);
    }

    public boolean grab(String itemName) {
        Room room = player.getCurrentRoom();
        Item item = room.getItem(itemName);
        if (item == null) {
            return false;
        }
        if (!item.can("pickup")) {
            return false;
        }
        room.removeItem(itemName);
        player.grab(item);
        return true;
    }

    public boolean drop(String itemName) {
        Room room = player.getCurrentRoom();
        Item item = player.getItem(itemName);
        if (item == null) {
            return false;
        }
        room.addItem(item);
        player.drop(item);
        return true;
    }

    public EatResult eat(String itemName) {
        return player.eat(itemName);      // this ?
    }

    /* public Item getItem(String itemName) {
        return player.getItem(itemName);
    } */    // potentially redundant
}