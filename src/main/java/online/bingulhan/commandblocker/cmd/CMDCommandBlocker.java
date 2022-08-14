package online.bingulhan.commandblocker.cmd;

import online.bingulhan.commandblocker.CommandBlocker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BingulHan
 */
public class CMDCommandBlocker implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length>0) {
            if (args[0].equalsIgnoreCase("reload")) {

                if (sender instanceof Player) {
                    CommandBlocker.getInstance().loadCommands((Player) sender);
                }else {
                    CommandBlocker.getInstance().loadCommands();
                }
                sender.sendMessage(ChatColor.GREEN+"Successful plugin");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> strings = new ArrayList<>();
        strings.add("reload");

        return strings;
    }
}
