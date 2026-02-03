package fr.bearless.lobbymanager;

import fr.bearless.lobbymanager.commands.LobbyManagerCommand;
import fr.bearless.lobbymanager.components.TablistUpdater;
import fr.bearless.lobbymanager.listeners.PlayerListener;
import fr.bearless.lobbymanager.listeners.WorldListener;
import fr.bearless.lobbymanager.managers.configs.ConfigManager;
import fr.bearless.lobbymanager.managers.configs.MessageManager;
import fr.bearless.lobbymanager.managers.LuckPermsManager;
import fr.bearless.lobbymanager.utils.ConfigReader;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbyManager extends JavaPlugin {
    @Getter private static LobbyManager instance;

    @Getter private static ConfigReader messagesConfig;

    @Getter private static MessageManager messageManager;
    @Getter private static ConfigManager configManager;
    @Getter private static LuckPermsManager luckPermsManager;

    private static TablistUpdater tablistUpdater;

    @Override
    public void onEnable() {
        instance = this;

        messagesConfig = new ConfigReader(this, "", "messages.yml");
        messagesConfig.saveDefaultConfig();
        saveDefaultConfig();

        messageManager = new MessageManager();
        configManager = new ConfigManager();
        luckPermsManager = new LuckPermsManager();

        tablistUpdater = new TablistUpdater();
        tablistUpdater.start();

        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        getCommand("lobbymanager").setExecutor(new LobbyManagerCommand());
        getCommand("spawn").setExecutor(new LobbyManagerCommand());
        getCommand("setspawn").setExecutor(new LobbyManagerCommand());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
    }
}