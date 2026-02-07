package fr.bearless.lobbymanager.configs;

import pl.mikigal.config.Config;
import pl.mikigal.config.annotation.ConfigName;
import pl.mikigal.config.annotation.ConfigPath;

@ConfigName("messages.yml")
public interface MessageConfig extends Config {

    // Command
    @ConfigPath("command.onlyPlayerCommand") default String getOnlyPlayerCommand() {
        return "This command can only be used by Player.";
    }
    @ConfigPath("command.spawn.usage") default String getSpawnCommandUsage() {
        return "&c&lUSAGE: &c/lobbymanager-spawn";
    }
    @ConfigPath("command.spawn.success") default String getSpawnCommandSuccess() {
        return "&aYou have been teleported to the Spawn.";
    }
    @ConfigPath("command.setSpawn.usage") default String getSetSpawnCommandUsage() {
        return "&c&lUSAGE: &c/lobbymanager-setspawn";
    }
    @ConfigPath("command.setSpawn.success") default String getSetSpawnCommandSuccess() {
        return "&aSpawn has been successfully set to your Location.";
    }
    @ConfigPath("command.reload.usage") default String getReloadCommandUsage() {
        return "&c&lUSAGE: &c/lobbymanager-reload <all|config|message>";
    }
    @ConfigPath("command.reload.reloadAll") default String getReloadAllCommand() {
        return "&aAll config file has been reloaded.";
    }
    @ConfigPath("command.reload.reloadConfig") default String getReloadConfigCommand() {
        return "&a%config% has been reloaded.";
    }

    // Player
    @ConfigPath("player.customJoinMessage") default String getCustomJoinMessage() {
        return "&7[&a+&7]&r %prefix% %player%";
    }
    @ConfigPath("player.customQuitMessage") default String getCustomQuitMessage() {
        return "&7[&c-&7]&r %prefix% %player%";
    }
    @ConfigPath("player.noPvPMesasge") default String getNoPvPMessage() {
        return "&cPvp is not allowed in this server.";
    }
    @ConfigPath("player.newVersionAvailable") default String getNewVersionAvailableMessage() {
        return "&aA new Version of the Plugin is available.";
    }
    @ConfigPath("player.chatFormat") default String getChatFormat() {
        return "%prefix% %player% &8» &r%message%";
    }

    // Tablist
    @ConfigPath("tablist.header") default String getTablistHeader() {
        return "&3&lLobbyManager &8| &b&nYour Server\n&7This header supports &f&lUNLIMITED LINES&7!\n&7Welcome back, &a%player%&7.";
    }
    @ConfigPath("tablist.footer") default String getTablistFooter() {
        return "&7Customize with &c&lCOLORS&7, &d&nUNDERLINE&7 or &e&oITALICS&7.\n&8(&7Edit this message in &fmessages.yml&8)";
    }
    @ConfigPath("tablist.format") default String getTablistFormat() {
        return "%prefix% %player%";
    }

    // Permission
    @ConfigPath("permission.block.place") default String getBlockPlacePermissionMessage() {
        return "&cYou don't have the required permission to Place Block.";
    }
    @ConfigPath("permission.block.break") default String getBlockBreakPermissionMessage() {
        return "&cYou don't have the required permission to Break Block.";
    }
    @ConfigPath("permission.command.spawn") default String getSpawnCommandPermissionMessage() {
        return "&cYou don't have the required permission for this command.";
    }
    @ConfigPath("permission.command.setSpawn") default String getSetSpawnCommandPermissionMessage() {
        return "&cYou don't have the required permission for this command.";
    }
    @ConfigPath("permission.command.reload") default String getReloadCommandPermissionMessage() {
        return "&cYou don't have the required permission for this command.";
    }
}