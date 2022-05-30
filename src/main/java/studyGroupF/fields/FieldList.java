package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FieldList {
    private Item item;
    private int i;
    private ArrayList<Field> fields;
    boolean itemShop = false;
    boolean weaponSmith = false;


    public FieldList(int i) { //Loads random amount of random fields
        this.i = i;

        ArrayList<Field> tempFields = new ArrayList<>();

        for (int o = 0; o < i; o++) {
            if (o == 0) {
                tempFields.add(getFieldByID(5));
            } else {
                tempFields.add(getRandomField());
            }
        }

        fields = tempFields;
    }

    public FieldList(ArrayList<Integer> id) { //Loads previous fields from ID arraylist
        ArrayList<Field> tempFields = new ArrayList<>();
        int i = id.size();

        for (int o = 0; o < i; o++) {
            tempFields.add(getFieldByID(id.get(o)));
        }

        fields = tempFields;
    }

    public Field getFieldByID(int id) {

        return switch (id) {
            case 1 -> new MonsterBattle(item, "MonsterBattle", 1);
            case 2 -> new LootChest(item, "LootChest", 2);
            case 3 -> new ItemShop(item, "ItemShop", 3);
            case 4 -> new WeaponSmith(item, "WeaponSmith", 4);
            case 5 -> new EmptyField(item, "EmptyField", 5);
            case 6 -> new CampFire(item, "CampFire", 6);
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };

    }

    public Field currentField(int index) {
        return fields.get(index);
    }

    public void doFunction(Item item, Player player, int index) throws IOException {
        Field currentField = fields.get(index);

        currentField.doFunction(item, player);
    }

    public Field getRandomField() {
        Random r = new Random();
        int randomField = r.nextInt((130 - 1) + 1) + 1;

        if (randomField >= 1 && randomField <= 40) { //MonsterBattle
            return new MonsterBattle(item, "MonsterBattle", 1);
        } else if (randomField >= 41 && randomField <= 65) { //LootChest
            return new LootChest(item, "LootChest", 2);
        } else if (randomField >= 66 && randomField <= 85 && !itemShop) { //ItemShop
            itemShop = true;
            return new ItemShop(item, "ItemShop", 3);
        } else if (randomField >= 86 && randomField <= 95 && !weaponSmith) { //WeaponSmith
            weaponSmith = true;
            return new WeaponSmith(item, "WeaponSmith", 4);
        } else if (randomField >= 96 && randomField <= 115) { //EmptyField
            return new EmptyField(item, "EmptyField", 5);
        } else if (randomField >= 116 && randomField <= 130) { //EmptyField
            return new CampFire(item, "CampFire", 6);
        }

        return new MonsterBattle(item, "MonsterBattle", 1);
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
