package studyGroupF;

import studyGroupF.data.FileIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Monster {
    private int HP;
    private int bossHP;
    private int damage;
    private String monsterType;
    private boolean isBoss = false;
    private FileIO fileIO = new FileIO();;

    Level level = new Level();

    public Monster(String monsterType, int HP, int damage) {
        this.monsterType = monsterType;
        this.HP = HP;
        this.damage = damage;
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
