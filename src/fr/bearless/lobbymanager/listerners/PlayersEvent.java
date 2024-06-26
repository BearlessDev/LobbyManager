package fr.bearless.lobbymanager.listerners;

import fr.bearless.lobbymanager.Main;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayersEvent implements Listener {

    final Main plugin;

    public PlayersEvent(Main main){
        this.plugin = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
        String prefix = user.getCachedData().getMetaData().getPrefix().replace("&", "§");

        if(prefix == null) return;

        if(plugin.isDefaultJoinMessageDisabled()){
            e.setJoinMessage(plugin.getCustomJoinMessage(player));
        }

        player.setHealth(plugin.getConfig().getInt("player.player_health_level"));
        player.setFoodLevel(plugin.getConfig().getInt("player.player_food_level"));

        player.setLevel(plugin.getConfig().getInt("player.player_level"));

        String world = plugin.getConfig().getString("world.spawn_world_name");
        double spawnX = plugin.getConfig().getDouble("world.spawn_X");
        double spawnY = plugin.getConfig().getDouble("world.spawn_Y");
        double spawnZ = plugin.getConfig().getDouble("world.spawn_Z");
        int spawnYaw = plugin.getConfig().getInt("world.spawn_Yaw");
        int spawnPitch = plugin.getConfig().getInt("world.spawn_Pitch");
        Location loc = new Location(Bukkit.getWorld(world), spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);

        if(plugin.getConfig().getBoolean("world.enable_join_player_spawn_loc")) {
            player.teleport(loc);
        }

        if(plugin.getConfig().getBoolean("player.enable_player_gamemode")){
            player.setGameMode(GameMode.valueOf(plugin.getConfig().getString("player.set_player_gamemode").toUpperCase()));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        if(plugin.isDefaultQuitMessageDisabled()){
            e.setQuitMessage(plugin.getConfig().getString("server.custom_quit_message")
                    .replace("&", "§")
                    .replaceAll("%player%", player.getName()));
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();

        String world = plugin.getConfig().getString("world.spawn_world_name");
        double spawnX = plugin.getConfig().getDouble("world.spawn_X");
        double spawnY = plugin.getConfig().getDouble("world.spawn_Y");
        double spawnZ = plugin.getConfig().getDouble("world.spawn_Z");
        float spawnYaw = plugin.getConfig().getInt("world.spawn_Yaw");
        float spawnPitch = plugin.getConfig().getInt("world.spawn_Pitch");
        Location loc = new Location(Bukkit.getWorld(world), spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);

        if(player.getLocation().getY() <= plugin.getConfig().getInt("servers.teleport_onFall")){
            player.teleport(loc);
        }
    }
    
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.setDeathMessage("");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        EntityDamageEvent.DamageCause cause = e.getCause();

        if(plugin.getConfig().getBoolean("player.disable_fall_damage")){
            if(cause == EntityDamageEvent.DamageCause.FALL){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent e){
        EntityDamageEvent.DamageCause cause = e.getCause();

        if(plugin.getConfig().getBoolean("world.disable_pvp")){
            if(cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onHealthChange(EntityRegainHealthEvent e){
        if(plugin.getConfig().getBoolean("player.disable_fall_damage")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e){

        if(plugin.getConfig().getBoolean("player.disable_food_loss")){
            e.setCancelled(true);
        }
    }
}
