public class ConsoleUI {

    public String readCommand() {
        return IO.readln("> ").trim().toLowerCase();
    }

    public void firstRoom(Room room) {
        IO.println("You wake up in a strange place. Nine rooms are connected, and one of them hides a secret.");
        IO.println("Type HELP at any time to see your options.\n");
        IO.println("You are in " + room.getName());
    }

    public void printRoom(Room room) {
        IO.println("You are in " + room.getName());
        IO.println(room.getDescription());
    }

    public void printCannotGo() {
        IO.println("You cannot go that way");
    }
}