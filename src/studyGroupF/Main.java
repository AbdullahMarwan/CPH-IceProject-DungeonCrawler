package studyGroupF;

public class Main {
    public static boolean gameInProgress = true;

    public static void main(String[] args) {
        GameController gC = new GameController();

        gC.setUpGame();
        while (gameInProgress){

            System.out.println("Welcome to the Dungeon Crawler Game!");
            
            System.out.println("----------------------------------------------------------------------------------------------------");


        }

    }
}
