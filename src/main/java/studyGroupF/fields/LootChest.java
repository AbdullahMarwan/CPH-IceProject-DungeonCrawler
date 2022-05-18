package studyGroupF.fields;

import studyGroupF.player.Item;

import java.util.Locale;
import java.util.Scanner;

public class LootChest extends Field {
    public LootChest(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction() {
        item.createItem();
    }

    @Override
    void introduction() {
        System.out.println("You have found a LootChest ");
        System.out.println("To open it press Enter ");

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine().toLowerCase(Locale.ROOT);

        if (input.equals("enterKey")) { //Load previous PlayerData
            doFunction();
        } else if (input.equals("n")) {
            //skip();
        }
    }

    @Override
    int returnGold() {
        return 0;
    }

    @Override
    Item returnLoot() {
        return null;
    }
}
