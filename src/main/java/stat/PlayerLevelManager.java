package stat;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import stat.Stat;

public class PlayerLevelManager extends JavaPlugin {

    public int level;
    public int xp;

    public PlayerLevelManager(int level, int xp) {
        this.level = level;
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    private void xpcheck(Player player, PlayerLevelManager playerLevelManager) {
        int xpneeded = this.getConfig().getInt("Levels.1.xp");
        int xp = playerLevelManager.getXp();

        if (xp  >=xpneeded ) {
            player.sendMessage("§6Leveled UP!");
            playerLevelManager.setLevel(1);
        }
    }

}

