package fr.bearless.lobbymanager;

import fr.bearless.lobbymanager.commands.ReloadCmd;
import fr.bearless.lobbymanager.commands.SpawnCmd;
import fr.bearless.lobbymanager.commands.SetSpawnCmd;
import fr.bearless.lobbymanager.events.PlayersEvent;
import fr.bearless.lobbymanager.events.PlayersTablist;
import fr.bearless.lobbymanager.events.WorldEvent;

public class Register{
	
	public static void commands(LobbyManager main){
		main.getCommand("lm-reload").setExecutor(new ReloadCmd(main));
		main.getCommand("lm-spawn").setExecutor(new SpawnCmd());
		main.getCommand("lm-setspawn").setExecutor(new SetSpawnCmd(main));
	}
	
	public static void events(LobbyManager main){
		main.getServer().getPluginManager().registerEvents(new WorldEvent(), main);
        main.getServer().getPluginManager().registerEvents(new PlayersEvent(), main);
        main.getServer().getPluginManager().registerEvents(new PlayersTablist(), main);
	}

}