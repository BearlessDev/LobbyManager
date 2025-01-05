package fr.bearless.lobbymanager;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.bearless.lobbymanager.Logger.LogLevel;
import fr.bearless.lobbymanager.commands.ReloadCmd;
import fr.bearless.lobbymanager.commands.SetSpawnCmd;
import fr.bearless.lobbymanager.commands.SpawnCmd;
import fr.bearless.lobbymanager.commands.TestCmd;
import fr.bearless.lobbymanager.events.PlayersEvent;
import fr.bearless.lobbymanager.events.PlayersTablist;
import fr.bearless.lobbymanager.events.WorldEvent;
import fr.bearless.lobbymanager.managers.LuckPermsManager;

public class LobbyManager extends JavaPlugin {
	
	private static LobbyManager instance;
	
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
		
		registerCommands();
		registerEvents();
	}
	
	private void registerCommands(){
		getCommand("lm-reload").setExecutor(new ReloadCmd(this));
		getCommand("lm-spawn").setExecutor(new SpawnCmd());
		getCommand("lm-setspawn").setExecutor(new SetSpawnCmd(this));
		
		// Testing Beta Feature !
		getCommand("test").setExecutor(new TestCmd());
	}
	
	private void registerEvents(){
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new WorldEvent(), this);
        pm.registerEvents(new PlayersEvent(), this);
        pm.registerEvents(new PlayersTablist(), this);
	}
	
	public static LobbyManager getInstance(){
		return instance;
	}

}