package studyGroupF.data;

import studyGroupF.player.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface IO {

    ArrayList<String> readLevelData();
    ArrayList<String> readPlayerData() throws FileNotFoundException;

    boolean isPlayerDataAvailable() throws FileNotFoundException;

    void saveLevelData();

    void savePlayerData(ArrayList<String> data) throws IOException;
}
