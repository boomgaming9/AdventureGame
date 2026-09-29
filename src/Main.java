void main() {
    World world = new World();
    Player player = new Player(world, world.getStartRoom());
    Adventure adventure = new Adventure(player);
    ConsoleUI UI = new ConsoleUI(adventure);
    UI.start();
}