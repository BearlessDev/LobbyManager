package fr.bearless.lobbymanager;

import fr.bearless.lobbymanager.commands.ReloadCommand;
import fr.bearless.lobbymanager.commands.SetSpawnCommand;
import fr.bearless.lobbymanager.commands.SpawnCommand;
import fr.bearless.lobbymanager.components.TablistUpdater;
import fr.bearless.lobbymanager.configs.BaseConfig;
import fr.bearless.lobbymanager.configs.MessageConfig;
import fr.bearless.lobbymanager.listeners.PlayerListener;
import fr.bearless.lobbymanager.listeners.WorldListener;
import fr.bearless.lobbymanager.managers.LuckPermsManager;
import fr.bearless.lobbymanager.utils.UpdateChecker;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mikigal.config.Config;
import pl.mikigal.config.ConfigAPI;
import pl.mikigal.config.style.CommentStyle;
import pl.mikigal.config.style.NameStyle;

public class LobbyManager extends JavaPlugin {
    @Getter private static LobbyManager instance;

    // Configs
    @Getter private static BaseConfig baseConfig;
    @Getter private static MessageConfig messageConfig;

    // Managers
    @Getter private static LuckPermsManager luckPermsManager;

    // Utils
    @Getter private UpdateChecker updateChecker;

    @Override
    public void onEnable() {
        instance = this;

        updateChecker = new UpdateChecker(this, 116001);
        updateChecker.checkForUpdate(v -> {
            if(updateChecker.isUpdateAvailable()) {
                getLogger().info("A new Update is available: " + v);
                getLogger().info("You are running on Version: " + getDescription().getVersion());
            } else {
                getLogger().info("You are running on the Latest Version.");
            }
        });

        baseConfig = initConfig(BaseConfig.class, false);
        messageConfig = initConfig(MessageConfig.class, true);

        luckPermsManager = new LuckPermsManager();

        TablistUpdater tablistUpdater = new TablistUpdater();
        tablistUpdater.start();

        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        getCommand("lobbymanager-spawn").setExecutor(new SpawnCommand());
        getCommand("lobbymanager-setspawn").setExecutor(new SetSpawnCommand());
        getCommand("lobbymanager-reload").setExecutor(new ReloadCommand());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
    }

    private <T extends Config> T initConfig(Class<T> clazz, boolean translateColorCode) {
        return ConfigAPI.init(
                clazz,
                NameStyle.CAMEL_CASE,
                CommentStyle.ABOVE_CONTENT,
                translateColorCode,
                this
        );
    }
}