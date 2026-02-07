package fr.bearless.lobbymanager.listeners;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.configs.BaseConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener implements Listener {
    private final BaseConfig baseConfig;

    public WorldListener() {
        baseConfig = LobbyManager.getBaseConfig();
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if(baseConfig.getCanWeatherChange()) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent e) {
        if(baseConfig.getCanMobSpawn()) return;
        e.setCancelled(true);
    }
}