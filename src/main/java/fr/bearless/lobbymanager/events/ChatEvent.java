package fr.bearless.lobbymanager.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.bearless.lobbymanager.managers.LuckPermsManager;

public class ChatEvent implements Listener{
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		Player player = e.getPlayer();
		String message = e.getMessage();
		String prefix = LuckPermsManager.getPlayerPrefix(player);
		String format;
		
		if(prefix != null){
			format = prefix + player.getName() + " &7> &r" + message;
		}else{
			format = player.getName() + " &7> &r" + message;
		}
		
		e.setFormat(ChatColor.translateAlternateColorCodes('&', format));
	}

}