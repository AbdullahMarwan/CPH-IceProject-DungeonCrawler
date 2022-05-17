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

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Monster> monsters = new ArrayList<>();
    ArrayList<Level> levels = new ArrayList<>();

    public void setUpGame() throws IOException {
        Scanner sc = new Scanner(System.in);
        level = new Level();
        fileIO = new FileIO();
        storage = new Storage();
        item = new Item();

        if (fileIO.isPlayerDataAvailable()) {
            System.out.println("This is the current PlayerData in the Database: ");
            initializePreviousPlayerData();
            System.out.println(players.get(0));

            System.out.println("Would you like to load previous data? ");
            System.out.println("To load it press 'L' or start a new 'N' save \n ");
            String input = sc.nextLine().toLowerCase(Locale.ROOT);

            //TODO If previous PlayerData is chosen, select and load the level of that, if not start from level 1
            if (input.equals("l")) { //Load previous PlayerData
                clearPlayerArrayList();
                initializePreviousPlayerData();
            } else if (input.equals("n")) { //Initialize a new save
                System.out.println("Starting a new save: ");
                clearPlayerArrayList();
                Player player = new Player();
                players.add(player);
            }

        } else {
            System.out.println("No previous Data found, starting a new save: ");
            clearPlayerArrayList();
            Player player = new Player();
            players.add(player);
        }

        //Testing adding item to storage
        //item = new Item(item.createItem());

        item = item.createItem();
        players.get(0).addLootToPlayer(item);

        Item item2 = new Item();
        item2 = item.createItem();

        players.get(0).addLootToPlayer(item2);

        players.get(0).viewStorage();

        //System.out.println(item);


        initializeAMonster();

        //saveData(); To Save new Data
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
        //System.out.println("The player: " + player);
        players.add(player);

        //players.get(0).setPlayerItems(storage.addItemToPlayerItemList(player.getPlayerItems(), item));
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

    private void clearPlayerArrayList(){
        players = new ArrayList<>();
    }
    private void clearMonsterArrayList(){
        monsters = new ArrayList<>();
    }
}
