package fr.bearless.lobbymanager.listeners;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.managers.configs.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener implements Listener {
    private final ConfigManager configManager;

    public WorldListener() {
        configManager = LobbyManager.getConfigManager();
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if(!configManager.canWeatherChange()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent e) {
        if(!configManager.canMobSpawn()) {
            e.setCancelled(true);
        }
    }
}