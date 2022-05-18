package studyGroupF.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileIO implements IO {
    File playerDataFile = new File("src/main/java/studyGroupF/Data/PlayerData");
    File monsterDataFile = new File("src/main/java/studyGroupF/Data/MonsterData");
    File levelDataFile = new File("src/main/java/studyGroupF/Data/LevelData");
    File itemStorageFile = new File("src/main/java/studyGroupF/Data/PlayerItemStorage");

    int amountOfLinesInMonsterDataFile;

    @Override
    public ArrayList<String> readLevelData() {
        ArrayList<String> levelData = new ArrayList<>();

        try {
            Scanner scan = new Scanner(levelDataFile);
            while (scan.hasNextLine()) {
                levelData.add((scan.nextLine()));
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        return levelData;
    }

    @Override
    public ArrayList<String> readPlayerData() throws FileNotFoundException {
        ArrayList<String> playerData = new ArrayList<>();

        try {
            Scanner scan = new Scanner(playerDataFile);
            while (scan.hasNextLine()) {
                playerData.add(scan.nextLine());
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        return playerData;
    }

    @Override
    public ArrayList<String> readMonsterData() throws IOException {
        ArrayList<String> monsterData = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get(String.valueOf(monsterDataFile)), Charset.defaultCharset());
        setAmountOfLinesInMonsterDataFile(lines.size());

        try {
            Scanner scan = new Scanner(monsterDataFile);
            while (scan.hasNextLine()) {
                monsterData.add(scan.nextLine());
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        return monsterData;
    }

    @Override
    public ArrayList<String> readItemData() throws IOException {
        ArrayList<String> itemStorageData = new ArrayList<>();

        try {
            Scanner scan = new Scanner(itemStorageFile);
            while (scan.hasNextLine()) {
                itemStorageData.add(scan.nextLine());
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        return itemStorageData;
    }

    @Override
    public boolean isPlayerDataAvailable() throws FileNotFoundException {
        Scanner scan = new Scanner(playerDataFile);

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
            FileWriter myWriter = new FileWriter(playerDataFile);
            boolean header = true;
            for (String s : data) {
                if (s.contains("Team") && !header) {
                    header = true;
                    myWriter.write("\n");
                } else {
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

    public int getAmountOfLinesInMonsterDataFile() {
        return amountOfLinesInMonsterDataFile;
    }

    public void setAmountOfLinesInMonsterDataFile(int amountOfLinesInMonsterDataFile) {
        this.amountOfLinesInMonsterDataFile = amountOfLinesInMonsterDataFile;
    }
}
