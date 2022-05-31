package studyGroupF.player;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String playerName;
    private int maxHP = 100;
    private int currentHP = 100;
    private int damage = 10;
    private int gold = 15;
    private int currentLevel = 1;
    private int currentTile = 0;
    private int amountOfPotions = 3;
    private int extraGoldGain = 1;
    private Item item;

    private ArrayList<Item> playerItems = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    //Constructor used to load previous available playerdata
    public Player(String playerName, int maxHP, int currentHP, int damage, int gold, int currentLevel, int currentTile, int amountOfPotions, int extraGoldGain) {
        this.playerName = playerName;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.damage = damage;
        this.gold = gold;
        this.currentLevel = currentLevel;
        this.currentTile = currentTile;
        this.amountOfPotions = amountOfPotions;
        this.extraGoldGain = extraGoldGain;
    }

    //When creating a new player, the user is asked for a name
    //Player stats are loaded with the defaults

    public Player() {

    }

    public void addLootToPlayer(Item item, Player player) {
        playerItems.add(item);
        item.useItems(playerItems, player);
    }

    public void addGoldToPlayer(int goldGiven) {
        int combinedGold = goldGiven + getExtraGoldGain();
        System.out.println("Gold Received: " + goldGiven + " + Extra Gold Gain: " + getExtraGoldGain() + " [" + combinedGold + "] ");
        this.gold = getGold() + goldGiven + getExtraGoldGain();
    }

    public void viewStorage() {
        int count = 1;

        for (Item i : playerItems) {
            System.out.println("Item " + count + " ) \n " + i);
            count++;
        }

    }

    @Override
    public String toString() {
        return "Player name: " + playerName + "\n Max HP: " + maxHP
                + "\n Current HP: " + currentHP
                + "\n Damage: " + damage
                + "\n Gold: " + gold
                + "\n Current Level: " + currentLevel
                + "\n Current tile: " + currentTile
                + "\n Amount of potions: " + amountOfPotions
                + "\n Extra gold gain: " + extraGoldGain;
    }

    public void changePlayerName() {
        System.out.println("\nWhat's your name?");
        this.playerName = sc.nextLine();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(int currentTile) {
        this.currentTile = currentTile;
    }

    public ArrayList<Item> getPlayerItems() {
        return playerItems;
    }

    public void setPlayerItems(ArrayList<Item> playerItems) {
        this.playerItems = playerItems;
    }

    public int getAmountOfPotions() {
        return amountOfPotions;
    }

    public void setAmountOfPotions(int amountOfPotions) {
        this.amountOfPotions = amountOfPotions;
    }

    public int getExtraGoldGain() {
        return extraGoldGain;
    }

    public void setExtraGoldGain(int extraGoldGain) {
        this.extraGoldGain = extraGoldGain;
    }
}
