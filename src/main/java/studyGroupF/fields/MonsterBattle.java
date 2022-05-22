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
    void doFunction(Item item, Player player) throws IOException {
        monster = new Monster();

        monster = monster.createMonster(player.getCurrentLevel());

        battleSystem = new BattleSystem(player, monster);

        System.out.println("\nYou will be fighting against the following monster: \n " + monster + "\n");

        while (inCombat) {
            checkWinner(player);
            if(battleSystem.areAlive())
            {
                System.out.println("-----Turn " + battleTurn + "-----");
                displayHP(player);
                combatOptions(player); //Player chooses options such as attack and heal
                battleSystem.attack(); //Monster attacks
                battleTurn++;
            }
        }

    }

    public void checkWinner(Player player) {
        if (player.getCurrentHP() <= 0) {
            System.out.println("You died an honorable death...");
            inCombat = false;
        } else if (monster.getHP() <= 0) {
            System.out.println("You have defeated the monster");
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
        System.out.println("""

                You are in Combat, the following options are:\s
                1: Attack monster
                2: Heal up
                3: View player stats
                """
        );

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {

            case "1" -> //Attack Monster
                    System.out.println("Attacking monster: ");
            case "2" -> { //Heal up
                if (player.getAmountOfPotions() > 0) {
                    System.out.println("You have " + player.getAmountOfPotions() + ". Would you like to use one? Y/N");

                    Scanner potion = new Scanner(System.in);

                    if (potion.nextLine().equalsIgnoreCase("y")) {
                        battleSystem.heal();
                        player.setAmountOfPotions(player.getAmountOfPotions() - 1);
                    } else if (potion.nextLine().equalsIgnoreCase("y")) {
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
            }

            default -> {
                System.out.println("\n---Invalid input, try again---");
                combatOptions(player);
            }
        }
    }

    public boolean isInCombat() {return inCombat;}

    public void setInCombat(boolean inCombat) {
        this.inCombat = inCombat;
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


