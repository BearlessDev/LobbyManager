package fr.bearless.lobbymanager.events;

import fr.bearless.lobbymanager.Config.GetBoolean;
import fr.bearless.lobbymanager.Config.GetInt;
import fr.bearless.lobbymanager.Config.GetString;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayersEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        if(GetBoolean.isDefaultJoinMessageDisabled()){
            e.setJoinMessage(GetString.getCustomJoinMessage(player));
        }

        player.setHealth(GetInt.getPlayerHealthLevel());
        player.setFoodLevel(GetInt.getPlayerFoodLevel());

        player.setExp((float) GetInt.getPlayerExp());
        player.setLevel(GetInt.getPlayerLevel());

        String world = GetString.getSpawnWorldName();
        double spawnX = GetInt.getSpawnX();
        double spawnY = GetInt.getSpawnY();
        double spawnZ = GetInt.getSpawnZ();
        float spawnYaw = (float) GetInt.getSpawnYaw();
        float spawnPitch = (float) GetInt.getSpawnPitch();
        Location loc = new Location(Bukkit.getWorld(world), spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);

        if(GetBoolean.isPlayerJoinSpawnLocEnabled()){
            player.teleport(loc);
        }

        if(GetBoolean.isPlayerGamemodeEnabled()){
            player.setGameMode(GameMode.valueOf(GetString.getPlayerGamemode().toUpperCase()));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        if(GetBoolean.isDefaultQuitMessageDisabled()){
            e.setQuitMessage(GetString.getCustomQuitMessage(player));
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();

        String world = GetString.getSpawnWorldName();
        double spawnX = GetInt.getSpawnX();
        double spawnY = GetInt.getSpawnY();
        double spawnZ = GetInt.getSpawnZ();
        float spawnYaw = (float) GetInt.getSpawnYaw();
        float spawnPitch = (float) GetInt.getSpawnPitch();
        Location loc = new Location(Bukkit.getWorld(world), spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);

        if(player.getLocation().getY() <= GetInt.getTeleportOnFall()){
            player.teleport(loc);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        EntityDamageEvent.DamageCause cause = e.getCause();

        if(GetBoolean.isFallDamageDisabled()){
            if(cause == EntityDamageEvent.DamageCause.FALL){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent e){
        EntityDamageEvent.DamageCause cause = e.getCause();

        if(GetBoolean.isPvpDisabled()){
            if(cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK){
            	if(e.getEntityType() == EntityType.PLAYER){
                	Player player = (Player) e.getEntity();
                	
                	e.setCancelled(true);
                	
                	player.sendMessage(ChatColor.translateAlternateColorCodes('&', GetString.getPvpMessage()));
                }
            }
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e){
        if(GetBoolean.isFoodLossDisabled()){
            e.setCancelled(true);
        }
    }
}