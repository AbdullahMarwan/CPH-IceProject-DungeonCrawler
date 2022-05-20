package studyGroupF.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface IO {

    ArrayList<String> readLevelData();
    ArrayList<String> readPlayerData() throws FileNotFoundException;
    ArrayList<String> readMonsterData() throws IOException;
    ArrayList<String> readItemData() throws IOException;

    boolean isPlayerDataAvailable() throws FileNotFoundException;

    void saveItemStorageData(ArrayList<String> data) throws IOException;
    void saveLevelData(ArrayList<String> data);

    void savePlayerData(ArrayList<String> data) throws IOException;
}
