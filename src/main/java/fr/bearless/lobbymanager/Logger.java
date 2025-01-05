package fr.bearless.lobbymanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logger{
	
	public static void log(LogLevel level, String message){
		if(message == null) return;
		
		switch(level){
			case BASIC:
				Bukkit.getConsoleSender().sendMessage(message);
				break;
			case INFO:
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&e&lINFO&8] &f") + message);
				break;
			case SUCCESS:
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a&lSUCCESS&8] &f") + message);
				break;
			case WARNING:
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6&lWARNING&8] &f") + message);
				break;
			case ERROR:
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c&lERROR&8] &f") + message);
				break;
		}
	}
	
	public enum LogLevel{ BASIC, INFO, SUCCESS, WARNING, ERROR }

}