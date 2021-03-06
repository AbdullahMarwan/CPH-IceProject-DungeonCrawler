package studyGroupF.shared;

import studyGroupF.player.Player;

public class BattleSystem {
    Player player;
    Monster monster;

    public BattleSystem(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    public void attack(boolean isPlayerTheAttacker) {
        if (isPlayerTheAttacker) {
            monster.setHP(monster.getHP() - player.getDamage());
            System.out.println(player.getPlayerName() + " dealt " + player.getDamage() + " to monster!");
        } else if (!isPlayerTheAttacker) {
            player.setCurrentHP(player.getCurrentHP() - monster.getDamage());
            System.out.println(monster.getMonsterType() + " dealt " + monster.getDamage() + " to " + player.getPlayerName() + "\n");
        }

    }

    public void heal() {
        int healAmount = (int) (player.getMaxHP() * 0.5);

        player.setCurrentHP(player.getCurrentHP() + healAmount);
        if(player.getCurrentHP() > player.getMaxHP()){
            player.setCurrentHP(player.getMaxHP());
        }
        System.out.println("You have healed for: " + healAmount);
    }

}
