package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

import java.util.Random;
import java.util.Scanner;

public class CampFire extends Field {
    boolean brokenCampFire = false;
    String campFireState = "fully lit";
    String campFireIntroduction = "You have found a fully lit Campfire.";
    int campFireSalvageGold = 100;
    int eatMaxHP = 10;
    double restHealAmount = 1;
    int min = 1;
    int max = 5;

    public CampFire(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) {
        randomizeCampFireState();
        campFireOptions(player);
    }

    public void randomizeCampFireState() {
        Random r = new Random();
        int randomChance = r.nextInt((max - min) + 1) + min;

        if (randomChance != 1) {
            brokenCampFire = true;
            campFireState = "barely lit";
            campFireIntroduction = "You have found a half broken Campfire.";
            campFireSalvageGold = campFireSalvageGold / 2;
            eatMaxHP = eatMaxHP / 2;
            restHealAmount = 0.5;
        }

    }

    public void restOption(Player player) {
        int healAmount = (int) (player.getMaxHP() * restHealAmount);

        player.setCurrentHP(player.getCurrentHP() + healAmount);
        System.out.println("You have healed for: " + healAmount);
    }

    public void salvageOption(Player player) {
        System.out.println("\nYou salvage the " + campFireState + "Campfire for a total of " + campFireSalvageGold + "Gold");
        player.setGold(player.getGold() + campFireSalvageGold);
    }

    public void eatOption(Player player) {
        player.setMaxHP(player.getMaxHP() + eatMaxHP);
        System.out.println("You have gained: " + eatMaxHP + " maxHP for a total of " + player.getMaxHP() + "HP");
    }

    public void campFireOptions(Player player) {
        System.out.println("\n" + campFireIntroduction);
        System.out.println("\n You are in Campfire, the following options are: \n" +
                "1: Rest (Heal" + restHealAmount + ")\n" +
                "2: Eat (Gain " + eatMaxHP + " MaxHP)\n" +
                "3: Salvage (Gain " + campFireSalvageGold + " Gold)\n" +
                "4: View player stats\n"
        );

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {

            case "1" -> { //Rest
                restOption(player);
            }
            case "2" -> { //Eat
                eatOption(player);
            }

            case "3" -> { //Salvage
                salvageOption(player);
            }

            case "4" -> { //View stats
                System.out.println("Your stats: ");
                System.out.println(player);
                campFireOptions(player);
            }

            default -> {
                System.out.println("\n---Invalid input, try again---");
                campFireOptions(player);
            }
        }
    }
}
