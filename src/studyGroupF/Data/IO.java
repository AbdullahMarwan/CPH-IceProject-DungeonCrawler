package studyGroupF.Data;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface IO {

    ArrayList<String> readGameData();
    ArrayList<String> readPlayerData();

    boolean isPlayerDataAvailable() throws FileNotFoundException;

    void saveGameData();
    void savePlayerData();

}
