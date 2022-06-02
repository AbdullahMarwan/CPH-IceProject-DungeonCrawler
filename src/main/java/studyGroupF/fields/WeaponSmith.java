package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class WeaponSmith extends Field {
    boolean weaponSmithInProgress = true;

    public WeaponSmith(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) {
        while (weaponSmithInProgress) {
            weaponSmithOptions(item, player);
        }
    }

    void printItemsByType(int id, Player player) {
        int commonAmount = 0;
        int uncommonAmount = 0;
        int rareAmount = 0;
        int epicAmount = 0;
        int legendaryAmount = 0;
        for (Item item : player.getPlayerItems()) {
            if (item.getId() == id) {
                switch (item.getRarityValue()) {
                    case 2 -> commonAmount++;
                    case 3 -> uncommonAmount++;
                    case 4 -> rareAmount++;
                    case 5 -> epicAmount++;
                    case 6 -> legendaryAmount++;
                }
            }
        }
        System.out.println("""
                Amount of commons: """ + commonAmount + """
                Amount of uncommons: """ + uncommonAmount + """
                Amount of rares: """ + rareAmount + """
                Amount of epics: """ + epicAmount + """
                Amount of legendaries: """ + legendaryAmount
        );

    }

    void weaponSmithOptions(Item item, Player player) {
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

            case "1" -> { //View your current equipment
                System.out.println("-----Viewing your current equipment-----");
                viewEquipmentOptions(item, player);
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
                weaponSmithInProgress = false;
            }

            default -> {
                System.out.println("\n---Invalid input, try again---");
                weaponSmithOptions(item, player);
            }
        }
    }

    void viewEquipmentOptions(Item item, Player player)
    {
        System.out.println("""
                    Which item type would you like to upgrade?
                    1. - Extra Gold
                    2. - Extra Damage
                    3. - Extra HP
                    
                    4. - View previous options
                                        
                    """);
        Scanner sc = new Scanner(System.in);
        int inputInt = sc.nextInt();
        if (inputInt >= 1 && inputInt <= 3) {
            printItemsByType(inputInt, player);
        }
        else if(inputInt==4)
        {
            weaponSmithOptions(item, player);
        }
        else {
            System.out.println("\n---Invalid input, try again!---");
            viewEquipmentOptions(item, player);
        }
    }

    void upgradeItemsByRarity(Player player, int desiredItemRarity) {
        for (Item item : player.getPlayerItems()) {
            if (item.getRarityValue() == desiredItemRarity) {
                item.matchNewItemProperties(item, item.getRarityValue() + 1);
            }
        }
    }
}
