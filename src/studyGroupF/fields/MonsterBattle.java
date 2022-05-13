package studyGroupF.fields;

import studyGroupF.BattleSystem;
import studyGroupF.Item;
import studyGroupF.Monster;

public class MonsterBattle extends Field {
    Monster monster = new Monster();
    BattleSystem battleSystem = new BattleSystem();

    public MonsterBattle(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    private void startbattle() {
        monster.createMonster();

    }

    @Override
    void doFunction(Item item) {

    }
}


