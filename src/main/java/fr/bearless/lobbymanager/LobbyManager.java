package fr.bearless.lobbymanager;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.bearless.lobbymanager.Logger.LogLevel;
import fr.bearless.lobbymanager.commands.ReloadCmd;
import fr.bearless.lobbymanager.commands.SetSpawnCmd;
import fr.bearless.lobbymanager.commands.SpawnCmd;
import fr.bearless.lobbymanager.events.ChatEvent;
import fr.bearless.lobbymanager.events.PlayersEvent;
import fr.bearless.lobbymanager.events.WorldEvent;
import fr.bearless.lobbymanager.managers.ConfigManager;
import fr.bearless.lobbymanager.managers.LuckPermsManager;

public class LobbyManager extends JavaPlugin {
	
	private static LobbyManager instance;
	private static ConfigManager messageConfig;
	
	@Override
	public void onEnable(){
		instance = this;
		
		LuckPermsManager.initialize();
		
		if(LuckPermsManager.isAvailable()){
			Logger.log(LogLevel.INFO, "Enabling LuckPerms functionnality...");
		}else{
			Logger.log(LogLevel.INFO, "LuckPerms was not found, some functionnality may not work.");
		}
		
		saveDefaultConfig();
		initializeConfigs();
		
		registerCommands();
		registerEvents();
	}
	
	private void registerCommands(){
		getCommand("lm-reload").setExecutor(new ReloadCmd(this));
		getCommand("lm-spawn").setExecutor(new SpawnCmd());
		getCommand("lm-setspawn").setExecutor(new SetSpawnCmd(this));
	}
	
	private void registerEvents(){
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new WorldEvent(), this);
        pm.registerEvents(new PlayersEvent(this), this);
        pm.registerEvents(new ChatEvent(), this);
	}
	
	public static LobbyManager getInstance(){
		return instance;
	}
	
	public void initializeConfigs(){
		messageConfig = new ConfigManager(this, "", "message.yml");
		
		messageConfig.saveDefaultConfig();
	}
	
	public ConfigManager getConfigs(String config){
		switch(config){
			case "message":
				return messageConfig;
			default:
				return null;
		}
	}
	
}