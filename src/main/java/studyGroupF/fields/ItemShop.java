package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ItemShop extends Field {
    private ArrayList<Item> shopItems = new ArrayList<>();
    private final int amountOfShopItems = 3;
    private Random r = new Random();
    private int amountOfPotionsInShop = r.nextInt((3 - 1) + 1) + 1;
    private int potionGoldCost = 150;
    private boolean shopInProgress = true;

    public ItemShop(Item item, String fieldType, int fieldID) { //FieldList
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) throws IOException { //Called one time when first landing on the function on each floor
        potionGoldCost += player.getCurrentLevel() * 50;
        createShopItems(item);
        while (shopInProgress) {
            shopOptions(item, player);
        }
    }

    public void createShopItems(Item item) { //Creates three random items (3 items only because of amountOfShopItems is set to 3)
        for (int i = 0; i < amountOfShopItems; i++) {
            item = item.createItem();
            item.setGoldCost(giveItemCost(item));
            shopItems.add(item);
        }
    }

    public int giveItemCost(Item item) { //Used in createShopItem based on the rarity of the item created
        return switch (item.getRarityName()) {
            case "Common" -> 50;
            case "Uncommon" -> 75;
            case "Rare" -> 120;
            case "Epic" -> 200;
            case "Legendary" -> 325;
            default -> 0;
        };
    }

    public void buyItem(Item item, Player player) { // buy item method, removes the item bought afterwards
        Scanner sc = new Scanner(System.in);
        if (player.getGold() >= item.getGoldCost()) { //Checks if player has enough gold to buy the item
            System.out.println("Would you like to buy " + item.getItemName() + " for " + item.getGoldCost() + " ? Y/N");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                System.out.println("You have bought " + item.getItemName());
                player.setGold(player.getGold() - item.getGoldCost());
                player.addLootToPlayer(item, player);
                shopItems.remove(item);
            }
        } else {
            System.out.println("Not enough gold");
        }
        System.out.println("---Returning to Shop Options---");
    }

    public void buyPotion(Player player) { // buy potion method, checks the price altered in doFunction. Similar to buyItem
        Scanner sc = new Scanner(System.in);
        if (player.getGold() >= potionGoldCost) {
            System.out.println("Would you like to buy " + "a Healing Potion" + " for " + potionGoldCost + " ? Y/N");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                System.out.println("You have bought a Healing Potion");
                player.setGold(player.getGold() - potionGoldCost);
                player.setAmountOfPotions(player.getAmountOfPotions() + 1);
                amountOfPotionsInShop--;
            }
        } else {
            System.out.println("Not enough gold");
        }
        System.out.println("---Returning to Shop Options---");
    }

    public void viewShopItems(Item item, Player player) throws IOException { // viewShopItems used in first shop options case
        int count = 1;

        System.out.println("You have " + player.getGold() + " gold.\n");
        System.out.println("Select the item you want by Typing it's number. ");
        System.out.println("These are the items currently available in this shop:\n ");

        System.out.println("Item " + count + " ) \n Cost: " + potionGoldCost +
                " Gold.\n  Item Name: " + "Healing Potion" + "\n  Item type: " + "Healing " +
                "\n  Amount of healing potions available in shop: " + amountOfPotionsInShop
                + "\n");
        count++;

        for (Item i : shopItems) { // Prints out all items in the shop
            System.out.println("Item " + count + " ) \n Cost: " + i.getGoldCost() +
                    " Gold.\n  Item Name: " + i.getItemName() + "\n  Item type: " + i.getItemType()
                    + "\n");
            count++;
        }

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        switch (choice) {
            case "1" -> {
                if (amountOfPotionsInShop > 0) {
                    buyPotion(player);
                }
            }
            case "2" -> {
                if (shopItems.size() >= 1) {
                    buyItem(shopItems.get(0), player);
                }
            }
            case "3" -> {
                if (shopItems.size() >= 2) {
                    buyItem(shopItems.get(1), player);
                }
            }
            case "4" -> {
                if (shopItems.size() >= 3) {
                    buyItem(shopItems.get(2), player);
                }
            }
            default -> {
                System.out.println("\n---Invalid input, returning to shop options---");
                shopOptions(item, player);
            }
        }
    }

    public void shopOptions(Item item, Player player) throws IOException {
        System.out.println("""

                You are in the Item Shop, the following options are:\s
                1: View items in shop
                2: View Player item Storage
                3: View player stats
                4: Exit the shop
                """
        );

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {
            case "1" -> { //View items in shop
                System.out.println("-----Viewing items in shop-----");
                viewShopItems(item, player);
            }
            case "2" -> { //View Inventory
                System.out.println("Here is your item storage: ");
                player.viewStorage();
            }
            case "3" -> { //View stats
                System.out.println("Your stats: ");
                System.out.println(player);
            }
            case "4" -> { //Exit Shop
                System.out.println("Exiting shop ");
                shopInProgress = false;
            }
            default -> {
                System.out.println("\n---Invalid input, try again---");
                shopOptions(item, player);
            }
        }
    }
}
