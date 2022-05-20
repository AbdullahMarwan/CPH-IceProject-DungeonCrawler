package studyGroupF.fields;

import studyGroupF.BattleSystem;
import studyGroupF.player.Item;
import studyGroupF.Monster;
import studyGroupF.player.Player;
import studyGroupF.player.States;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MonsterBattle extends Field {
    private Monster monster;
    private BattleSystem battleSystem;
    boolean inCombat = true;
    int minGold = 20;
    int maxGold = 100;

    public MonsterBattle(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) throws IOException {
        monster = new Monster();

        player.setPlayerState(States.COMBAT);

        try {
            monster = monster.createMonster();
        } catch (IOException e) {
            e.printStackTrace();
        }

        battleSystem = new BattleSystem(player, monster);

        System.out.println("You will be fighting against the following monster: \n " + monster);

        while (inCombat) {
            displayHP(player);
            combatOptions(player); //Player chooses options such as attack and heal
            battleSystem.attack(false); //Monster attacks
            checkWinner(player);
        }

        player.setPlayerState(States.IDLE);
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
        System.out.println(monster.getMonsterType() + "'s HP: " + monster.getHP());
    }

    public void combatOptions(Player player) throws IOException {
        System.out.println("\n You are in Combat, the following options are: \n" +
                "1: Attack monster\n" +
                "2: Heal up\n" +
                "3: View player stats\n"
        );

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {

            case "1" -> { //Attack Monster
                System.out.println("Attacking monster: ");
                battleSystem.attack(true);
            }
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

    public boolean isInCombat() {
        return inCombat;
    }

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


