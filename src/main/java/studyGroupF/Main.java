package studyGroupF;

import java.io.IOException;

public class Main {
    public static boolean gameInProgress = true;

    public static void main(String[] args) throws IOException {
        GameController gC = new GameController();

        System.out.println("Welcome to the Dungeon Crawler Game!");

        //Sets up game
        gC.setUpGame();

        //Starts the game


        while (gameInProgress){
            System.out.println("----------------------------------------------------------------------------------------------------");
            gC.playGame();
            if (gC.players.get(0).getCurrentHP() <= 0) {
                gameInProgress = false;
            }
        }

    }

    public static boolean isGameInProgress() {
        return gameInProgress;
    }

    public static void setGameInProgress(boolean gameInProgress) {
        Main.gameInProgress = gameInProgress;
    }
}
