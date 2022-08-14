package online.bingulhan.commandblocker.listener;

import online.bingulhan.commandblocker.CommandBlocker;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * @author BingulHan
 */
public class CommandListener implements Listener {

    @EventHandler
    public void event(PlayerCommandPreprocessEvent e) {
        if (e.getPlayer().hasPermission(CommandBlocker.getInstance().getBypassPermission())==false && e.getPlayer().isOp()==false) {

            String command = e.getMessage().split(" ")[0];

            if (CommandBlocker.getInstance().getCommands().stream().anyMatch(cmd -> command.equals("/"+cmd)) == false) {

                String message =CommandBlocker.getInstance().getMessage();

                for (String cmd : CommandBlocker.getInstance().getCommands()) {

                    message = message + "/"+cmd + ", ";

                }

                e.getPlayer().sendMessage(message);

                e.setCancelled(true);
            }


        }
    }
}
