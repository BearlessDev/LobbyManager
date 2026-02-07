package fr.bearless.lobbymanager.components;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.configs.MessageConfig;
import fr.bearless.lobbymanager.managers.LuckPermsManager;
import fr.bearless.lobbymanager.utils.FormatText;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TablistUpdater {
    private final LobbyManager plugin;
    private final MessageConfig messageConfig;
    private final LuckPermsManager luckPermsManager;

    public TablistUpdater() {
        plugin = LobbyManager.getInstance();
        messageConfig = LobbyManager.getMessageConfig();
        luckPermsManager = LobbyManager.getLuckPermsManager();
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    String prefix = luckPermsManager.getPlayerPrefix(player);
                    String header = new FormatText(messageConfig.getTablistHeader()).withPrefix(prefix).withPlayer(player).build();
                    String footer = new FormatText(messageConfig.getTablistFooter()).withPrefix(prefix).withPlayer(player).build();
                    String format = new FormatText(messageConfig.getTablistFormat()).withPrefix(prefix).withPlayer(player).build();

                    Tablist.sendToPlayer(player, header, footer);
                    player.setPlayerListName(format);
                }
            }
        }.runTaskTimer(plugin, 0, 20*5);
    }
}