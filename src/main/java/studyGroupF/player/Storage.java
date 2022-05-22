package studyGroupF.player;

import java.util.ArrayList;

public class Storage {

    public ArrayList<Item> addLootToStorage(ArrayList<Item> playerItems, Item item) {
        playerItems.add(item);

        return playerItems;
    }
}