package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

public class EmptyField extends Field {
    public EmptyField(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) {

    }

    @Override
    void introduction() {
        System.out.println("You landed on an Empty Field, enjoy the view");
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
