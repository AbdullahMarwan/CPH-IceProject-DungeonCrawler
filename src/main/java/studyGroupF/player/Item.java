package studyGroupF.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Random;

@JsonIgnoreProperties(value = {
        "itemRarities",
        "itemTypes"
})
public class Item {
    private String itemName;
    private String itemType;
    private String rarityName;
    private int rarityValue = 1;
    private int id;
    private int goldCost;
    private boolean inUse = false;
    String[] itemRarities = {"Common", "Uncommon", "Rare", "Epic", "Legendary"};
    String[] itemTypes = {"Extra Gold", "Extra Damage", "Extra maxHP"};

    public Item(String itemName, String itemType, String rarityName, int rarityValue, int id, boolean inUse) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.rarityName = rarityName;
        this.rarityValue = rarityValue;
        this.id = id;
        this.inUse = inUse;
    }

    //Empty constructor used to call methods
    public Item() {

    }

    public void matchNewItemProperties(Item item, int rarityValue, Player player) { //Changes an item's rarity and value corresponding to desired rarity

        item.setRarityValue(rarityValue);
        item.setRarityName(getRarityName(rarityValue));
        item.setItemName(item.getRarityName() + " " + item.getItemType());
        reApplyAllItems(player);
    }

    public String getRarityName(int rarityValue) {
        return switch (rarityValue) {
            case 2 -> "Common";
            case 3 -> "Uncommon";
            case 4 -> "Rare";
            case 5 -> "Epic";
            case 6 -> "Legendary";
            default -> throw new IllegalStateException("Unexpected value: " + rarityValue);
        };
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

    public void reApplyAllItems(Player currentPlayer){ //Resets player's stats to default and re-applies all item effects
        Player playerDummy = new Player();


        for (Item i : currentPlayer.getPlayerItems()) {
            i.setInUse(false);
        }

        currentPlayer.setMaxHP(playerDummy.getMaxHP());
        currentPlayer.setDamage(playerDummy.getDamage());
        currentPlayer.setExtraGoldGain(playerDummy.getExtraGoldGain());

        useItems(currentPlayer);
    }

    //Method to perform the chosen items effect
    public void useItems(Player player) {
        int extraGold = 1;
        int extraMaxHP = 5;
        int extraDMG = 2;

        for (Item i : player.getPlayerItems()) {

            switch (i.getId()) {
                case 1 -> { //Increase Gold Gained
                    if (!i.inUse) {
                        player.setExtraGoldGain(player.getExtraGoldGain() + extraGold * i.getRarityValue());
                        i.inUse = true;
                    }
                }

                case 2 -> { //Increase Sword Damage
                    if (!i.inUse) {
                        player.setDamage(player.getDamage() + extraDMG * i.getRarityValue());
                        i.inUse = true;
                    }
                }

                case 3 -> { //Increase MaxHP
                    if (!i.inUse) {
                        player.setMaxHP(player.getMaxHP() + extraMaxHP * i.getRarityValue());
                        i.inUse = true;
                    }
                }

            }
        }
    }

    //Created a fully randomized item
    public Item createItem() {
        //Randomly chooses item rarity
        String rarityName = selectRandomItemRarity();

        //Randomly chooses Rarity Value
        int rarityValue = setItemRarityValue(rarityName);

        //Randomly chooses item type
        Random r = new Random();
        int id = r.nextInt((3 - 1) + 1) + 1;
        String itemType = selectRandomItemType(id);

        //Item name is combined from Rarity and Type of the item, for example: "Legendary Gold Statue"
        String itemName = rarityName + " " + itemType;

        boolean inUse = false;

        return new Item(itemName, itemType, rarityName, rarityValue, id, inUse);
    }

    public String selectRandomItemRarity() {
        Random r = new Random();
        int randomNr = r.nextInt((100 - 1) + 1) + 1;

        String rarityName;

        if (randomNr <= 40) { //Common
            rarityName = itemRarities[0];
        } else if (randomNr <= 65) { //Uncommon
            rarityName = itemRarities[1];
        } else if (randomNr <= 85) { //Rare
            rarityName = itemRarities[2];
        } else if (randomNr <= 95) { //Epic
            rarityName = itemRarities[3];
        } else { //Legendary
            rarityName = itemRarities[4];
        }

        return rarityName;
    }

    public String selectRandomItemType(int id) {
        return switch (id) {
            case 1 -> itemTypes[0];
            case 2 -> itemTypes[1];
            case 3 -> itemTypes[2];
            default -> "Broken";
        };
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

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(int goldCost) {
        this.goldCost = goldCost;
    }

    public String[] getItemRarities() {
        return itemRarities;
    }

    public String[] getItemTypes() {
        return itemTypes;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    @Override
    public String toString() {
        return "Item name: " + itemName;
    }
}
