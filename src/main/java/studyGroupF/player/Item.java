package studyGroupF.player;

import java.util.ArrayList;
import java.util.Random;

public class Item {
    private String itemName;
    private String itemType;
    private String rarityName;
    private int id;
    private int rarityValue = 1;
    String[] itemRarities = {"Common", "Uncommon", "Rare", "Epic", "Legendary"};
    String[] itemTypes = {"Gold Statue", "Extra Damage", "Extra maxHP"};

    public Item(String itemName, String itemType, String rarityName, int id, int rarityValue) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.rarityName = rarityName;
        this.id = id;
        this.rarityValue = rarityValue;
    }

    public int setItemRarityValue(String rarityName) {
        int commonRarity = 2;
        int uncommonRarity = 3;
        int rareRarity = 4;
        int epicRarity = 5;
        int legendaryRarity = 6;

        switch (rarityName) {
            case "Common" -> {
                return rarityValue = commonRarity;
            }
            case "Uncommon" -> {
                return rarityValue = uncommonRarity;
            }
            case "Rare" -> {
                return rarityValue = rareRarity;
            }
            case "Epic" -> {
                return rarityValue = epicRarity;
            }
            case "Legendary" -> {
                return rarityValue = legendaryRarity;
            }
        }
        return 1;
    }

    //Method to perform the chosen item's effect
    public void performUseItem(Item item, int goldGained) {
        int extraGold = 20;
        int extraMaxHP = 10;

        switch (id) {
            case 1 -> { //Increase Gold Gained by [20-100]
                player.setGold(goldGained + extraGold * item.rarityValue);
            }

            case 2 -> { //Increase Sword Damage by [1-5] for each enemy slain
                player.setDamage(player.getDamage() + item.rarityValue);
            }

            case 3 -> { //Increase MaxHP by 10-50
                player.setMaxHP(player.getMaxHP() + extraMaxHP * item.rarityValue);
            }

        }
    }

    public void createItem() {
        //Randomly chooses item rarity
        String rarityName = selectRandomItemRarity();

        //Randomly chooses Rarity Value
        int rarityValue = setItemRarityValue(rarityName);

        //Randomly chooses item type
        Random r = new Random();
        int id = r.nextInt((3 - 1) + 1) + 1;
        String itemType = selectRandomItemType(id);

        //Item name is combined from Rarity and Type of the item, for example: "Legendary Gold Statue"
        String itemName = rarityName + itemType;

        Item item = new Item(itemName, itemType, rarityName, id, rarityValue);
    }

    public String selectRandomItemRarity() {
        Random r = new Random();
        int randomNr = r.nextInt((100 - 1) + 1) + 1;

        String rarityName = "";

        if (randomNr >= 1 && randomNr <= 40) { //Common
            rarityName = itemRarities[0];
        } else if (randomNr >= 41 && randomNr <= 65) { //Uncommon
            rarityName = itemRarities[1];
        } else if (randomNr >= 66 && randomNr <= 85) { //Rare
            rarityName = itemRarities[2];
        } else if (randomNr >= 86 && randomNr <= 95) { //Epic
            rarityName = itemRarities[3];
        } else if (randomNr >= 96 && randomNr <= 100) { //Legendary
            rarityName = itemRarities[4];
        }

        return rarityName;
    }

    public String selectRandomItemType(int id) {
        String itemType = "";

        return switch (id) {
            case 1 -> itemType = itemTypes[0];
            case 2 -> itemType = itemTypes[1];
            case 3 -> itemType = itemTypes[2];
            default -> "Broken";
        };
    }

    public void loadItemsFromFileToArrayList() {
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRarityName() {
        return rarityName;
    }

    public void setRarityName(String rarityName) {
        this.rarityName = rarityName;
    }

    public int getRarityValue() {
        return rarityValue;
    }

    public void setRarityValue(int rarityValue) {
        this.rarityValue = rarityValue;
    }
}
