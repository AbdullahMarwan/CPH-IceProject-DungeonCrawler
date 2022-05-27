package studyGroupF.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import studyGroupF.fields.Field;
import studyGroupF.player.Item;
import studyGroupF.player.Player;
import studyGroupF.shared.Level;
import studyGroupF.shared.Monster;

import java.util.ArrayList;

public class GameData {
    private Player player;
    private Level level;

    public GameData() {
        if (player == null) {
            player = new Player();
        }

        if (level == null) {
            level = new Level();
        }
    }

    public GameData(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
