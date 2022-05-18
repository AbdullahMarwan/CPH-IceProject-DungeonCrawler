package studyGroupF;

import studyGroupF.data.FileIO;
import studyGroupF.fields.FieldList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import studyGroupF.fields.*;

public class Level {
    FileIO fileIO = new FileIO();
    Monster monster;
    Field field;
    FieldList fieldList;
    Field[] fields;

    public void addFieldsToArray() {
        Random r = new Random();
        int i = r.nextInt((15 - 9) + 1) + 9;

        fieldList = new FieldList(i);

        fields = fieldList.getFields();
    }

    public void printFieldArray() {
        for (Field f : fields) {
            System.out.println(f);
        }
    }

    public Monster createMonster() throws IOException {
        //Initializing temporary variables that will be overriden a bit later. Values should be set to 0 in the start
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


}
