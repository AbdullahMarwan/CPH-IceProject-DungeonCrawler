package studyGroupF;

import studyGroupF.data.FileIO;
import studyGroupF.player.Player;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class GameController {
    Player player;
    Level level;
    FileIO fileIO;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Level> levels = new ArrayList<>();

    public void setUpGame() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        level = new Level();
        fileIO = new FileIO();

        if (fileIO.isPlayerDataAvailable()) {
            System.out.println("This is the current PlayerData in the Database: ");
            //TODO Print all of the previous player's stats

            System.out.println("Would you like to load previous data? ");
            System.out.println("To load it press 'L' or start a new 'N' save \n ");
            String input = sc.nextLine().toLowerCase(Locale.ROOT);

            //TODO If previous PlayerData is chosen, select and load the level of that, if not start from level 1
            if (input.equals("l")) { //Load previous PlayerData
                initializePreviousPlayerData();
            } else if (input.equals("n")) { //Initialize a new save
            }

        } else {
            System.out.println("No previous Data found, starting a new save: ");
            Player player = new Player();
            players.add(player);
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
            System.out.println(s);
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
        System.out.println("The player: " + player);
        players.add(player);
    }

    private void saveData() {

    }
}
