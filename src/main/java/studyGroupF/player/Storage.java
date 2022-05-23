package studyGroupF.player;

import java.util.ArrayList;

public class Storage {
    private Item item;

    public void loadPlayerItemsFromFileToArrayList() {

    }

    public ArrayList<Item> addLootToStorage(ArrayList<Item> playerItems, Item item) {
        playerItems.add(item);

        return playerItems;
    }

    public void removeItemFromPlayerItemList() {

    }

}
