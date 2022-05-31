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
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class GameController {
    Player player;
    Level level;
    Monster monster;
    Item item;
    BattleSystem battleSystem;

    public boolean gameInProgress = true;

    public void setUpGame() {
        Scanner sc = new Scanner(System.in);
        level = new Level();
        item = new Item();
        battleSystem = new BattleSystem(player, monster);

        if (DataManager.getInstance().isGameDataAvailable()) {
            System.out.println("This is the current PlayerData in the Database: \n");
            initializeFullGame(SaveState.OLD_SAVE);
            System.out.println(getPlayer());
            level.printFieldArray(getPlayer().getCurrentTile());

            System.out.println("\nWould you like to load previous data? ");
            System.out.println("To load it press 'L' or start a new 'N' save \n ");
            String input = sc.nextLine().toLowerCase(Locale.ROOT);

            if (input.equals("l")) { //Load previous PlayerData
                System.out.println("Loading previous save");
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
        System.out.println("----------------------------------------------------------------------------------------------------");
        level.printFieldArray(getPlayer().getCurrentTile());

        Field currentField = level.getCurrentField(getPlayer().getCurrentTile());
        System.out.println("Current field: " + currentField);

        idleOptions();
    }

    public void initializeFullGame(SaveState saveState) {
        DataManager.getInstance().initializeGame(saveState);
        initializeLevel(saveState);
        if (saveState == SaveState.NEW_SAVE) {
            getPlayer().changePlayerName();
        }
    }

    private void saveFullGame() {
        DataManager.getInstance().setGameData(DataManager.getInstance().getGameData());
        DataManager.getInstance().getGameData().setFieldsID(level.returnFieldsIDs());
        DataManager.getInstance().saveGameData();
    }

    public void initializeLevel(SaveState saveState) {

        switch (saveState) {

            case OLD_SAVE -> {
                level.loadPreviousFieldsToLevel(getFields());
            }
            case NEW_SAVE -> {
                level.addRandomsFieldsToLevel();
            }
        }

        level.setLevelNr(getPlayer().getCurrentLevel());
    }

    public void goToNextLevel() {
        int newLevelNr = level.getLevelNr() + 1;

        initializeLevel(SaveState.NEW_SAVE);
        level.setLevelNr(newLevelNr);
        getPlayer().setCurrentLevel(newLevelNr);

        getPlayer().setCurrentTile(0);
    }

    public void idleOptions() throws IOException {
        System.out.println("\nYou are idle, the following options are: \n" +
                "1: Move to next field\n" +
                "2: View Item Storage\n" +
                "3: View player stats\n" +
                "4: View Map Icons\n" +
                "5: Save game\n" +
                "6: Exit game\n"
        );

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {

            case "1" -> { //Go to next field
                if (level.fields.size() < getPlayer().getCurrentTile() + 2) {
                    goToNextLevel();
                } else {
                    System.out.println("----------------------------------------------------------------------------------------------------");

                    System.out.println("Moving to next field: ");
                    getPlayer().setCurrentTile(getPlayer().getCurrentTile() + 1);

                    //System.out.println("New tile: " + getPlayer().getCurrentTile()); //+1 for Visual clarity
                    Field newField = level.getCurrentField(getPlayer().getCurrentTile());
                    System.out.println("You have landed on " + newField);

                    level.doFieldFunction(item, getPlayer(), getPlayer().getCurrentTile());
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
            case "6" -> { //Exit Game
                System.out.println("Would you like to save before quitting? Y/N");
                String input = scan.nextLine().toLowerCase(Locale.ROOT);

                if (input.equals("y")) { //Saves then Quits
                    System.out.println("Saving and quitting");
                    saveFullGame();
                    gameInProgress = false;
                } else if (input.equals("n")) { //Quits without saving
                    System.out.println("Quitting without saving");
                    gameInProgress = false;
                } else {
                    System.out.println("\n---Invalid input, try again---");
                    idleOptions();
                }
            }

            default -> {
                System.out.println("\n---Invalid input, try again---");
                idleOptions();
            }
        }
    }

    public Player getPlayer() {
        return DataManager.getInstance().getGameData().getPlayer();
    }

    public void setPlayer(Player player) {
        DataManager.getInstance().setPlayerData(player);
    }

    public ArrayList<Integer> getFields() {
        return DataManager.getInstance().getGameData().getFieldsID();
    }

    public void setFields(ArrayList<Integer> fieldsData) {
        DataManager.getInstance().setFieldData(fieldsData);
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }
}
