package studyGroupF.fields;

import studyGroupF.BattleSystem;
import studyGroupF.player.Item;
import studyGroupF.Monster;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MonsterBattle extends Field {
    private Monster monster;
    private BattleSystem battleSystem = new BattleSystem();

    public MonsterBattle(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    private void startbattle() throws IOException {
       //monster.createMonster();

    }

    @Override
    void doFunction(Item item) {

    }
}


