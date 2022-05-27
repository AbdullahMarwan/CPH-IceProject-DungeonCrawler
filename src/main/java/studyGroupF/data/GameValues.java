package studyGroupF.data;

import studyGroupF.shared.Monster;

import java.util.ArrayList;

public class GameValues {
    private ArrayList<Monster> monsters;

    public GameValues() {
        if (monsters == null) {
            monsters = new ArrayList<>();
        }
    }

    public GameValues(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }
}
