package studyGroupF.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements IO {
    File playerData = new File("src/studyGroupF/Data/PlayerData");

    @Override
    public ArrayList<String> readGameData() {
        return null;
    }

    @Override
    public ArrayList<String> readPlayerData() {
        return null;
    }

    @Override
    public boolean isPlayerDataAvailable() throws FileNotFoundException {
        Scanner scan = new Scanner(playerData);

        if (scan.hasNextLine()) {
            return true;
        }

        return false;
    }

    @Override
    public void saveGameData() {

    }

    @Override
    public void savePlayerData() {

    }
}
