package studyGroupF.data;

import studyGroupF.fields.Field;
import studyGroupF.player.Item;
import studyGroupF.player.Player;
import studyGroupF.shared.Level;
import studyGroupF.shared.SaveState;

import java.io.IOException;
import java.util.ArrayList;

public class DataManager {
    static FileIO fileIO = new FileIO();
    static Level level;

    public static void saveData(ArrayList<Level> levels, ArrayList<Player> players) throws IOException {
        fileIO.savePlayerData(addPlayersToData(players));
        fileIO.saveLevelData(addLevelToData(levels));
        fileIO.saveItemStorageData(addItemStorageToData(players));
    }

    public static Player initializePlayerData(SaveState saveState) {
        return switch (saveState) {
            case OLD_SAVE -> DataManager.initializePreviousPlayerData();
            case NEW_SAVE -> DataManager.initializeNewPlayerData();
        };
    }

    /* //TODO Maybe move loading and creating levels here?
    public static Field[] initializeLevelData(SaveState saveState) {


        return switch (saveState) {
            case OLD_SAVE -> level.loadPreviousFieldsToLevel();
            case NEW_SAVE -> level.addRandomsFieldsToLevel();
        };
    }
     */

    public static ArrayList<Item> initializeStorageData(SaveState saveState) {
        return switch (saveState) {
            case OLD_SAVE -> DataManager.initializeOldItemStorage();
            case NEW_SAVE -> null;
        };
    }

    public static ArrayList<Item> initializeOldItemStorage() {
        ArrayList<Item> items = new ArrayList<>();

        ArrayList<String> data;
        data = fileIO.readItemData();

        for (String s : data) {
            String[] values = s.split(", ");

            String itemName = values[0];
            String itemType = values[1];
            String rarityName = values[2];
            int rarityValue = Integer.parseInt(values[3]);
            int id = Integer.parseInt(values[4]);
            boolean inUse = Boolean.parseBoolean(values[5]);

            Item item = new Item(itemName, itemType, rarityName, rarityValue, id, inUse);
            items.add(item);
        }
        return items;
    }

    public static Player initializePreviousPlayerData() {
        String playerName = "";
        int maxHP = 0;
        int currentHP = 0;
        int damage = 0;
        int gold = 0;
        int currentLevel = 0;
        int currentTile = 0;
        int amountOfPotions = 0;
        int extraGoldGain = 0;

        ArrayList<String> data;
        data = fileIO.readPlayerData();

        for (String s : data) {
            //System.out.println(s);
            String[] values = s.split(", ");

            playerName = values[0];
            maxHP = Integer.parseInt(values[1]);
            currentHP = Integer.parseInt(values[2]);
            damage = Integer.parseInt(values[3]);
            gold = Integer.parseInt(values[4]);
            currentLevel = Integer.parseInt(values[5]);
            currentTile = Integer.parseInt(values[6]);
            amountOfPotions = Integer.parseInt(values[7]);
            extraGoldGain = Integer.parseInt(values[8]);
        }

        return new Player(playerName, maxHP, currentHP, damage, gold, currentLevel, currentTile, amountOfPotions, extraGoldGain);
    }

    public static Player initializeNewPlayerData() {
        return new Player();
    }

    public static ArrayList<String> addItemStorageToData(ArrayList<Player> players) {
        ArrayList<String> data = new ArrayList<>();

        for (Item t : players.get(0).getPlayerItems()) {
            data.add(t.getItemName() + ", ");
            data.add(t.getItemType() + ", ");
            data.add(t.getRarityName() + ", ");
            data.add(t.getRarityValue() + ", ");
            data.add(t.getId() + ", ");
            data.add(t.isInUse() + "");
        }

        return data;
    }

    public static ArrayList<String> addLevelToData(ArrayList<Level> levels) {
        ArrayList<String> data = new ArrayList<>();

        for (Level l : levels) {

            for (int i = 0; i < l.fields.length; i++) {
                if (i == l.fields.length - 1) {
                    data.add(l.fieldList.currentField(i).getFieldID() + "");
                } else {
                    data.add(l.fieldList.currentField(i).getFieldID() + ", ");
                }
            }

        }

        return data;
    }

    public static ArrayList<String> addPlayersToData(ArrayList<Player> players) {
        ArrayList<String> data = new ArrayList<>();

        for (Player t : players) {
            data.add(t.getPlayerName() + ", ");
            data.add(t.getMaxHP() + ", ");
            data.add(t.getCurrentHP() + ", ");
            data.add(t.getDamage() + ", ");
            data.add(t.getGold() + ", ");
            data.add(t.getCurrentLevel() + ", ");
            data.add(t.getCurrentTile() + ", ");
            data.add(t.getAmountOfPotions() + ", ");
            data.add(t.getExtraGoldGain() + "");
        }

        return data;
    }

}
