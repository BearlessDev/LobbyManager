package fr.bearless.lobbymanager.managers.configs;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.utils.ConfigReader;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

/**
 * This class is just to easily access every key of the "messages.yml" config file.
 */
public class MessageManager {
    private final ConfigReader messagesConfig;

    public MessageManager() {
        messagesConfig = LobbyManager.getMessagesConfig();
    }

    // Command
    public String getCommandGlobalUsage() {
        return coloredText(messagesConfig.getConfig().getString("commands.globalUsage"), null, null, null);
    }
    public String getCommandNotForConsole() {
        return messagesConfig.getConfig().getString("commands.notConsoleCommand");
    }
    public String getSpawnCommandUsage() {
        return coloredText(messagesConfig.getConfig().getString("commands.spawn.usage"), null, null, null);
    }
    public String getSpawnCommandSuccess() {
        return coloredText(messagesConfig.getConfig().getString("commands.spawn.success"), null, null, null);
    }
    public String getSetSpawnCommandUsage() {
        return coloredText(messagesConfig.getConfig().getString("commands.setSpawn.usage"), null, null, null);
    }
    public String getSetSpawnCommandSuccess() {
        return coloredText(messagesConfig.getConfig().getString("commands.setSpawn.success"), null, null, null);
    }
    public String getReloadCommandUsage() {
        return coloredText(messagesConfig.getConfig().getString("commands.reload.usage"), null, null, null);
    }
    public String getReloadCommandReloadAll() {
        return coloredText(messagesConfig.getConfig().getString("commands.reload.reloadAll"), null, null, null);
    }
    public String getReloadCommandReloadConfig(String config) {
        return coloredText(messagesConfig.getConfig().getString("commands.reload.reloadConfig"), null, null, config);
    }

    // Player
    public String getCustomJoinMessage(Player player, String prefix) {
        return coloredText(messagesConfig.getConfig().getString("player.customJoinMessage"), player, prefix, null);
    }
    public String getCustomQuitMessage(Player player, String prefix) {
        return coloredText(messagesConfig.getConfig().getString("player.customQuitMessage"), player, prefix, null);
    }
    public String getChatFormat(Player player, String prefix) {
        return coloredText(messagesConfig.getConfig().getString("player.chatFormat"), player, prefix, null);
    }
    public String getNoPvPMessage() {
        return coloredText(messagesConfig.getConfig().getString("player.noPvPMessage"), null, null, null);
    }
    public String getNewVersionAvailable() {
        return coloredText(messagesConfig.getConfig().getString("player.newVersionAvailable"),null, null, null);
    }

    // Tablist
    public String getTablistHeader(Player player, String prefix) {
        return coloredText(messagesConfig.getConfig().getString("tablist.header"), player, prefix, null);
    }
    public String getTablistFooter(Player player, String prefix) {
        return coloredText(messagesConfig.getConfig().getString("tablist.footer"), player, prefix, null);
    }
    public String getTablistFormat(Player player, String prefix) {
        return coloredText(messagesConfig.getConfig().getString("tablist.format"), player, prefix, null);
    }

    // Permissions
    public String getBlockBreakPermissionMessage() {
        return coloredText(messagesConfig.getConfig().getString("permissions.block.break"), null, null, null);
    }
    public String getBlockPlacePermissionMessage() {
        return coloredText(messagesConfig.getConfig().getString("permissions.block.place"), null, null, null);
    }
    public String getSpawnCommandPermissionMessage() {
        return coloredText(messagesConfig.getConfig().getString("permissions.commands.spawn"), null, null, null);
    }
    public String getSetSpawnCommandPermissionMessage() {
        return coloredText(messagesConfig.getConfig().getString("permissions.commands.setSpawn"), null, null, null);
    }
    public String getReloadCommandPermissionMessage() {
        return coloredText(messagesConfig.getConfig().getString("permissions.commands.reload"), null, null, null);
    }

    // Utils
    private String coloredText(String text, @Nullable Player player, @Nullable String prefix, @Nullable String configFile) {
        return ChatColor.translateAlternateColorCodes('&', text)
                .replaceAll("%player%", player != null ? player.getDisplayName() : "")
                .replaceAll("%prefix%", prefix != null ? prefix : "")
                .replaceAll("%config%", configFile != null ? configFile : "");
    }
}