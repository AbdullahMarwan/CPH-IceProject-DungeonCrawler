package studyGroupF;

import studyGroupF.data.FileIO;
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
    ArrayList<Monster> monsters = new ArrayList<>();
    ArrayList<Level> levels = new ArrayList<>();

    public void setUpGame() throws IOException {
        Scanner sc = new Scanner(System.in);
        level = new Level();
        fileIO = new FileIO();
        storage = new Storage();
        item = new Item();
        battleSystem = new BattleSystem(player, monster);

        if (fileIO.isPlayerDataAvailable()) {
            System.out.println("This is the current PlayerData in the Database: ");
            initializePreviousPlayerData();
            System.out.println(players.get(0));

            System.out.println("Would you like to load previous data? ");
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

        //players.get(0).viewStorage();
    }

    public void initializeOldLevel() {
        level.loadPreviousFieldsToLevel();
        levels.add(level);
        levels.get(0).setLevelNr(players.get(0).getCurrentLevel());

        //level.printFieldArray();
    }

    public void initializeNewSave() {
        clearPlayerArrayList();
        Player player = new Player();
        players.add(player);

        level.addRandomsFieldsToLevel();
        levels.add(level);
        //level.printFieldArray();
    }

    public void playGame() throws IOException {

        optionsFromPhase();

        //System.out.println("player" + players.get(0));

    }

    public void optionsFromPhase() {

        switch (level.getCurrentPhase()) {
            case "Idle" -> {
                idleOptions();
            }
            case "Combat" -> {
                combatOptions();
            }
            case "Chest" -> {

            }
            case "Shop" -> {

            }
        }

    }

    public void combatOptions() {
        System.out.println("\n You are in Combat, the following options are: \n" +
                "1: Attack monster\n" +
                "2: View Item Storage\n" +
                "3: View player stats\n"
        );

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {

            case "1" -> { //Go to next field
                System.out.println("Attacking monster: ");
                battleSystem.attack(true);
            }
            case "2" -> { //View Inventory
                System.out.println("Here is your item storage: ");
                players.get(0).viewStorage();
            }
            case "3" -> { //View stats
                System.out.println("Here is your item storage: ");
                System.out.println(players.get(0));
            }

            default -> {
                System.out.println("\n---Invalid input, try again---");
                idleOptions();
            }
        }
    }

    public void idleOptions() {
        System.out.println("\n You are idle, the following options are: \n" +
                "1: Move to next field\n" +
                "2: View Item Storage\n" +
                "3: View player stats\n"
        );

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch (choice) {

            case "1" -> { //Go to next field
                System.out.println("Moving to next field: ");
                players.get(0).setCurrentTile(player.getCurrentTile() + 1);
            }
            case "2" -> { //View Inventory
                System.out.println("Here is your item storage: ");
                players.get(0).viewStorage();
            }
            case "3" -> { //View stats
                System.out.println("Here is your item storage: ");
                System.out.println(players.get(0));
            }

            default -> {
                System.out.println("\n---Invalid input, try again---");
                idleOptions();
            }
        }
    }

    public void initializePreviousPlayerData() throws FileNotFoundException {
        int maxHP = 0;
        int currentHP = 0;
        int damage = 0;
        int gold = 0;
        int currentLevel = 0;
        int currentTile = 0;
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
        }

        Player player = new Player(playerName, maxHP, currentHP, damage, gold, currentLevel, currentTile);
        players.add(player);

        //System.out.println(players.get(0));
    }

    private void saveData() throws IOException {
        fileIO.savePlayerData(addPlayersToData());
        //TODO call saveLevelData
        //TODO call saveItemStorageData
    }

    private void initializeAMonster() throws IOException {
        clearMonsterArrayList();
        monster = level.createMonster();
        monsters.add(monster);

        //System.out.println("The monster created: " + monsters.get(0));
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
            data.add(t.getCurrentTile() + "");
        }

        System.out.println("The Data is: " + data);

        return data;
    }

    private void clearPlayerArrayList() {
        players = new ArrayList<>();
    }

    private void clearMonsterArrayList() {
        monsters = new ArrayList<>();
    }

    private void testAndLeftOvers() {
        //Testing adding item to storage
        //item = new Item(item.createItem());

        item = item.createItem();
        players.get(0).addLootToPlayer(item);

        Item item2 = new Item();
        item2 = item.createItem();

        players.get(0).addLootToPlayer(item2);

        players.get(0).viewStorage();

        //System.out.println(item);


        level.printFieldArray();

        //initializeAMonster();

        //saveData(); To Save new Data


    }

}
