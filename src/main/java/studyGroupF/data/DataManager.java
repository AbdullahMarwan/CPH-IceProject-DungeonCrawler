package studyGroupF.data;

import studyGroupF.player.Item;
import studyGroupF.player.Player;
import studyGroupF.shared.Level;
import studyGroupF.shared.SaveState;

import java.io.File;
import java.util.ArrayList;

public class DataManager {
    private static DataManager dataManagerInstance = null;
    private GameData gameData = null;
    private GameValues gameValues = null;
    private File gameDataJsonFile = new File("src/main/resources/data/GameData.json");
    private File gameValuesJsonFile = new File("src/main/resources/data/GameValues.json");

    public static DataManager getInstance() {

        if (dataManagerInstance == null) {
            dataManagerInstance = new DataManager();
        }

        if (dataManagerInstance.getGameData() == null) {
            dataManagerInstance.loadGameData();
        }

        if (dataManagerInstance.getGameValues() == null) {
            dataManagerInstance.loadGameValues();
        }

        return dataManagerInstance;
    }

    public boolean isGameDataAvailable() {
        return this.getGameData() != null;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public GameValues getGameValues() {
        return gameValues;
    }

    public void setGameValues(GameValues gameValues) {
        this.gameValues = gameValues;
    }

    public void loadGameData() {
        Object result = JSONHelper.getInstance().readJSON(
                gameDataJsonFile,
                GameData.class
        );

        if (result == null) {
            this.setGameData(null);
        }

        this.setGameData((GameData) result);
    }

    public void saveGameData() {
        JSONHelper.getInstance().writeJSON(
                gameDataJsonFile,
                gameData
        );
    }

    public void loadGameValues() {
        Object result = JSONHelper.getInstance().readJSON(
                gameValuesJsonFile,
                GameValues.class
        );

        if (result == null) {
            this.setGameValues(null);
        }

        this.setGameValues((GameValues) result);
    }

    public void saveGameValues() {
        JSONHelper.getInstance().writeJSON(
                gameValuesJsonFile,
                gameValues
        );
    }

    public void setPlayerData(Player player) {
        this.getGameData().setPlayer(player);
    }

    public void setFieldData(ArrayList<Integer> fieldData) {
        this.getGameData().setFieldsID(fieldData);
    }

    public void initializeGame(SaveState saveState) {
        switch (saveState) {
            case OLD_SAVE -> initializePreviousGame();
            case NEW_SAVE -> setGameData(new GameData());
        }
    }

    public void initializePreviousGame() {
        this.initializePreviousPlayerData();
        this.initializePreviousFieldsToLevel();
        this.initializePreviousItemStorage();
    }

    public ArrayList<Item> initializePreviousItemStorage() {
        return this.getGameData().getPlayer().getPlayerItems();
    }

    public Player initializePreviousPlayerData() {
        return this.getGameData().getPlayer();
    }

    public ArrayList<Integer> initializePreviousFieldsToLevel() {
        return this.getGameData().getFieldsID();
    }

    public Player initializeNewPlayerData() {
        return new Player();
    }
}
