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

        baseConfig = initConfig(BaseConfig.class, false);
        messageConfig = initConfig(MessageConfig.class, true);

        luckPermsManager = new LuckPermsManager();

        TablistUpdater tablistUpdater = new TablistUpdater();
        tablistUpdater.start();

        updateChecker = new UpdateChecker(this, 116001);
        updateChecker.fetchVersion(v -> {
            if(updateChecker.isNewerVersion(v)) {
                getLogger().info("A new version of the plugin is available: " + v);
            } else {
                getLogger().info("You are Running the latest version of this plugin.");
            }
        });

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