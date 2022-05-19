package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class LootChest extends Field {
    public LootChest(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }
    int minGold = 20;
    int maxGold = 100;

    @Override
    void doFunction(Item item, Player player) {
        System.out.println("You have found a LootChest. Would you like to open it? Y/N");

        Scanner sc = new Scanner(System.in);

        if (sc.nextLine().equalsIgnoreCase("y")) { //Open Chest
            Random r = new Random();
            int randomGoldAmount = r.nextInt((maxGold - minGold) + 1) + minGold;

            System.out.println("You have received " + randomGoldAmount + " gold!");
            player.addGoldToPlayer(randomGoldAmount);

            Item randomItem = item.createItem();

            System.out.println("You have received the following item: \n " + randomItem);
            player.addLootToPlayer(randomItem);
        } else {
            System.out.println("You walk away from the chest...");
        }
    }

    @Override
    void introduction() {

    }

    @Override
    int returnGold() {
        return 0;
    }

    @Override
    Item returnLoot() {
        return null;
    }

    public int getMinGold() {
        return minGold;
    }

    public void setMinGold(int minGold) {
        this.minGold = minGold;
    }

    public int getMaxGold() {
        return maxGold;
    }

    public void setMaxGold(int maxGold) {
        this.maxGold = maxGold;
    }
}
