package studyGroupF.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import studyGroupF.fields.Field;
import studyGroupF.player.Item;
import studyGroupF.player.Player;
import studyGroupF.shared.Level;
import studyGroupF.shared.Monster;
import studyGroupF.shared.SaveState;

import java.util.ArrayList;

public class GameData {
    private Player player;
    private ArrayList<Integer> fieldsID;
    boolean createNewPlayer = false;

    public GameData() {

        if (player == null) {
            player = new Player();
        }

        if (fieldsID == null) {
            fieldsID = new ArrayList<>();
        }
    }

    public GameData(Player player, ArrayList<Integer> fieldsID) {
        this.player = player;
        this.fieldsID = fieldsID;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Integer> getFieldsID() {
        return fieldsID;
    }

    public void setFieldsID(ArrayList<Integer> fieldsID) {
        this.fieldsID = fieldsID;
    }
}
