package studyGroupF.data;

import studyGroupF.player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    public void savePlayerData(ArrayList<String> data) throws IOException {
        try {
            FileWriter myWriter = new FileWriter(playerData);
            boolean header = true;
            for(String s : data)
            {
                if (s.contains("Team") && !header) {
                    header = true;
                    myWriter.write("\n");
                }
                else
                {
                    header = false;
                }
                myWriter.write(s);
            }
            // myWriter.write(String.valueOf(data));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred.");
        }

    }
}
