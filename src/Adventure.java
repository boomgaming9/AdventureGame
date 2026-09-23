public class Adventure {
    public void StartGame(){
        Room room1 = new Room("Room 1", "This is the first room");
        Room room2 = new Room("Room 2", "This is the second room");
        Room room3 = new Room("Room 3", "This is the third room");
        Room room4 = new Room("Room 4", "This is the fourth room");
        Room room5 = new Room("Room 5", "This is the fifth room");
        Room room6 = new Room("Room 6", "This is the sixth room");
        Room room7 = new Room("Room 7", "This is the seventh room");
        Room room8 = new Room("Room 8", "This is the eight room");
        Room room9 = new Room("Room 9", "This is the ninth room");

        room1.setEast(room2);
        room1.setSouth(room4);

        room2.setWest(room1);
        room2.setEast(room3);

        room3.setWest(room2);
        room3.setSouth(room6);

        room4.setNorth(room1);
        room4.setSouth(room7);

        room5.setSouth(room8);

        room6.setNorth(room3);
        room6.setSouth(room9);

        room7.setNorth(room4);
        room7.setEast(room8);

        room8.setWest(room7);
        room8.setNorth(room5);
        room8.setEast(room9);

        room9.setWest(room8);
        room9.setNorth(room6);

        Room currentRoom = room1;
        boolean isFinished = false;
        IO.println("Welcome to the Adventure Game!");

        while (!isFinished){
            String command = IO.readln(">").toLowerCase();

            switch (command){
                case "go north" -> {
                    IO.println("Going north...");
                    if (!(currentRoom.north == null)){
                        currentRoom = currentRoom.north;
                        //IO.println(currentRoom);
                        currentRoom.isVisited();

                    } else {
                        IO.println("You can't go that way!");
                        continue;
                    }
                }
                case "go south" -> {
                    IO.println("Going south...");
                    if (!(currentRoom.south == null)){
                        currentRoom = currentRoom.south;
                        //IO.println(currentRoom);
                        currentRoom.isVisited();
                    } else {
                        IO.println("You can't go that way!");
                        continue;
                    }
                }
                case "go east" -> {
                    IO.println("Going east...");
                    if (!(currentRoom.east == null)){
                        currentRoom = currentRoom.east;
                        IO.println(currentRoom);
                        currentRoom.isVisited();

                    } else {
                        IO.println("You can't go that way!");
                        continue;
                    }
                }
                case "go west" -> {
                    IO.println("Going west");
                    if (!(currentRoom.west == null)){
                        currentRoom = currentRoom.west;
                        IO.println(currentRoom);
                        currentRoom.isVisited();

                    } else {
                        IO.println("You can't go that way!");
                    }
                }
                case "exit" -> {
                    IO.println("Exiting game...");
                    isFinished = true;
                }
                case "help" -> {
                    IO.println("List of all possible commands: ");
                    IO.println("Go north, go south, go east, go west, exit, help, look");
                }
                case "look" -> {
                    IO.println(currentRoom);
                    IO.println(currentRoom.description);

                }
            }
        }

    }
}
