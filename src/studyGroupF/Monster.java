package studyGroupF;

public class Monster {

    private int HP;
    private int bossHP;
    private int damage;
    private String monsterName;

    Level level = new Level();

    public void createMonster(){
        String[] monsterName = {"Skeleton","Zombie"};
        HP = (10*level.currentLevel());
        damage = (1*level.currentLevel());

    }

}
