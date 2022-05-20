package studyGroupF;

import studyGroupF.data.FileIO;
import studyGroupF.fields.FieldList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import studyGroupF.fields.*;
import studyGroupF.player.Item;
import studyGroupF.player.Player;

public class Level {
    FileIO fileIO = new FileIO();
    Monster monster;
    Field field;
    FieldList fieldList;
    Field[] fields;

    public int levelNr = 1;

    public Level() {
    }

    public void addRandomsFieldsToLevel() {
        Random r = new Random();
        int i = r.nextInt((15 - 9) + 1) + 9;

        fieldList = new FieldList(i);

        fields = fieldList.getFields();
    }

    public void doFieldFunction(Item item, Player player, int index) throws IOException {
        fieldList.doFunction(item, player, index);
    }

    public Field getCurrentField(int index) {
        return fieldList.currentField(index);
    }

    public void printFieldArray(int playerCurrentTile) {
        StringBuilder allFields = new StringBuilder("Level map: ");

        for (int i = 0; i < fields.length; i++) {
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

    public void loadPreviousFieldsToLevel() {
        ArrayList<String> data;
        data = fileIO.readLevelData();

        for (String s : data) {
            //System.out.println(s);
            String[] values = s.split(", ");

            int[] id = new int[values.length];

            for (int i = 0; i < values.length; i++) {
                id[i] = Integer.parseInt(values[i]);
            }

            fieldList = new FieldList(id);

            fields = fieldList.getFields();
        }

    }

    public void increaseDifficulty(int levelNr) {
        

        double statIncrease = levelNr;

        monster.setStatIncrease(statIncrease);


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
