package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemShop extends Field {
    ArrayList<Item> shopItems = new ArrayList<>();
    private int amountOfShopItems = 3;

    public ItemShop(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) throws IOException {
        createShopItems();
        shopOptions(item, player);
    }

    public void createShopItems() {
        for (int i = 0; i < amountOfShopItems; i++) {
            item = item.createItem();

            shopItems.add(item);
        }
    }


    public void viewShopItems(Item item, Player player) {

    }

    public void shopOptions(Item item, Player player) throws IOException {
        System.out.println("\nYou are in the Item Shop, the following options are: \n" +
                "1: View items in shop\n" +
                "2: View Player item Storage\n" +
                "3: View player stats\n"
        );

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {

            case "1" -> { //View items in shop
                System.out.println("Viewing items in shop");
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

            default -> {
                System.out.println("\n---Invalid input, try again---");
                shopOptions(item, player);
            }
        }
    }

}
