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
    private String currentPhase = "Idle";
    String[] gamePhases = {"Idle", "Combat", "Shop"};

    public Level() {
    }

    public String getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(String currentPhase) {
        this.currentPhase = currentPhase;
    }

    public String[] getGamePhases() {
        return gamePhases;
    }

    public void setGamePhases(String[] gamePhases) {
        this.gamePhases = gamePhases;
    }

    public void addRandomsFieldsToLevel() {
        Random r = new Random();
        int i = r.nextInt((15 - 9) + 1) + 9;

        fieldList = new FieldList(i);

        fields = fieldList.getFields();
    }

    public void doFieldFunction(Item item, Player player, int index) {
        fieldList.doFunction(item, player, index);
    }

    public Field getCurrentField(int index) {
        return fieldList.currentField(index);
    }

    public void printFieldArray(int playerCurrentTile) {
        StringBuilder allFields = new StringBuilder("Level: ");

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


    public Monster createMonster() throws IOException {
        //Initializing temporary variables that will be overridden a bit later. Values should be set to 0 in the start
        String monsterType = "";
        int HP = 0;
        int damage = 0;
        int minHP = 0;
        int maxHP = 0;
        int minDamage = 0;
        int maxDamage = 0;

        ArrayList<String> data;
        data = fileIO.readMonsterData();

        Random line = new Random();
        int specificLine = line.nextInt(fileIO.getAmountOfLinesInMonsterDataFile() - 1) + 1;
        //int specificLine = 3; //To test for specific line (To get the wanted line, just -1 SpecificLine)

        int counter = 0;

        for (String s : data) {
            //System.out.println(s);
            if (counter == specificLine) {
                String[] values = s.split(", ");

                monsterType = values[0];
                minHP = Integer.parseInt(values[1]);
                maxHP = Integer.parseInt(values[2]);
                minDamage = Integer.parseInt(values[3]);
                maxDamage = Integer.parseInt(values[4]);
            }
            counter++;
        }

        HP = randomFromMinMax(minHP, maxHP);
        damage = randomFromMinMax(minDamage, maxDamage);

        Monster monster = new Monster(monsterType, HP, damage);
        return monster;
    }

    int randomFromMinMax(int min, int max) {
        Random r = new Random();
        int randomMinMax = r.nextInt((max - min) + 1) + min;
        return randomMinMax;
    }

    private void increaseDifficulty() {
        monster.setDamage(monster.getDamage() + 10);

         /*
        String[] monsterName = {"Skeleton","Zombie"};
        int skeletonBaseHP = 5;
        int skeletonHP = (skeletonBaseHP * level.currentLevel());
        damage = (1*level.currentLevel());


    public Monster newSkeleton(){
        return new Monster("Skeleton", createMonster().skeletonHP, 5);
    }
    public static Monster newZombie(){
        return new Monster("Zombie", newZombie().HP, 10);
    }
     */
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
