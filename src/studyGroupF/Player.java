package studyGroupF;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private int HP;
    private int damage;
    private int gold;
    private int currentLevel;
    private String playerName;
    private Storage storage;

    private ArrayList<Item> playerItems;

    Scanner sc = new Scanner(System.in);

    //Constructor used to load previous available playerdata
    public Player(int HP, int damage, int gold, int currentLevel, String playerName) {
        this.HP = HP;
        this.damage = damage;
        this.gold = gold;
        this.currentLevel = currentLevel;
        this.playerName = playerName;
    }

    //When creating a new player, the user is asked for a name
    //Player stats are loaded with the defaults
    public Player() {
        System.out.println("\nWhat's your name?");
        this.playerName = sc.nextLine();
    }


    void viewPlayerStats() {

    }

    void viewStorage() {

    }

    void restToHeal() {

    }
}
