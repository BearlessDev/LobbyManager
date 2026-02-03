package fr.bearless.lobbymanager.managers.configs;

import fr.bearless.lobbymanager.LobbyManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

/**
 * This class is just to easily access every key of the "config.yml" config file.
 */
public class ConfigManager {
    private final LobbyManager plugin;
    private final FileConfiguration config;

    public ConfigManager() {
        config = LobbyManager.getInstance().getConfig();
        plugin = LobbyManager.getInstance();
    }

    // Spawn Location
    public String getSpawnWorld() {
        return config.getString("spawn.world");
    }
    public double getSpawnX() {
        return config.getDouble("spawn.x");
    }
    public double getSpawnY() {
        return config.getDouble("spawn.y");
    }
    public double getSpawnZ() {
        return config.getDouble("spawn.z");
    }
    public float getSpawnYaw() {
        return config.getInt("spawn.yaw");
    }
    public float getSpawnPitch() {
        return config.getInt("spawn.pitch");
    }
    public void setWorldSpawn(String world, double x, double y, double z, float yaw, float pitch) {
        config.set("spawn.world", world);
        config.set("spawn.x", Math.round(x * 100.0) / 100.0);
        config.set("spawn.y", Math.round(y * 100.0) / 100.0);
        config.set("spawn.z", Math.round(z * 100.0) / 100.0);
        config.set("spawn.yaw", (float) (Math.round(yaw * 100.0) / 100.0));
        config.set("spawn.pitch", (float) (Math.round(pitch * 100.0) / 100.0));
        plugin.saveConfig();
    }

    // Player
    public boolean sendPlayerAtSpawnOnJoin() {
        return config.getBoolean("player.sendToSpawnAtJoin");
    }
    public boolean enableCustomJoinMessage() {
        return config.getBoolean("player.enableCustomJoinMessage");
    }
    public boolean enableCustomQuitMessage() {
        return config.getBoolean("player.enableCustomQuitMessage");
    }
    public String getGamemode() {
        return config.getString("player.gamemode");
    }
    public boolean canPvP() {
        return config.getBoolean("player.canPvP");
    }
    public boolean isFallDamageEnabled() {
        return config.getBoolean("player.isFallDamageEnabled");
    }
    public boolean isFoodLossEnabled() {
        return config.getBoolean("player.isFoodLossEnabled");
    }
    public int getLevelOnJoin() {
        return config.getInt("player.levelOnJoin");
    }
    public float getExpOnJoin() {
        return config.getInt("player.expOnJoin");
    }
    public double getPlayerMaxHealth() {
        return config.getDouble("player.playerMaxHealth");
    }
    public int getPlayerFoodLevel() {
        return config.getInt("player.playerFoodLevel");
    }

    // World
    public boolean canWeatherChange() {
        return config.getBoolean("world.canWeatherChange");
    }
    public boolean canMobSpawn() {
        return config.getBoolean("world.canMobSpawn");
    }
    public boolean teleportToSpawnIfFall() {
        return config.getBoolean("world.teleportToSpawnIfFall");
    }
    public int teleportIfYPosUnder() {
        return config.getInt("world.teleportIfYPosUnder");
    }

    // Permissions
    public String getBlockBreakPermission() {
        return config.getString("permissions.block.break");
    }
    public String getBlockPlacePermission() {
        return config.getString("permissions.block.place");
    }
    public String getSpawnCommandPermission() {
        return config.getString("permissions.commands.spawn");
    }
    public String getSetSpawnCommandPermission() {
        return config.getString("permissions.commands.setSpawn");
    }
    public String getReloadCommandPermission() {
        return config.getString("permissions.commands.reload");
    }

    // Utils
    private String coloredText(String text, @Nullable Player player, @Nullable String prefix) {
        return ChatColor.translateAlternateColorCodes('&', text)
                .replaceAll("%player%", player != null ? player.getDisplayName() : "")
                .replaceAll("%prefix%", prefix != null ? prefix : "");
    }

}