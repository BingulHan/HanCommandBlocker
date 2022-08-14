package online.bingulhan.commandblocker;

import lombok.AccessLevel;
import lombok.Getter;
import online.bingulhan.commandblocker.blstat.Metrics;
import online.bingulhan.commandblocker.cmd.CMDCommandBlocker;
import online.bingulhan.commandblocker.listener.CommandListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;


/**
 * @author BingulHan
 */
@Getter
public final class CommandBlocker extends JavaPlugin {

    private List<String> commands = new ArrayList<>();
    private String bypassPermission;

    private String message;

    @Getter
    private static CommandBlocker instance;


    @Override
    public void onEnable() {

        instance=this;
        getConfig().options().copyDefaults(true);
        saveConfig();

        getLogger().info("Created by BingulHan");
        loadCommands();

        getServer().getPluginManager().registerEvents(new CommandListener(), this);
        getCommand("cb").setExecutor(new CMDCommandBlocker());

        int pluginId = 14082022;
        Metrics metrics = new Metrics(this, pluginId);

    }

    @Override
    public void onDisable() {

    }

    public void loadCommands() {
        commands.clear();
        reloadConfig();

        bypassPermission = getConfig().getString("bypass-permission");
        message = ChatColor.translateAlternateColorCodes('&', getConfig().getString("message"));

        ((List<String>) getConfig().get("commands")).stream().forEach(command -> commands.add(command));

        getServer().getLogger().info("Reload plugin.");
    }


    public void loadCommands(Player player) {
        commands.clear();
        reloadConfig();

        bypassPermission = getConfig().getString("bypass-permission");
        message = ChatColor.translateAlternateColorCodes('&', getConfig().getString("message"));

        player.sendMessage("New message : "+message);
        player.sendMessage("New bypass-permission : "+bypassPermission);

        ((List<String>) getConfig().get("commands")).stream().forEach(command -> commands.add(command));

        getServer().getLogger().info("Reload plugin.");
    }

}
