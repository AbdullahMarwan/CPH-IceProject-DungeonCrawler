package studyGroupF.fields;

import studyGroupF.BattleSystem;
import studyGroupF.player.Item;
import studyGroupF.Monster;
import studyGroupF.player.Player;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MonsterBattle extends Field {
    private Monster monster;
    private BattleSystem battleSystem;
    boolean inCombat = true;
    int minGold = 20;
    int maxGold = 100;
    int battleTurn = 1;

    public MonsterBattle(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    public void doFunction(Item item, Player player) throws IOException {
        monster = new Monster();

        monster = monster.createMonster(player.getCurrentLevel());

        battleSystem = new BattleSystem(player, monster);

        System.out.println("\nYou will be fighting against the following monster: \n " + monster + "\n");

        while (inCombat) {
            checkWinner(player);
            System.out.println("-----Turn " + battleTurn + "-----");
            displayHP(player);

            combatOptions(player); //Player chooses options such as attack and heal

            battleTurn++;
        }

    }

    public void playerFinishedTurn(Player player) {
        checkWinner(player);
        if (inCombat) {
            battleSystem.attack(false); //Monster attacks
        }
    }

    public void checkWinner(Player player) {
        if (player.getCurrentHP() <= 0) {
            System.out.println("\nYou died an honorable death...");
            inCombat = false;
        } else if (monster.getHP() <= 0) {
            System.out.println("\nYou have defeated the monster");
            inCombat = false;

            Random r = new Random();
            int randomGoldAmount = r.nextInt((maxGold - minGold) + 1) + minGold;

            System.out.println("You have received " + randomGoldAmount + " gold!");
            player.addGoldToPlayer(randomGoldAmount);
        }

    }

    public void displayHP(Player player) {
        System.out.println(player.getPlayerName() + "'s HP: " + player.getCurrentHP() + "/" + player.getMaxHP());
        System.out.println(monster.getMonsterType() + "'s HP: " + monster.getHP() + "/" + monster.getMaxHP());
    }

    public void combatOptions(Player player) {
        System.out.println("\n You are in Combat, the following options are: \n" +
                "1: Attack monster\n" +
                "2: Heal up (" + player.getAmountOfPotions() + " potions)\n" +
                "3: View player stats\n"
        );

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {

            case "1" -> { //Attack Monster
                System.out.println("Attacking monster: ");
                battleSystem.attack(true); //Player attacks
                playerFinishedTurn(player);
            }
            case "2" -> { //Heal up
                if (player.getAmountOfPotions() > 0) {
                    System.out.println("You have " + player.getAmountOfPotions() + " potions. Would you like to use one? Y/N");

                    Scanner potion = new Scanner(System.in);
                    String potionChoice = potion.nextLine();

                    if (potionChoice.equalsIgnoreCase("y")) {
                        battleSystem.heal();
                        player.setAmountOfPotions(player.getAmountOfPotions() - 1);
                        playerFinishedTurn(player);
                    } else if (potionChoice.equalsIgnoreCase("n")) {
                        System.out.println("You decided to save your potion.");
                    } else {
                        System.out.println("\n---Invalid input, try again---");
                        combatOptions(player);
                    }
                } else {
                    System.out.println("You have 0 Potions");
                }
            }

            case "3" -> { //View stats
                System.out.println("Your stats: ");
                System.out.println(player);
                combatOptions(player);
            }

            default -> {
                System.out.println("\n---Invalid input, try again---");
                combatOptions(player);
            }
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


