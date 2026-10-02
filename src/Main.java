void main() {
    World world = new World();
    Player player = new Player(world);
    Adventure adventure = new Adventure(player);
    ConsoleUI UI = new ConsoleUI(adventure);
    UI.start();
}