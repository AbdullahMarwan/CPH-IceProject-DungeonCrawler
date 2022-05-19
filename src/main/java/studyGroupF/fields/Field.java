package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

public abstract class Field {
    protected String fieldType;
    protected int fieldID;
    protected Item item;
    protected Player player;

    public Field(Item item, String fieldType, int fieldID) {
        this.item = item;
        this.fieldType = fieldType;
        this.fieldID = fieldID;
    }

    abstract void doFunction(Item item, Player player);

    abstract void introduction();

    abstract int returnGold();

    abstract Item returnLoot();

    @Override
    public String toString() {
        return fieldType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }
}
