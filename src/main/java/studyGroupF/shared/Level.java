package studyGroupF.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import studyGroupF.fields.FieldList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import studyGroupF.fields.*;
import studyGroupF.player.Item;
import studyGroupF.player.Player;

@JsonIgnoreProperties(value = {"fieldList"})
public class Level {
    public FieldList fieldList;
    public ArrayList<Field> fields;
    public int levelNr = 1;
    private int maxFields = 15;
    private int minFields = 9;

    public Level() {
        addRandomsFieldsToLevel();
    }

    public void addRandomsFieldsToLevel() {
        Random r = new Random();
        int i = r.nextInt((maxFields - minFields) + 1) + minFields;

        fieldList = new FieldList(i);

        fields = fieldList.getFields();
    }

    public void loadPreviousFieldsToLevel(ArrayList<Integer> fieldIDs) {

        fieldList = new FieldList(fieldIDs);

        fields = fieldList.getFields();
    }

    public void doFieldFunction(Item item, Player player, int index) throws IOException {
        fieldList.doFunction(item, player, index);
    }

    public Field getCurrentField(int index) {
        return fieldList.currentField(index);
    }

    public void printFieldArray(int playerCurrentTile) {
        StringBuilder allFields = new StringBuilder("LEVEL " + levelNr + " MAP: ");

        for (int i = 0; i < fields.size(); i++) {
            String fieldToString = "";
            if (playerCurrentTile == i) {
                fieldToString = fieldToString(0);
            } else {
                fieldToString = fieldToString(fieldList.currentField(i).getFieldID());
            }

            allFields.append(fieldToString);
        }

        System.out.println(allFields);
    }

    public String fieldToString(int id) {

        return switch (id) {
            case 0 -> "[+]"; //Player
            case 1 -> "[M]"; //MonsterBattle
            case 2 -> "[G]"; //LootChest
            case 3 -> "[I]"; //ItemShop
            case 4 -> "[W]"; //WeaponSmith
            case 5 -> "[0]"; //EmptyField
            case 6 -> "[C]"; //CampFire
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

    public ArrayList<Integer> returnFieldsIDs() {
        return fieldList.fieldsToIDList(fields);
    }

    @Override
    public String toString() {
        return "Level Nr: " + levelNr;
    }

    public int getLevelNr() {
        return levelNr;
    }

    public void setLevelNr(int levelNr) {
        this.levelNr = levelNr;
    }
}
