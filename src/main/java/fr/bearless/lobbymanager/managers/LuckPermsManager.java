package fr.bearless.lobbymanager.managers;

import fr.bearless.lobbymanager.LobbyManager;
import net.luckperms.api.LuckPerms;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Optional;

public class LuckPermsManager {
    private LuckPerms luckPerms;
    private boolean isEnabled = false;

    public LuckPermsManager() {
        initLuckPerms();
    }

    private void initLuckPerms() {
        if(!isLuckPermsPresent()) {
            LobbyManager.getInstance().getLogger().info("LuckPerms is not detected, this plugin will not using it.");
            return;
        }

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

        if(provider != null) {
            this.luckPerms = provider.getProvider();
            this.isEnabled = true;
            LobbyManager.getInstance().getLogger().info("LuckPerms detected, this plugin will using it.");
        }
    }

    private boolean isLuckPermsPresent() {
        return Bukkit.getPluginManager().getPlugin("LuckPerms") != null;
    }

    public boolean isLuckPermsEnabled() {
        return isEnabled && luckPerms != null;
    }

    public String getPlayerPrefix(Player player) {
        if(!isLuckPermsEnabled()) return "";

        return Optional.ofNullable(
                luckPerms.getPlayerAdapter(Player.class).getUser(player)
                        .getCachedData()
                        .getMetaData()
                        .getPrefix()
        ).map(prefix -> ChatColor.translateAlternateColorCodes('&', prefix)).orElse("");
    }

}