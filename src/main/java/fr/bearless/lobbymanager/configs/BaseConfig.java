package fr.bearless.lobbymanager.configs;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import pl.mikigal.config.Config;
import pl.mikigal.config.annotation.ConfigName;
import pl.mikigal.config.annotation.ConfigPath;

@ConfigName("config.yml")
public interface BaseConfig extends Config {

    // Spawn
    default Location getSpawn() {
        return new Location(Bukkit.getWorld("world"), 0.0, 100.0, 0.0, 0.0f, 0.0f);
    }
    void setSpawn(Location newSpawn);

    // Player
    @ConfigPath("player.enableDefaultJoinMessage") default boolean getEnableCustomJoinMessage() {
        return true;
    }
    @ConfigPath("player.enableDefaultQuitMessage") default boolean getEnableCustomQuitMessage() {
        return true;
    }
    @ConfigPath("player.sendToSpawnOnJoin") default boolean getSendToSpawnOnJoin() {
        return true;
    }
    @ConfigPath("player.gamemode") default GameMode getPlayerGamemode() {
        return GameMode.ADVENTURE;
    }
    @ConfigPath("player.canPvP") default boolean getCanPlayerPvP() { return false; }
    @ConfigPath("player.canTakeFallDamage") default boolean getPlayerCanTakeFallDamage() { return false; }
    @ConfigPath("player.canLostFood") default boolean getPlayerCanLostFood() { return false; }
    @ConfigPath("player.setLevelOnJoin") default int getPlayerLevelOnJoin() { return 2026; }
    @ConfigPath("player.setExpOnJoin") default double getPlayerExpOnJoin() { return 0.0; }
    @ConfigPath("player.maxHealth") default double getPlayerMaxHealth() { return 20.0; }
    @ConfigPath("player.foodLevel") default int getPlayerFoodLevel() { return 20; }
    @ConfigPath("player.preventVoidFall.enabled") default boolean getPreventPlayerVoidFallEnabled() { return true; }
    @ConfigPath("player.preventVoidFall.yLevel") default int getYLevelTarget() { return 5; }

    // World
    @ConfigPath("world.canMobSpawn") default boolean getCanMobSpawn() { return false; }
    @ConfigPath("world.canWeatherChanger") default boolean getCanWeatherChange() { return false; }

    // Permission
    @ConfigPath("permission.notifyNewUpdate") default String getNotifyNewUpdatePermission() { return "lobbymanager.version.notify"; }
    @ConfigPath("permission.block.place") default String getBlockPlacePermission() { return "lobbymanager.block.place"; }
    @ConfigPath("permission.block.break") default String getBlockBreakPermission() { return "lobbymanager.block.break"; }
    @ConfigPath("permission.command.spawn") default String getSpawnCommandPermission() { return "lobbymanager.command.spawn"; }
    @ConfigPath("permission.command.setSpawn") default String getSetSpawnCommandPermission() { return "lobbymanager.command.setspawn"; }
    @ConfigPath("permission.command.reload") default String getReloadCommandPermission() { return "lobbymanager.command.reload"; }
}