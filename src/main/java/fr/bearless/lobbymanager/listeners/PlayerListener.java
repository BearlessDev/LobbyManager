package fr.bearless.lobbymanager.listeners;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.configs.BaseConfig;
import fr.bearless.lobbymanager.configs.MessageConfig;
import fr.bearless.lobbymanager.managers.LuckPermsManager;
import fr.bearless.lobbymanager.utils.FormatText;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
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
    private final LobbyManager plugin;
    private final BaseConfig baseConfig;
    private final MessageConfig messageConfig;
    private final LuckPermsManager luckPermsManager;

    public PlayerListener() {
        plugin = LobbyManager.getInstance();
        baseConfig = LobbyManager.getBaseConfig();
        messageConfig = LobbyManager.getMessageConfig();
        luckPermsManager = LobbyManager.getLuckPermsManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String prefix = luckPermsManager.getPlayerPrefix(player);

        // Set the custom Join Message.
        if(baseConfig.getEnableCustomJoinMessage()) {
            String joinMessage = new FormatText(messageConfig.getCustomJoinMessage())
                    .withPrefix(prefix).withPlayer(player).build();
            e.setJoinMessage(joinMessage);
        }

        // Teleport Player to the spawn if set in "config.yml"
        if(baseConfig.getSendToSpawnOnJoin()) {
            World world = baseConfig.getSpawn().getWorld();
            double x = baseConfig.getSpawn().getX();
            double y = baseConfig.getSpawn().getY();
            double z = baseConfig.getSpawn().getZ();
            float yaw = baseConfig.getSpawn().getYaw();
            float pitch = baseConfig.getSpawn().getPitch();

            Location spawnLoc = new Location(world, x, y, z, yaw, pitch);
            player.teleport(spawnLoc);
        }

        // Set Player Gamemode
        GameMode mode = baseConfig.getPlayerGamemode();
        player.setGameMode(mode);

        // Set Player Level and Exp on Join
        player.setLevel(baseConfig.getPlayerLevelOnJoin());
        player.setExp((float) baseConfig.getPlayerExpOnJoin());

        // Set Player Heart and Food value
        player.setMaxHealth(baseConfig.getPlayerMaxHealth());
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(baseConfig.getPlayerFoodLevel());

        // Notify the Player that a new Plugin Version is available
        if(player.hasPermission(baseConfig.getNotifyNewUpdatePermission())) {
            String fetchVersion = plugin.getUpdateChecker().getLatestVersion();
            if(plugin.getUpdateChecker().isNewerVersion(fetchVersion)) {
                player.sendMessage(messageConfig.getNewVersionAvailableMessage());
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String prefix = luckPermsManager.getPlayerPrefix(player);

        if(baseConfig.getEnableCustomQuitMessage()) {
            String quitMessage = new FormatText(messageConfig.getCustomQuitMessage())
                    .withPrefix(prefix).withPlayer(player).build();
            e.setQuitMessage(quitMessage);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        if(!player.hasPermission(baseConfig.getBlockBreakPermission())) {
            player.sendMessage(messageConfig.getBlockBreakPermissionMessage());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        if(!player.hasPermission(baseConfig.getBlockPlacePermission())) {
            player.sendMessage(messageConfig.getBlockPlacePermissionMessage());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String prefix = luckPermsManager.getPlayerPrefix(player);
        String message = e.getMessage();
        String format = new FormatText(messageConfig.getChatFormat())
                .withPrefix(prefix).withPlayer(player).withMessage(message).build();
        e.setFormat(format);
    }

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent e) {
        if(!baseConfig.getCanPlayerPvP()) {
            e.setCancelled(true);
            if(e.getEntity() instanceof Player) {
                e.getEntity().sendMessage(messageConfig.getNoPvPMessage());
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        EntityDamageEvent.DamageCause damageCause = e.getCause();

        if(!baseConfig.getPlayerCanTakeFallDamage()) {
            if(damageCause == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        if(!baseConfig.getPlayerCanLostFood()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if(baseConfig.getPreventPlayerVoidFallEnabled()) {
            if(player.getLocation().getY() <= baseConfig.getYLevelTarget()) {
                World world = baseConfig.getSpawn().getWorld();
                double x = baseConfig.getSpawn().getX();
                double y = baseConfig.getSpawn().getY();
                double z = baseConfig.getSpawn().getZ();
                float yaw = baseConfig.getSpawn().getYaw();
                float pitch = baseConfig.getSpawn().getPitch();

                Location spawnLoc = new Location(world, x, y, z, yaw, pitch);
                player.teleport(spawnLoc);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        World world = baseConfig.getSpawn().getWorld();
        double x = baseConfig.getSpawn().getX();
        double y = baseConfig.getSpawn().getY();
        double z = baseConfig.getSpawn().getZ();
        float yaw = baseConfig.getSpawn().getYaw();
        float pitch = baseConfig.getSpawn().getPitch();

        Location spawnLoc = new Location(world, x, y, z, yaw, pitch);
        e.getPlayer().teleport(spawnLoc);
    }

}