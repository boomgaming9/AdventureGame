public class Adventure {
    private final Player player;

    public Adventure(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }


    // Functions
    public boolean move(String direction) {
        return player.move(direction);
    }

    public boolean grab(String itemName) {
        return player.grab(itemName);
    }

    public boolean drop(String itemName) {
        return player.drop(itemName);
    }

    public EatResult eat(String itemName) {
        return player.eat(itemName);
    }

    public EquipResult equip (String itemName){
        return player.equip(itemName);
    }

    /* public Item getItem(String itemName) {
        return player.getItem(itemName);
    } */    // potentially redundant
}