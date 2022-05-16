package studyGroupF;

public class Monster {

    private int HP;
    private int bossHP;
    private int damage;
    private String monsterName;

    Level level = new Level();

    public Monster(String monsterName, int HP, int damage) {
        this.monsterName = monsterName;
        this.HP = HP;
        this.damage = damage;

    }

    public void createMonster(){
        String[] monsterName = {"Skeleton","Zombie"};
        int skeletonBaseHP = 5;
        int skeletonHP = (skeletonBaseHP * level.currentLevel());
        damage = (1*level.currentLevel());
    }

    public Monster newSkeleton(){
        return new Monster("Skeleton", createMonster().skeletonHP, 5);
    }
    public static Monster newZombie(){
        return new Monster("Zombie", newZombie().HP, 10);
    }

}
