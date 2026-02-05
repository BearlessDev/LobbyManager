package fr.bearless.lobbymanager.managers.configs;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.utils.ConfigReader;

/**
 * This class is just to easily access every key of the "config.yml" config file.
 */
public class ConfigManager {
    private final ConfigReader config;

    public ConfigManager() {
        config = LobbyManager.getBaseConfig();
    }

    // Spawn Location
    public String getSpawnWorld() {
        return config.getConfig().getString("spawn.world");
    }
    public double getSpawnX() {
        return config.getConfig().getDouble("spawn.x");
    }
    public double getSpawnY() {
        return config.getConfig().getDouble("spawn.y");
    }
    public double getSpawnZ() {
        return config.getConfig().getDouble("spawn.z");
    }
    public float getSpawnYaw() {
        return config.getConfig().getInt("spawn.yaw");
    }
    public float getSpawnPitch() {
        return config.getConfig().getInt("spawn.pitch");
    }
    public void setWorldSpawn(String world, double x, double y, double z, float yaw, float pitch) {
        config.getConfig().set("spawn.world", world);
        config.getConfig().set("spawn.x", Math.round(x * 100.0) / 100.0);
        config.getConfig().set("spawn.y", Math.round(y * 100.0) / 100.0);
        config.getConfig().set("spawn.z", Math.round(z * 100.0) / 100.0);
        config.getConfig().set("spawn.yaw", (float) (Math.round(yaw * 100.0) / 100.0));
        config.getConfig().set("spawn.pitch", (float) (Math.round(pitch * 100.0) / 100.0));
        config.saveConfig();
    }

    // Player
    public boolean sendPlayerAtSpawnOnJoin() {
        return config.getConfig().getBoolean("player.sendToSpawnAtJoin");
    }
    public boolean enableCustomJoinMessage() {
        return config.getConfig().getBoolean("player.enableCustomJoinMessage");
    }
    public boolean enableCustomQuitMessage() {
        return config.getConfig().getBoolean("player.enableCustomQuitMessage");
    }
    public String getGamemode() {
        return config.getConfig().getString("player.gamemode");
    }
    public boolean canPvP() {
        return config.getConfig().getBoolean("player.canPvP");
    }
    public boolean isFallDamageEnabled() {
        return config.getConfig().getBoolean("player.isFallDamageEnabled");
    }
    public boolean isFoodLossEnabled() {
        return config.getConfig().getBoolean("player.isFoodLossEnabled");
    }
    public int getLevelOnJoin() {
        return config.getConfig().getInt("player.levelOnJoin");
    }
    public float getExpOnJoin() {
        return config.getConfig().getInt("player.expOnJoin");
    }
    public double getPlayerMaxHealth() {
        return config.getConfig().getDouble("player.playerMaxHealth");
    }
    public int getPlayerFoodLevel() {
        return config.getConfig().getInt("player.playerFoodLevel");
    }

    // World
    public boolean canWeatherChange() {
        return config.getConfig().getBoolean("world.canWeatherChange");
    }
    public boolean canMobSpawn() {
        return config.getConfig().getBoolean("world.canMobSpawn");
    }
    public boolean teleportToSpawnIfFall() {
        return config.getConfig().getBoolean("world.teleportToSpawnIfFall");
    }
    public int teleportIfYPosUnder() {
        return config.getConfig().getInt("world.teleportIfYPosUnder");
    }

    // Permissions
    public String getBlockBreakPermission() {
        return config.getConfig().getString("permissions.block.break");
    }
    public String getBlockPlacePermission() {
        return config.getConfig().getString("permissions.block.place");
    }
    public String getSpawnCommandPermission() {
        return config.getConfig().getString("permissions.commands.spawn");
    }
    public String getSetSpawnCommandPermission() {
        return config.getConfig().getString("permissions.commands.setSpawn");
    }
    public String getReloadCommandPermission() {
        return config.getConfig().getString("permissions.commands.reload");
    }

}