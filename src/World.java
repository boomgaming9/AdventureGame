import java.util.Set;

public class World {
    private final Room room1, room2, room3,
            room4, room5, room6,
            room7, room8, room9;

    /* 3x3 grid:
       room1 - room2 - room3
         |               |
       room4   room5   room6
         |       |       |
       room7 - room8 - room9 */

    public World() {
        Item torch = new Item(
                "torch",
                "A wooden torch. Its head is wrapped in oil-soaked cloth."
        );
        MeleeWeapon axe = new MeleeWeapon(
                "axe",
                "rusty",
                "A rusty axe of a bygone era.",
                2
                );
        RangedWeapon crossbow = new RangedWeapon(
                "crossbow",
                "heavy",
                "A heavy crossbow. Standard military issue.",
                3,
                20
        );
        Item key = new Item(
                "key",
                "iron",
                "A small iron key. It's cold to the touch."
        );
        Item table = new Item(
                "table",
                "A writer's desk it would seem."
        );
        Container chest = new Container(
                "chest",
                "sturdy",
                "A sturdy looking chest. It seems to be locked"
        );
        Item diamond = new Item(
                "diamond",
                "It's like a reward."
        );
        Food burger = new Food(
                "burger",
                "vennison",
                "A juicy vennison burger.",
                45
        );
        Food burger2 = new Food(
                "burger",
                "mushroom",
                "A juicy vennison burger.",
                25
        );
        Food mushroom = new Food(
                "mushroom",
                "dubious",
                "A dubious looking mushroom.",
                -45
        );

        room1 = new Room("Room 1", "An unremarkable room with two doors.");
        room1.addItem(burger);
        room2 = new Room("Room 2", "A narrow corridor with torches flickering on the walls.");
        room2.addItem(torch);
        room3 = new Room("Room 3", "A dusty library with ancient books on crumbling shelves.");
        room3.addItem(burger);
        room4 = new Room("Room 4", "A dark hallway. You hear dripping in the distance.");
        room4.addItem(burger2);
        room5 = new Room("Room 5", "A mysterious chamber glowing with an eerie blue light. A chest sits at the center.");
        room5.addItem(chest);
        chest.addContent(diamond);
        room6 = new Room("Room 6", "An abandoned guard post with a table and rusted weapons on the walls.");
        room6.addItem(table);
        room6.addItem(key);
        room6.addItem(axe);
        room6.addItem(crossbow);
        room7 = new Room("Room 7", "A mossy grotto with the sound of dripping water echoing.");
        room7.addItem(mushroom);
        room8 = new Room("Room 8", "A vast underground lake stretching into the darkness.");
        room9 = new Room("Room 9", "A cold cave where your breath forms small clouds of mist.");
        // take 5 damage each time you enter

        /* Default, no walls placed
        link(room1, null,  room2, room4, null);
        link(room2, null,  room3, room5, room1);
        link(room3, null,  null,  room6, room2);
        link(room4, room1, room5, room7, null);
        link(room5, room2, room6, room8, room4);
        link(room6, room3, null,  room9, room5);
        link(room7, room4, room8, null,  null);
        link(room8, room5, room9, null,  room7);
        link(room9, room6, null,  null,  room8); */
        link(room1, null, room2, room4, null);
        link(room2, null, room3, null, room1);
        link(room3, null, null, room6, room2);
        link(room4, room1, null, room7, null);
        link(room5, null, null, room8, null);
        link(room6, room3, null, room9, null);
        link(room7, room4, room8, null, null);
        link(room8, room5, room9, null, room7);
        link(room9, room6, null, null, room8);
        Enemy troll = new Enemy(
                "troll",
                "a cave troll",
                "A large, menacing cave troll.",
                30,
                sword,
                room8
        );
        room8.addEnemy(troll);

        Enemy bandit = new Enemy(
                "bandit",
                "a sneaky bandit",
                "A sneaky bandit with a revolver.",
                20,
                revolver,
                room6
        );
        room6.addEnemy(bandit);
    }

    private void link(Room current, Room north, Room east, Room south, Room west) {
        current.setNorth(north);
        current.setEast(east);
        current.setSouth(south);
        current.setWest(west);
    }

    public Room getStartRoom() {
        return room1;
    }
}
