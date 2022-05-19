package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

public class EmptyField extends Field {
    public EmptyField(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) {
        System.out.println("You landed on an Empty Field, enjoy the view");
    }

}
