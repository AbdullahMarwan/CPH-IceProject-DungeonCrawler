package studyGroupF.fields;

import studyGroupF.player.Item;

abstract class Field {
    protected String fieldType;
    protected int fieldID;
    protected Item item;

    public Field (Item item, String fieldType, int fieldID) {
        this.item = item;
        this.fieldType = fieldType;
        this.fieldID = fieldID;
    }

    abstract void doFunction(Item item);
}
