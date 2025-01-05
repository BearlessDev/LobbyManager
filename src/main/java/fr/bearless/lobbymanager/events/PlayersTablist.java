package fr.bearless.lobbymanager.events;

import fr.bearless.lobbymanager.Config.GetString;
import fr.bearless.lobbymanager.tables.Tablist;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayersTablist implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        
        String header = ChatColor.translateAlternateColorCodes('&', GetString.getTablistHeader(player));
        String footer = ChatColor.translateAlternateColorCodes('&', GetString.getTablistFooter(player));

        String format = ChatColor.translateAlternateColorCodes('&', GetString.getTablistFormat(player));

        Tablist.sendToPlayer(player, header, footer);

        player.setPlayerListName(format);
    }
}