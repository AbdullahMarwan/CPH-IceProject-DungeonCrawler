package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemShop extends Field {
    ArrayList<Item> shopItems = new ArrayList<>();
    private int amountOfShopItems = 3;
    boolean shopInProgress = true;

    public ItemShop(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) throws IOException {
        createShopItems(item);
        while (shopInProgress) {
            shopOptions(item, player);
        }
    }

    public void createShopItems(Item item) {
        for (int i = 0; i < amountOfShopItems; i++) {
            item = item.createItem();

            item.setGoldCost(giveItemCost(item));

            shopItems.add(item);
        }
    }

    public int giveItemCost(Item item) {

        return switch (item.getRarityName()) {
            case "Common" -> 50;
            case "Uncommon" -> 75;
            case "Rare" -> 120;
            case "Epic" -> 200;
            case "Legendary" -> 325;
            default -> 0;
        };

    }

    public void buyItem(Item item, Player player) {
        Scanner sc = new Scanner(System.in);

        if (player.getGold() >= item.getGoldCost()) {
            System.out.println("Would you like to buy " + item.getItemName() + " for " + item.getGoldCost() + " ? Y/N");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                System.out.println("You have bought " + item.getItemName());
                player.addLootToPlayer(item);
                shopItems.remove(item);
            }
        } else {
            System.out.println("Not enough gold");
        }

        System.out.println("Returning to Shop Options");
    }


    public void viewShopItems(Item item, Player player) throws IOException {
        int count = 1;

        System.out.println("You have " + player.getGold() + " gold.");
        System.out.println("These are the items currently available in this shop: ");
        System.out.println("Select the item you want by Typing it's number: ");

        for (Item i : shopItems) {
            System.out.println("Item " + count + " ) \n Cost: " + i.getGoldCost() +
                    " Gold.\n  Item Name: " + i.getItemName() + "\n  Item type: " + i.getItemType()
            );
            count++;
        }

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {
            case "1" -> {
                if (shopItems.size() >= 1) {
                    buyItem(shopItems.get(0), player);
                }
            }
            case "2" -> {
                if (shopItems.size() >= 2) {
                    buyItem(shopItems.get(1), player);
                }
            }
            case "3" -> {
                if (shopItems.size() >= 3) {
                    buyItem(shopItems.get(2), player);
                }
            }
            default -> shopOptions(item, player);
        }
    }

    public void shopOptions(Item item, Player player) throws IOException {
        System.out.println("\nYou are in the Item Shop, the following options are: \n" +
                "1: View items in shop\n" +
                "2: View Player item Storage\n" +
                "3: View player stats\n" +
                "4: Exit the shop\n"
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
