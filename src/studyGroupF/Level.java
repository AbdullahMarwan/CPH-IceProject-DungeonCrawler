package studyGroupF;

import studyGroupF.data.FileIO;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class Level {
    ArrayList<Field> fields;
    FileIO fileIO = new FileIO();
    Monster monster;

    public void addFieldToArrayList() {

    }

    public int currentLevel() {

        return currentLevel();
    }

    public Monster createMonster() throws IOException {
        //Initiliazing temporary variables that will be overriden a bit later. Values should be set to 0 in the start
        String monsterType = "";
        int HP = 0;
        int damage = 0;
        int minHP = 0;
        int maxHP = 0;
        int minDamage = 0;
        int maxDamage = 0;





        ArrayList<String> data;
        data = fileIO.readMonsterData();

        //TODO set 100 in SpecificLine to amount of lines in the file
        System.out.println("Amount of lines in MonsterData " + fileIO.getAmountOfLinesInMonsterDataFile());
        Random line = new Random();
        int specificLine = line.nextInt(fileIO.getAmountOfLinesInMonsterDataFile()-1) + 1;
        //int specificLine = 3; //Temp solution
        System.out.println("SpecificLine Chosen: " + specificLine);

        int counter = 0;

        for (String s : data) {
            //System.out.println(s);
            if (counter == specificLine) {
                System.out.println("Counter: " + counter);
                String[] values = s.split(", ");

                monsterType = values[0];
                minHP = Integer.parseInt(values[1]);
                maxHP = Integer.parseInt(values[2]);
                minDamage = Integer.parseInt(values[3]);
                maxDamage = Integer.parseInt(values[4]);
            }
            counter++;
        }

        Random r = new Random();
        HP = r.nextInt(maxHP) + minHP;

        Random t = new Random();
        damage = t.nextInt(maxDamage) + minDamage;

        Monster monster = new Monster(monsterType, HP, damage);
        return monster;
        //TODO add the new monster to empty Arraylist
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
