public class Room {
    String name;
    String description;
    Room north;
    Room east;
    Room south;
    Room west;
    boolean isVisited;

    public Room(String name, String description){
        this.name = name;
        this.description = description;
        this.isVisited = false;

    }
    // metode som husker, om spilleren har været i et rum; giver beskrivelse på baggrund af det.
    public void isVisited(){
        if (isVisited == false){
            IO.println(description);
        } else {
            IO.println("You've been here...");
        }
        this.isVisited = true;

    }


    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public void setNorth(Room room){
        this.north = room;
    }
    public Room getNorth(){
        return north;
    }
    public void setEast(Room room){
        this.east = room;
    }
    public Room getEast(){
        return east;
    }
    public void setSouth(Room room){
        this.south = room;
    }
    public Room getSouth(){
        return south;
    }
    public void setWest(Room room){
        this.west = room;
    }
    public Room getWest(){
        return west;
    }
    @Override
    public String toString(){
        return String.format("""
                You are in %s
                %s""", name, description);
    }
}
