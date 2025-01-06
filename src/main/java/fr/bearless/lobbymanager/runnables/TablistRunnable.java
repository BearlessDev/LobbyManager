package fr.bearless.lobbymanager.runnables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.managers.LuckPermsManager;
import fr.bearless.lobbymanager.Config.GetString;
import fr.bearless.lobbymanager.tables.Tablist;

public class TablistRunnable{
	
	private final LobbyManager plugin;
	
	public TablistRunnable(LobbyManager plugin){
		this.plugin = plugin;
	}
	
	public void start(){
		new BukkitRunnable(){
			@Override
			public void run(){
				for(Player player : Bukkit.getOnlinePlayers()){			        
			        String header = ChatColor.translateAlternateColorCodes('&', GetString.getTablistHeader(player));
			        String footer = ChatColor.translateAlternateColorCodes('&', GetString.getTablistFooter(player));
			        String format;

			        if(LuckPermsManager.isAvailable()){
			        	if(LuckPermsManager.getPlayerPrefix(player) != null){
			        		format = ChatColor.translateAlternateColorCodes('&', GetString.getTablistFormat(player).replaceAll("%prefix%", LuckPermsManager.getPlayerPrefix(player)));
			        	}else{
			        		format = ChatColor.translateAlternateColorCodes('&', GetString.getTablistFormat(player).replaceAll("%prefix%", ""));
			        	}
			        }else{
			        	format = ChatColor.translateAlternateColorCodes('&', GetString.getTablistFormat(player));
			        }
			        
			        Tablist.sendToPlayer(player, header, footer);
			        player.setPlayerListName(format);
				}
			}
		}.runTaskTimer(plugin, 0, 20);
	}

}