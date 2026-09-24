public class UserInterface {
    private Room currentRoom;
    private final UserInterface UI = new UserInterface();

    public String readCommand() {
        return IO.readln().trim().toLowerCase();
    }


    public void printRoom(Room room) {
        IO.println("You are in " + room.getName());
        IO.println(room.getDescription());
    }

    public void printCannotGo() {
        IO.println("You cannot go that way");
    }

}