public class Adventure {
    private final ConsoleUI ui = new ConsoleUI();
    private Player player;

    public void start() {
        player = new Player(new World().getStartRoom());
        ui.printWelcome();
        ui.printRoom(player.getCurrentRoom());

        boolean isRunning = true;

        while (isRunning) {
            String kommando = ui.readCommand();

            switch (kommando) {
                case "north", "east", "south", "west" -> move(kommando);
                case "look" -> ui.printRoom(player.getCurrentRoom());
                case "help" -> ui.printHelp();
                case "exit" -> {
                    ui.printGoodbye();
                    isRunning = false;
                }
                default -> ui.printUnknownCommand();

                //remove while loop
            }
        }
    }

    private void move(String direction) {
        if (player.move(direction)) {
            ui.printRoom(player.getCurrentRoom());
        } else {
            ui.printCannotGo();
        }
    }
}