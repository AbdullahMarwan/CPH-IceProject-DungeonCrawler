package studyGroupF.fields;

import studyGroupF.player.Item;

public class WeaponSmith extends Field{
    public WeaponSmith(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction() {

    }

    @Override
    void introduction() {

    }

    @Override
    int returnGold() {
        return 0;
    }

    @Override
    Item returnLoot() {
        return null;
    }
}
