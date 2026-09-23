public class Main {
    public static void main() {
        Adventure adventure = new Adventure();
        UserInterface ui = new UserInterface(adventure);
        ui.start();
    }
}