package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

import java.io.IOException;
import java.util.Scanner;

public class ItemShop extends Field{

    public ItemShop(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) throws IOException {
        shopOptions(item, player);
    }

    public void shopOptions(Item item, Player player) throws IOException {
        System.out.println("\nYou are in the Item Shop, the following options are: \n" +
                "1: View items in shop\n" +
                "2: View Item Storage\n" +
                "3: View player stats\n" +
                "4: V\n" +
                "5: Save game\n"
        );

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {

            case "1" -> { //

            }
            case "2" -> { //View Inventory
                System.out.println("Here is your item storage: ");
                player.viewStorage();
            }
            case "3" -> { //View stats
                System.out.println("Your stats: ");
                System.out.println(player);
            }
            case "4" -> { //Save Game
            }
            case "5" -> { //
            }

            default -> {
                System.out.println("\n---Invalid input, try again---");
                shopOptions(item, player);
            }
        }
    }

}
