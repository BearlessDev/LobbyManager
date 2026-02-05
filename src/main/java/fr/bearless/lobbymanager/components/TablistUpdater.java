package fr.bearless.lobbymanager.components;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.managers.LuckPermsManager;
import fr.bearless.lobbymanager.managers.configs.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TablistUpdater {
    private final LobbyManager plugin;
    private final MessageManager messageManager;
    private final LuckPermsManager luckPermsManager;

    public TablistUpdater() {
        plugin = LobbyManager.getInstance();
        messageManager = LobbyManager.getMessageManager();
        luckPermsManager = LobbyManager.getLuckPermsManager();
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    String prefix = luckPermsManager.getPlayerPrefix(player);
                    String header = messageManager.getTablistHeader(player, prefix);
                    String footer = messageManager.getTablistFooter(player, prefix);
                    String format = messageManager.getTablistFormat(player, prefix);

                    Tablist.sendToPlayer(player, header, footer);
                    player.setPlayerListName(format);
                }
            }
        }.runTaskTimer(plugin, 0, 20*5);
    }
}