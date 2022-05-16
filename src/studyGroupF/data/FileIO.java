package studyGroupF.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements IO {
    File playerData = new File("src/studyGroupF/Data/PlayerData");

    @Override
    public ArrayList<String> readLevelData() {
        return null;
    }

    @Override
    public ArrayList<String> readPlayerData() throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();

        try {
            Scanner scan = new Scanner(playerData);
            while (scan.hasNextLine()) {
                data.add(scan.nextLine());
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
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
    public void saveLevelData() {

    }

    @Override
    public void savePlayerData() {

    }
}
