package studyGroupF;

import studyGroupF.data.DataManager;
import studyGroupF.fields.Field;
import studyGroupF.player.Item;
import studyGroupF.player.Player;
import studyGroupF.shared.BattleSystem;
import studyGroupF.shared.Level;
import studyGroupF.shared.Monster;
import studyGroupF.shared.SaveState;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class GameController {
    Player player;
    Level level;
    Monster monster;
    Item item;
    BattleSystem battleSystem;

    public Player getPlayer() {
        return DataManager.getInstance().getGameData().getPlayer();
    }

    public void setPlayer(Player player) {
        DataManager.getInstance().setPlayerData(player);
    }

    public Level getLevel() {
        return DataManager.getInstance().getGameData().getLevel();
    }

    public void setLevel(Level level) {
        DataManager.getInstance().setLevelData(level);
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void setUpGame() throws IOException {
        Scanner sc = new Scanner(System.in);
        level = new Level();
        item = new Item();
        battleSystem = new BattleSystem(player, monster);

        if (DataManager.getInstance().isGameDataAvailable()) {
            System.out.println("This is the current PlayerData in the Database: \n");
            //clearPlayerArrayList();
            //players.add(DataManager.getInstance().initializePreviousPlayerData());
            System.out.println(getPlayer());

            System.out.println("\nWould you like to load previous data? ");
            System.out.println("To load it press 'L' or start a new 'N' save \n ");
            String input = sc.nextLine().toLowerCase(Locale.ROOT);

            if (input.equals("l")) { //Load previous PlayerData
                //clearPlayerArrayList();
                initializeFullGame(SaveState.OLD_SAVE);

            } else if (input.equals("n")) { //Initialize a new save
                System.out.println("Starting a new save: ");
                initializeFullGame(SaveState.NEW_SAVE);
            } else {
                System.out.println("\n---Invalid input, try again---");
                setUpGame();
            }

        } else { //No Data Found, start new save
            System.out.println("No previous Data found, starting a new save: ");
            initializeFullGame(SaveState.NEW_SAVE);
        }

    }

    public void playGame() throws IOException {
        getLevel().printFieldArray(getPlayer().getCurrentTile());

        Field currentField = getLevel().getCurrentField(getPlayer().getCurrentTile());
        System.out.println("Current field: " + currentField);

        idleOptions();
    }

    public void initializeFullGame(SaveState saveState) {
        DataManager.getInstance().initializeGame(saveState);
    }

    private void saveFullGame() throws IOException {
        DataManager.getInstance().setGameData(DataManager.getInstance().getInstance().getGameData());
        DataManager.getInstance().saveGameData();
    }

    public void initializeLevel(SaveState saveState) {
        if (saveState == SaveState.NEW_SAVE) {
            level.addRandomsFieldsToLevel();
        }

        setLevel(level);
        getLevel().setLevelNr(getLevel().getLevelNr());
        System.out.println("[LEVEL " + getLevel().getLevelNr() + "]");
    }

    public void goToNextLevel() {
        int newLevelNr = getLevel().getLevelNr() + 1;

        initializeLevel(SaveState.NEW_SAVE);
        getLevel().setLevelNr(newLevelNr);
        getPlayer().setCurrentLevel(newLevelNr);

        getPlayer().setCurrentTile(0);
    }

    public void idleOptions() throws IOException {
        System.out.println("\nYou are idle, the following options are: \n" +
                "1: Move to next field\n" +
                "2: View Item Storage\n" +
                "3: View player stats\n" +
                "4: View Map Icons\n" +
                "5: Save game\n"
        );

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {

            case "1" -> { //Go to next field
                if (getLevel().fields.length < getPlayer().getCurrentTile() + 2) {
                    goToNextLevel();
                } else {
                    System.out.println("Moving to next field: ");
                    getPlayer().setCurrentTile(getPlayer().getCurrentTile() + 1);

                    //System.out.println("New tile: " + getPlayer().getCurrentTile()); //+1 for Visual clarity
                    Field newField = getLevel().getCurrentField(getPlayer().getCurrentTile());
                    System.out.println("You have landed on " + newField);

                    getLevel().doFieldFunction(item, getPlayer(), getPlayer().getCurrentTile());
                }
            }
            case "2" -> { //View Inventory
                System.out.println("Here is your item storage: ");
                getPlayer().viewStorage();
            }
            case "3" -> { //View stats
                System.out.println("Your stats: ");
                System.out.println(getPlayer());
            }
            case "4" -> { //Map Icons
                System.out.println("This is the different map Icons: ");
                System.out.println(
                        "[+] Player\n" +
                                "[M] MonsterBattle\n" +
                                "[G] LootChest\n" +
                                "[I] ItemShop\n" +
                                "[W] WeaponSmith\n" +
                                "[0] EmptyField\n" +
                                "[C] CampFire\n"
                );
            }
            case "5" -> { //Save Game
                System.out.println("Saving game.");
                saveFullGame();
            }

            default -> {
                System.out.println("\n---Invalid input, try again---");
                idleOptions();
            }
        }
    }

}
