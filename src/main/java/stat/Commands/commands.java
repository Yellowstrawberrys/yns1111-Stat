package stat.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;

public class commands extends JavaPlugin implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        Player p = (Player) sender;
        Date date =java.util.Calendar.getInstance().getTime();
        p.sendMessage("current time" + (date));

        return false;
    }
}
