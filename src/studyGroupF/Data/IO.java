package studyGroupF.Data;

import java.util.ArrayList;

public interface IO {

    ArrayList<String> readGameData();
    ArrayList<String> readPlayerData();

    void saveGameData();
    void savePlayerData();

}
