package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

import java.util.Random;
import java.util.Scanner;

public class LootChest extends Field {
    public LootChest(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    private int minGold = 20;
    private int maxGold = 100;

    @Override
    void doFunction(Item item, Player player) {
        System.out.println("Would you like to open it? Y/N");

        Scanner sc = new Scanner(System.in);

        if (sc.nextLine().equalsIgnoreCase("y")) { //Open Chest
            Random r = new Random();
            int randomGoldAmount = r.nextInt((maxGold - minGold) + 1) + minGold;
            player.addGoldToPlayer(randomGoldAmount);

            Item randomItem = item.createItem();
            System.out.println("Item Received: " + randomItem.getItemName());
            player.addLootToPlayer(randomItem, player);

        } else if (sc.nextLine().equalsIgnoreCase("n")) { //Leave the Chest
            System.out.println("You walk away from the chest...");
        } else {
            System.out.println("\n---Invalid input, try again---");
            doFunction(item, player);
        }
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
