package fr.bearless.lobbymanager.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.bearless.lobbymanager.Logger;
import fr.bearless.lobbymanager.Logger.LogLevel;
import fr.bearless.lobbymanager.managers.LuckPermsManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;

public class TestCmd implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(label.equalsIgnoreCase("test")){
			if(!(sender instanceof Player)){
				Logger.log(LogLevel.WARNING, "Only player can execute this command.");
				return true;
			}
			
			Player player = (Player) sender;
			
			if(LuckPermsManager.isAvailable()){
				LuckPerms luckPerms = LuckPermsManager.getLuckPerms();
				
				User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
				String prefix = user.getCachedData().getMetaData().getPrefix();
				
				if(prefix == null){
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cAucun Préfix trouvé."));
					return true;
				}
				
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e" + player.getName() + " &apossède le Préfix: &r" + prefix));
			}else{
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lErreur: &cLuckPerms n'est pas installé."));
				Logger.log(LogLevel.ERROR, "LuckPerms n'est pas disponible");
			}
		}
		
		return false;
	}

}