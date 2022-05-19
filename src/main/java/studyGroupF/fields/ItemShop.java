package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

public class ItemShop extends Field{

    public ItemShop(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) {

    }

}
