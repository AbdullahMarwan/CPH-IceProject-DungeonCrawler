package studyGroupF.fields;

import studyGroupF.player.Item;
import studyGroupF.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class WeaponSmith extends Field {
    public WeaponSmith(Item item, String fieldType, int fieldID) {
        super(item, fieldType, fieldID);
    }

    @Override
    void doFunction(Item item, Player player) {
        System.out.println("Would you like to upgrade your equipment? [Y] / [N] \n");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if(input.equalsIgnoreCase("y"))
        {
            player.getPlayerItems().get(0);
        }
        else if(input.equalsIgnoreCase("n"))
        {
            System.out.println("You walk away from the weaponsmith...");
        }
        else
        {
            System.out.println("\n---Invalid input, try again!---");
            doFunction(item, player);
        }
    }
    void printItemsByType(int id, Player player)
    {
        int commonAmount=0;
        int uncommonAmount=0;
        int rareAmount=0;
        int epicAmount=0;
        int legendaryAmount=0;

        for (Item item: player.getPlayerItems()) {
            if(item.getId()==id)
            {
                switch(item.getRarityValue())
                {
                    case 2-> commonAmount++;
                    case 3-> uncommonAmount++;
                    case 4-> rareAmount++;
                    case 5-> epicAmount++;
                    case 6-> legendaryAmount++;
                }
            }
        }
        System.out.println("""
               Amount of commons: """+commonAmount+"""
               Amount of uncommons: """+uncommonAmount+"""
               Amount of rares: """+rareAmount+"""
               Amount of epics: """+epicAmount+"""
               Amount of legendaries: """+legendaryAmount
                );
    }
}
