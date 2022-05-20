package studyGroupF;

import studyGroupF.data.FileIO;

public class Monster {
    private int HP;
    private int bossHP;
    private int damage;
    private String monsterType;
    private boolean isBoss = false;
    private FileIO fileIO = new FileIO();

    public Monster(String monsterType, int HP, int damage) {
        this.monsterType = monsterType;
        this.HP = HP;
        this.damage = damage;
    }

    public Monster() {

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

    //TODO method for setting up BossFights, where the chosen MonsterType becomes a Boss with 3-5x the stats of a normal minion of its typ

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getBossHP() {
        return bossHP;
    }

    public void setBossHP(int bossHP) {
        this.bossHP = bossHP;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    @Override
    public String toString() {
        return "Monster type: " + monsterType
                + "\n Damage: " + damage
                + "\n HP: " + HP;
    }
}
