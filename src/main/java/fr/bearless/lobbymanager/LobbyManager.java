package fr.bearless.lobbymanager;

import org.bukkit.plugin.java.JavaPlugin;

public class LobbyManager extends JavaPlugin {
	
	private static LobbyManager instance;
	
	@Override
	public void onEnable(){
		instance = this;
		
		saveDefaultConfig();
		
		Register.commands(instance);
		Register.events(instance);
	}
	
	public static LobbyManager getInstance(){
		return instance;
	}

}