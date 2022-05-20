package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

public class WeaponSmith extends Field {
    public WeaponSmith(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) {
    }

}
