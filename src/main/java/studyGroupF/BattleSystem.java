package studyGroupF;

import studyGroupF.player.Player;

public class BattleSystem {
    Player player;
    Monster monster;

    public int turnNr = 1;

    public BattleSystem(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    public void attack() {
        if(areAlive())
        {
            monster.setHP(monster.getHP() - player.getDamage());
            System.out.println(player.getPlayerName() + " dealt " + player.getDamage() + " to monster!\n");
            if(areAlive())
            {
            player.setCurrentHP(player.getCurrentHP() - monster.getDamage());
            System.out.println(monster.getMonsterType() + " dealt " + monster.getDamage() + " to " + player.getPlayerName() + "\n");
            }
        }
    }

    public boolean areAlive()
    {
        return monster.getHP()>0&&player.getCurrentHP()>0;
    }

    public void heal() {
        int healAmount = (int) (player.getMaxHP() * 0.5);

        player.setCurrentHP(player.getCurrentHP() + healAmount);
        System.out.println("You have healed for: " + healAmount);
    }

    public int getTurnNr() {
        return turnNr;
    }

    public void setTurnNr(int turnNr) {
        this.turnNr = turnNr;
    }
}
