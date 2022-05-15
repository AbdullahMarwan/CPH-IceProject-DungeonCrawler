package studyGroupF;

import studyGroupF.Data.FileIO;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class GameController {
    Player player;
    Level level;
    FileIO fileIO;

    ArrayList<Level> levels;

    public void setUpGame() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        player = new Player();
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
            } else if (input.equals("n")) { //Initialize a new save
            }

        } else {
            System.out.println("No previous Data found, starting a new save: ");

        }

    }

    public void initializePlayer() {

    }

    private void saveData() {

    }
}
