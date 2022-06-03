package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;
import java.util.Scanner;

public class WeaponSmith extends Field {
    boolean weaponSmithInProgress = true;
    int commonAmount = 0;
    int uncommonAmount = 0;
    int rareAmount = 0;
    int epicAmount = 0;
    int legendaryAmount = 0;

    public WeaponSmith(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) {
        while (weaponSmithInProgress) {
            weaponSmithOptions(item, player);
        }
    }

    void countRarities(int id, Player player) {
        commonAmount = 0;
        uncommonAmount = 0;
        rareAmount = 0;
        epicAmount = 0;
        legendaryAmount = 0;

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
        System.out.println(
                "\nAmount of commons: " + commonAmount +
                "\nAmount of uncommons: " + uncommonAmount +
                "\nAmount of rares: " + rareAmount +
                "\nAmount of epics: " + epicAmount +
                "\nAmount of legendaries:" + legendaryAmount
        );

    }

    int returnSpecificRarityAmount(int rarityValue) {
        return switch (rarityValue) {
            case 2 -> commonAmount;
            case 3 -> uncommonAmount;
            case 4 -> rareAmount;
            case 5 -> epicAmount;
            default -> 0;
        };
    }

    void weaponSmithOptions(Item item, Player player) {
        System.out.println("""

                You are at the weaponsmith, the following options are:
                    1: Choose an equipment type to upgrade
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

    void viewEquipmentOptions(Item item, Player player) {
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
            countRarities(inputInt, player);
            upgradeItemsByRarity(player, inputInt);
        } else if (inputInt == 4) {
            weaponSmithOptions(item, player);
        } else {
            System.out.println("\n---Invalid input, try again!---");
            viewEquipmentOptions(item, player);
        }
    }

    int accumulativeItemUpgradePrice(int itemAmount, int rarityValue) { //Calculates cost based on rarity and quantity of items
        return (int) (itemAmount * rarityCost(rarityValue) * Math.pow(0.95, itemAmount - 1));
    }

    public int rarityCost(int rarityValue) {
        return switch (rarityValue) { //2 = upgrade common to uncommon, 3 = uncommon to rare, etc.
            case 2 -> 150;
            case 3 -> 225;
            case 4 -> 375;
            case 5 -> 600;
            default -> 0;
        };
    }

    void upgradeItemsByRarity(Player player, int id) {
        System.out.println(
                "\nWhich rarity would you like to upgrade?\n " +
                        "\t1. - Common -> Uncommon" + " (Cost: " + accumulativeItemUpgradePrice(commonAmount, 2) + ")\n"+
                        "\t2. - Uncommon -> Rare" +  " (Cost: " + accumulativeItemUpgradePrice(uncommonAmount, 3) + ")\n"+
                        "\t3. - Rare -> Epic" + " (Cost: " + accumulativeItemUpgradePrice(rareAmount, 4) + ")\n"+
                        "\t4. - Epic -> Legendary" + " (Cost: " + accumulativeItemUpgradePrice(epicAmount, 5) + ")\n"+
                        "\t5. - View previous options\n");
        Scanner sc = new Scanner(System.in);
        int inputInt = sc.nextInt();
        int desiredItemRarity = inputInt+1;
        if (inputInt >= 1 && inputInt <= 4) {
            if (hasRarity(returnSpecificRarityAmount(desiredItemRarity))) {
                if (player.getGold() >= accumulativeItemUpgradePrice(returnSpecificRarityAmount(desiredItemRarity), desiredItemRarity)) {
                    for (Item item : player.getPlayerItems()) {
                        if (item.getRarityValue() == desiredItemRarity && item.getId() == id) {
                            item.matchNewItemProperties(item, item.getRarityValue() + 1);
                        }
                    }
                    int goldSpent=accumulativeItemUpgradePrice(returnSpecificRarityAmount(desiredItemRarity), desiredItemRarity);
                    int goldRemaining=player.getGold() - goldSpent;
                    player.setGold(goldRemaining);
                    System.out.println("You used "+goldSpent+" gold! (Remaining gold: "+goldRemaining+")");
                } else {
                    System.out.println("Not enough money!");
                    upgradeItemsByRarity(player, id);
                }
            } else {
                System.out.println("You don't have any equipment of that rarity.");
                upgradeItemsByRarity(player, id);
            }
        } else if (inputInt == 5) {
            weaponSmithOptions(item, player);

        } else {
            System.out.println("\n---Invalid input, try again!---");
            upgradeItemsByRarity(player, id);
        }
    }

    boolean hasRarity(int rarityAmount)
    {
        return rarityAmount>0;
    }
}
