package studyGroupF;

import studyGroupF.data.FileIO;
import studyGroupF.fields.Field;
import studyGroupF.player.Item;
import studyGroupF.player.Player;
import studyGroupF.player.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class GameController {
    Player player;
    Level level;
    FileIO fileIO;
    Monster monster;
    Storage storage;
    Item item;
    BattleSystem battleSystem;

    public ArrayList<Player> players = new ArrayList<>();
    public ArrayList<Level> levels = new ArrayList<>();

    public void setUpGame() throws IOException {
        Scanner sc = new Scanner(System.in);
        level = new Level();
        fileIO = new FileIO();
        storage = new Storage();
        item = new Item();
        battleSystem = new BattleSystem(player, monster);

        if (fileIO.isPlayerDataAvailable()) {
            System.out.println("This is the current PlayerData in the Database: \n");
            initializePreviousPlayerData();
            System.out.println(players.get(0));

            System.out.println("\nWould you like to load previous data? ");
            System.out.println("To load it press 'L' or start a new 'N' save \n ");
            String input = sc.nextLine().toLowerCase(Locale.ROOT);

            if (input.equals("l")) { //Load previous PlayerData
                clearPlayerArrayList();
                initializeOldSave();

            } else if (input.equals("n")) { //Initialize a new save
                System.out.println("Starting a new save: ");
                initializeNewSave();
            } else {
                System.out.println("\n---Invalid input, try again---");
                setUpGame();
            }

        } else { //No Data Found, start new save
            System.out.println("No previous Data found, starting a new save: ");
            initializeNewSave();
        }

    }

    public void playGame() throws IOException {
        levels.get(0).printFieldArray(players.get(0).getCurrentTile());

        //System.out.println("Current tile: " + players.get(0).getCurrentTile() + 1); //+1 for Visual clarity
        Field currentField = levels.get(0).getCurrentField(players.get(0).getCurrentTile());
        System.out.println("Current field: " + currentField);

        idleOptions();
    }

    public void initializeOldSave() throws IOException {
        initializePreviousPlayerData();
        initializeOldLevel();
        initializeOldItemStorage();
    }

    public void initializeOldItemStorage() throws IOException {
        String itemName = "";
        String itemType = "";
        String rarityName = "";
        int rarityValue = 0;
        int id = 0;

        ArrayList<String> data;
        data = fileIO.readItemData();

        for (String s : data) {
            //System.out.println(s);
            String[] values = s.split(", ");

            itemName = values[0];
            itemType = values[1];
            rarityName = values[2];
            rarityValue = Integer.parseInt(values[3]);
            id = Integer.parseInt(values[4]);

            Item item = new Item(itemName, itemType, rarityName, rarityValue, id);
            players.get(0).addLootToPlayer(item);
        }
    }

    public void initializeOldLevel() {
        level.loadPreviousFieldsToLevel();
        levels.add(level);
        levels.get(0).setLevelNr(players.get(0).getCurrentLevel());
        System.out.println("[LEVEL "+levels.get(0).getLevelNr()+"]");
    }

    public void initializeNewSave() {
        clearPlayerArrayList();
        Player player = new Player();
        players.add(player);

        initializeNewLevel();
    }

    public void initializeNewLevel() {
        clearLevelArrayList();
        level.addRandomsFieldsToLevel();
        levels.add(level);
        levels.get(0).setLevelNr(levels.get(0).getLevelNr() + 1);
        System.out.println("[LEVEL "+levels.get(0).getLevelNr()+"]");
    }

    public void goToNextLevel() {
        int newLevelNr = levels.get(0).getLevelNr() + 1;

        initializeNewLevel();
        levels.get(0).setLevelNr(newLevelNr);
        players.get(0).setCurrentLevel(newLevelNr);

        players.get(0).setCurrentTile(0);
    }

    private void clearLevelArrayList() {
        levels = new ArrayList<>();
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
                if (levels.get(0).fields.length < players.get(0).getCurrentTile() + 2) {
                    goToNextLevel();
                } else {
                    System.out.println("Moving to next field: ");
                    players.get(0).setCurrentTile(players.get(0).getCurrentTile() + 1);

                    //System.out.println("New tile: " + players.get(0).getCurrentTile()); //+1 for Visual clarity
                    Field newField = levels.get(0).getCurrentField(players.get(0).getCurrentTile());
                    System.out.println("You have landed on " + newField);

                    levels.get(0).doFieldFunction(item, players.get(0), players.get(0).getCurrentTile());
                }
            }
            case "2" -> { //View Inventory
                System.out.println("Here is your item storage: ");
                players.get(0).viewStorage();
            }
            case "3" -> { //View stats
                System.out.println("Your stats: ");
                System.out.println(players.get(0));
            }
            case "4" -> { //Save Game
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
                saveData();
            }

            default -> {
                System.out.println("\n---Invalid input, try again---");
                idleOptions();
            }
        }
    }

    public void initializePreviousPlayerData() {
        int maxHP = 0;
        int currentHP = 0;
        int damage = 0;
        int gold = 0;
        int currentLevel = 0;
        int currentTile = 0;
        int amountOfPotions = 0;
        String playerName = "";

        ArrayList<String> data;
        data = fileIO.readPlayerData();

        for (String s : data) {
            //System.out.println(s);
            String[] values = s.split(", ");

            playerName = values[0];
            maxHP = Integer.parseInt(values[1]);
            currentHP = Integer.parseInt(values[2]);
            damage = Integer.parseInt(values[3]);
            gold = Integer.parseInt(values[4]);
            currentLevel = Integer.parseInt(values[5]);
            currentTile = Integer.parseInt(values[6]);
            amountOfPotions = Integer.parseInt(values[7]);
        }

        Player player = new Player(playerName, maxHP, currentHP, damage, gold, currentLevel, currentTile, amountOfPotions);
        players.add(player);
    }

    private void saveData() throws IOException {
        fileIO.savePlayerData(addPlayersToData());
        fileIO.saveLevelData(addLevelToData());
        fileIO.saveItemStorageData(addItemStorageToData());
    }

    public ArrayList<String> addItemStorageToData() {
        ArrayList<String> data = new ArrayList<>();

        for (Item t : players.get(0).getPlayerItems()) {
            data.add(t.getItemName() + ", ");
            data.add(t.getItemType() + ", ");
            data.add(t.getRarityName() + ", ");
            data.add(t.getRarityValue() + ", ");
            data.add(t.getId() + "");
        }

        System.out.println("The Data is: " + data);

        return data;
    }


    public ArrayList<String> addLevelToData() {
        ArrayList<String> data = new ArrayList<>();

        for (Level l : levels) {

            for (int i = 0; i < level.fields.length; i++) {
                if (i == level.fields.length - 1) {
                    data.add(l.fieldList.currentField(i).getFieldID() + "");
                } else {
                    data.add(l.fieldList.currentField(i).getFieldID() + ", ");
                }
            }

        }

        return data;
    }

    public ArrayList<String> addPlayersToData() {
        ArrayList<String> data = new ArrayList<>();

        for (Player t : players) {
            data.add(t.getPlayerName() + ", ");
            data.add(t.getMaxHP() + ", ");
            data.add(t.getCurrentHP() + ", ");
            data.add(t.getDamage() + ", ");
            data.add(t.getGold() + ", ");
            data.add(t.getCurrentLevel() + ", ");
            data.add(t.getCurrentTile() + ", ");
            data.add(t.getAmountOfPotions() + "");
        }

        return data;
    }

    private void clearPlayerArrayList() {
        players = new ArrayList<>();
    }

}
