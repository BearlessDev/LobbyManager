package fr.bearless.lobbymanager.commands;

import fr.bearless.lobbymanager.Config.GetString;
import fr.bearless.lobbymanager.LobbyManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCmd implements CommandExecutor {

    private final LobbyManager plugin;

    public ReloadCmd(LobbyManager main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(label.equalsIgnoreCase("lobbymanager-reload") || label.equalsIgnoreCase("lm-reload")){
            	if(!player.hasPermission(GetString.getReloadPermission())){
            		player.sendMessage(ChatColor.translateAlternateColorCodes('&', GetString.getReloadPermissionMessage()));
            		return true;
            	}
            	if(args.length > 1){
            		player.sendMessage(ChatColor.translateAlternateColorCodes('&', GetString.getReloadUsageMessage()));
            		return true;
            	}
            	
                if(args.length == 1){
                	String configName = args[0];
                	
                	switch(configName){
                		case "message":
                			plugin.getConfigs("message").reloadConfig();
                			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lMessage.yml &ahas been reloaded."));
                			break;
                		default:
                			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l" + configName + " &cn'existe pas."));
                	}
                	return true;
                }
                
                // Reload all the Config Files
                plugin.reloadConfig();
                plugin.getConfigs("message").reloadConfig();
                
                // Send all the Reload Message
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', GetString.getReloadMessage()));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lMessage.yml &ahas been reloaded."));
            }
        }

        return false;
    }
}