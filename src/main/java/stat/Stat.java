package stat;

import jossc.economy.EconomyAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;
import org.bukkit.entity.*;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.File;
import java.lang.annotation.Inherited;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;


public class Stat extends PlayerLevelManager implements CommandExecutor {

    private HashMap<Object, Object> levelManagerHashMap;

    public Stat(int level, int xp) {
        super(level, xp);
    }

    @Override
    public void onEnable() {
        //Plugin Startup Logic
        Bukkit.getLogger().info("Enabled!");
        getCommand("daytime");
        getCommand("statptest");
        this.levelManagerHashMap = new HashMap<>();
        this.getServer().getPluginManager().registerEvents(null, null);
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    @Override
    public void onDisable() {
        //Plugin Shutdown Logic
        Bukkit.getLogger().info("Disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        Player player = (Player) sender;
        if (cmd.getName().equals("statptest")) {
            player.sendMessage("Test");

        }
        return true;
    }

    private void xpcheck(Player player, PlayerLevelManager playerLevelManager) {
        int xpneeded = this.getConfig().getInt("Levels.1.xp");
        int xp = playerLevelManager.getXp();

        if (xp >= xpneeded) {
            player.sendMessage("§6Leveled UP!");
            playerLevelManager.setLevel(1);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective objective = board.registerNewObjective("yns", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("yns1111.mcv.kr" + new Color(252, 168, 187));


        int getmoney = EconomyAPI.getUser("playerName").getMoney(); //돈
        String name = player.getName();

        if (getmoney >= 1000 || getmoney <= 1000000) {
            String money = ((getmoney * 0.001) + "k");
        } else if (getmoney >= 1000000 || getmoney <= 1000000000) {
            String money = ((getmoney * 0.000001) + "m");
        } else if (getmoney >= 1000000000 || getmoney <= MAX_VALUE) {
            String money = ((getmoney * 0.00000001) + "b");
        } else if (getmoney >= MAX_VALUE) {
            String money = ("2.1b+");
            //돈 단위 표시

            Score score1 = objective.getScore("닉네임");
            score1.setScore(1);

            Score score2 = objective.getScore(name);
            score2.setScore(2);

            Score score3 = objective.getScore("");
            score3.setScore(3);

            Score score4 = objective.getScore("레벨");
            score4.setScore(4);

            Score score5 = objective.getScore("" + level);
            score5.setScore(5);

            Score score6 = objective.getScore("");
            score6.setScore(6);

            Score score7 = objective.getScore("돈");
            score7.setScore(7);

            Score score8 = objective.getScore(money);
            score8.setScore(8);


            Objective objective2 = board.registerNewObjective("hp", "dummy");

            double hp = player.getHealth();

            objective2.setDisplaySlot(DisplaySlot.BELOW_NAME);
            objective2.setDisplayName("hp" + hp);
            //체력 표시

            Objective objective3 = board.registerNewObjective("tab", "dummy");

            objective3.setDisplaySlot(DisplaySlot.PLAYER_LIST);
            objective3.setDisplayName("마승룡 서버 시즌2");
            //플레이어 목록 하단에 글자 표시
        }
    }
}


