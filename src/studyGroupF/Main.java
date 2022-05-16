package studyGroupF;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static boolean gameInProgress = true;

    public static void main(String[] args) throws IOException {
        GameController gC = new GameController();

        System.out.println("Welcome to the Dungeon Crawler Game!");

        gC.setUpGame();
        while (gameInProgress){
            //System.out.println("----------------------------------------------------------------------------------------------------");

        }

    }
}
