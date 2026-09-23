public class UserInterface {
    private final Adventure adventure;

    public UserInterface (Adventure adventure){
        this.adventure = adventure;
    }

    public void start() {
        IO.println("Welcome to the adventure! Type 'help' for a list of commands.");
        printCurrentRoom();

        boolean running = true;
        while (running) {
            IO.print("> ");

            String line = IO.readln("> ");
            if (line == null) {
                IO.println("Goodbye!");
                break;
            }
            String input = line.trim().toLowerCase();

            String[] words = input.split("\\s+");

            String first = words[0];
            String second = words.length > 1 ? words[1] : "";

            switch (first) {
                case "go" -> go(second);
                case "north", "n", "east", "e", "south", "s", "west", "w" -> go(first);
                case "look" -> IO.println("looking around");
                case "help" -> printHelp();
                case "exit" -> {
                    IO.println("Goodbye!");
                    running = false;
                }
                case "" -> { }
                default -> IO.println("I don't understand '" + input + "'. Type 'help' for commands.");
            }
        }
    }

    private void go(String word) {
        String direction = parseDirection(word);
        if (direction == null) {
            IO.println("Go where? Try north, east, south or west.");
        } else {
            IO.println("going " + direction);
        }
    }

    private String parseDirection(String word) {
        return switch (word) {
            case "north", "n" -> "north";
            case "east", "e" -> "east";
            case "south", "s" -> "south";
            case "west", "w" -> "west";
            default -> null;
        };
    }

    private void printHelp() {
        IO.println("""
                Commands:
                  go north / go east / go south / go west  - move in a direction
                  (short forms: north, n, go n, etc.)
                  look  - describe the room you are in
                  help  - show this list
                  exit  - quit the game""");
    }

    private void printCurrentRoom (){
        Room room = adventure.getCurrentRoom();
        IO.println ("you are in Room " + room.getName());
        IO.println(room.getDescription());


    }
}
