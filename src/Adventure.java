public class Adventure {
    private final UserInterface UI = new UserInterface();
    private Player player;

    public void start() {
        player = new Player(new World().getStartRoom());
        UI.printWelcome();
        UI.printRoom(player.getCurrentRoom());

        boolean isRunning = true;

        while (isRunning) {
            String command = UI.readCommand();

            switch (command) {
                case "north", "east", "south", "west" -> move(command);
                case "look" -> UI.printRoom(player.getCurrentRoom());
                case "help" -> UI.printHelp();
                case "exit" -> {
                    UI.printGoodbye();
                    isRunning = false;
                }
                default -> UI.printUnknownCommand();
            }
        }
    }

    private void move(String direction) {
        if (player.move(direction)) {
            UI.printRoom(player.getCurrentRoom());
        } else {
            UI.printCannotGo();
        }
    }
}