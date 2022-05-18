package studyGroupF.fields;

import studyGroupF.player.Item;

public abstract class Field {
    protected String fieldType;
    protected int fieldID;
    protected Item item;

    public Field(Item item, String fieldType, int fieldID) {
        this.item = item;
        this.fieldType = fieldType;
        this.fieldID = fieldID;
    }

    abstract void doFunction();

    abstract void introduction();

    abstract int returnGold();

    abstract Item returnLoot();

    @Override
    public String toString() {
        return "Field Type: " + fieldType
                + "\n Field ID: " + fieldID;
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
