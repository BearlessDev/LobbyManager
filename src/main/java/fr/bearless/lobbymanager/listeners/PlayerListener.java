package fr.bearless.lobbymanager.listeners;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.managers.configs.ConfigManager;
import fr.bearless.lobbymanager.managers.configs.MessageManager;
import fr.bearless.lobbymanager.managers.LuckPermsManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {
    private final ConfigManager configManager;
    private final MessageManager messageManager;
    private final LuckPermsManager luckPermsManager;

    public PlayerListener() {
        configManager = LobbyManager.getConfigManager();
        messageManager = LobbyManager.getMessageManager();
        luckPermsManager = LobbyManager.getLuckPermsManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        // Set the custom Join Message.
        if(configManager.enableCustomJoinMessage()) {
            e.setJoinMessage(messageManager.getCustomJoinMessage(player, luckPermsManager.getPlayerPrefix(player)));
        }

        // Teleport Player to the spawn if set in "config.yml"
        if(configManager.sendPlayerAtSpawnOnJoin()) {
            String spawnWorld = configManager.getSpawnWorld();
            double spawnX = configManager.getSpawnX();
            double spawnY = configManager.getSpawnY();
            double spawnZ = configManager.getSpawnZ();
            float spawnYaw = configManager.getSpawnYaw();
            float spawnPitch = configManager.getSpawnPitch();

            Location spawnLoc = new Location(Bukkit.getWorld(spawnWorld), spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);

            player.teleport(spawnLoc);
        }

        // Set Player Gamemode
        GameMode mode = GameMode.valueOf(configManager.getGamemode().toUpperCase());
        player.setGameMode(mode);

        // Set Player Level and Exp on Join
        player.setLevel(configManager.getLevelOnJoin());
        player.setExp(configManager.getExpOnJoin());

        // Set Player Heart and Food value
        player.setMaxHealth(configManager.getPlayerMaxHealth());
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(configManager.getPlayerFoodLevel());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if(configManager.enableCustomQuitMessage()) {
            e.setQuitMessage(messageManager.getCustomQuitMessage(player, luckPermsManager.getPlayerPrefix(player)));
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        if(!player.hasPermission(configManager.getBlockBreakPermission())) {
            player.sendMessage(messageManager.getBlockBreakPermissionMessage());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        if(!player.hasPermission(configManager.getBlockPlacePermission())) {
            player.sendMessage(messageManager.getBlockPlacePermissionMessage());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String prefix = luckPermsManager.getPlayerPrefix(player);
        String message = e.getMessage();
        String format = messageManager.getChatFormat(player, prefix).replaceAll("%message%", message);
        e.setFormat(format);
    }

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent e) {
        if(!configManager.canPvP()) {
            e.setCancelled(true);
            if(e.getEntity() instanceof Player) {
                e.getEntity().sendMessage(messageManager.getNoPvPMessage());
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        EntityDamageEvent.DamageCause damageCause = e.getCause();

        if(!configManager.isFallDamageEnabled()) {
            if(damageCause == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        if(!configManager.isFoodLossEnabled()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if(configManager.teleportToSpawnIfFall()) {
            if(player.getLocation().getY() <= configManager.teleportIfYPosUnder()) {
                String spawnWorld = configManager.getSpawnWorld();
                double spawnX = configManager.getSpawnX();
                double spawnY = configManager.getSpawnY();
                double spawnZ = configManager.getSpawnZ();
                float spawnYaw = configManager.getSpawnYaw();
                float spawnPitch = configManager.getSpawnPitch();

                Location spawnLoc = new Location(Bukkit.getWorld(spawnWorld), spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);

                player.teleport(spawnLoc);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        String spawnWorld = configManager.getSpawnWorld();
        double spawnX = configManager.getSpawnX();
        double spawnY = configManager.getSpawnY();
        double spawnZ = configManager.getSpawnZ();
        float spawnYaw = configManager.getSpawnYaw();
        float spawnPitch = configManager.getSpawnPitch();

        Location spawnLoc = new Location(Bukkit.getWorld(spawnWorld), spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);

        e.setRespawnLocation(spawnLoc);
    }

}