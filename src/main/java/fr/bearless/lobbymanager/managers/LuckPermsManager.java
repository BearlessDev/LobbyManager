package fr.bearless.lobbymanager.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import fr.bearless.lobbymanager.Logger;
import fr.bearless.lobbymanager.Logger.LogLevel;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;

public class LuckPermsManager{
	
	private static LuckPerms luckPerms;
	
	public static void initialize(){
		try{
			RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
			if(provider != null){
				luckPerms = provider.getProvider();
				
				Logger.log(LogLevel.INFO, "LuckPerms API successfully initialized.");
			}else{
				Logger.log(LogLevel.INFO, "LuckPerms API not found.");
			}
		}catch(NoClassDefFoundError e){
			Logger.log(LogLevel.INFO, "LuckPerms is not installed on the server.");
		}
	}
	
	public static boolean isAvailable(){
		return luckPerms != null;
	}
	
	public static LuckPerms getLuckPerms(){
		return luckPerms;
	}

	public static String getPlayerPrefix(Player player){
		if(!isAvailable()){
			return null;
		}
		
		LuckPerms luckPerms = getLuckPerms();
		
		User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
		String prefix = user.getCachedData().getMetaData().getPrefix();
		
		return prefix;
	}
	
}