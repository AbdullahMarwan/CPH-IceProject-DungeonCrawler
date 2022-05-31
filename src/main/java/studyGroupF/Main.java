package studyGroupF;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        GameController gC = new GameController();

        System.out.println("Welcome to the Dungeon Crawler Game!");

        //Sets up game
        gC.setUpGame();

        //Starts the game
        while (gC.isGameInProgress()){
            gC.playGame();
            if (gC.getPlayer().getCurrentHP() <= 0) {
                System.out.println("Game Over");
                gC.setGameInProgress(false);
            }
        }
    }

}
