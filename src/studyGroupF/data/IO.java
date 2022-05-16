package studyGroupF.data;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface IO {

    ArrayList<String> readLevelData();
    ArrayList<String> readPlayerData() throws FileNotFoundException;

    boolean isPlayerDataAvailable() throws FileNotFoundException;

    void saveLevelData();
    void savePlayerData();

}
