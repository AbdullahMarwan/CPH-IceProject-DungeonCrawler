package studyGroupF.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import studyGroupF.data.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@JsonIgnoreProperties(value = {
        "HP",
        "statIncrease"
})
public class Monster {
    private int HP;
    private int minHP;
    private int maxHP;

    private int minDMG;
    private int maxDMG;
    private int damage;
    private String monsterType;

    double statIncrease = 1.0;

    public Monster(String monsterType, int HP, int damage, int maxHP) {
        this.monsterType = monsterType;
        this.HP = HP;
        this.damage = damage;
        this.maxHP = maxHP;
    }

    public Monster() {
    }

    public ArrayList<Monster> getMonsters() {
        return DataManager.getInstance().getGameValues().getMonsters();
    }

    public Monster createMonster(int levelNr) {
        double statIncrease = increaseDifficulty(levelNr);

        Monster monster = getMonsters().get(new Random().nextInt(getMonsters().size()));
        monster.setHP((int) (randomFromMinMax(monster.getMinHP(), monster.getMaxHP()) * statIncrease));
        monster.setDamage((int) (randomFromMinMax(monster.getMinDMG(), monster.getMaxDMG()) * statIncrease));
        monster.setMaxHP(monster.getHP());

        return monster;
    }

    int randomFromMinMax(int min, int max) {
        Random r = new Random();
        int randomMinMax = r.nextInt((max - min) + 1) + min;
        return randomMinMax;
    }

    public double increaseDifficulty(int levelNr) {
        return Math.pow(1.5, levelNr - 1);
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
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

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public double getStatIncrease() {
        return statIncrease;
    }

    public void setStatIncrease(double statIncrease) {
        this.statIncrease = statIncrease;
    }

    public int getMinHP() {
        return minHP;
    }

    public void setMinHP(int minHP) {
        this.minHP = minHP;
    }

    public int getMinDMG() {
        return minDMG;
    }

    public void setMinDMG(int minDMG) {
        this.minDMG = minDMG;
    }

    public int getMaxDMG() {
        return maxDMG;
    }

    public void setMaxDMG(int maxDMG) {
        this.maxDMG = maxDMG;
    }

    @Override
    public String toString() {
        return "Monster type: " + monsterType
                + "\n Damage: " + damage
                + "\n HP: " + HP;
    }
}
