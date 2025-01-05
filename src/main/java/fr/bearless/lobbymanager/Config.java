package fr.bearless.lobbymanager;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Config{
	
	static FileConfiguration config = LobbyManager.getInstance().getConfig();
	
	public static class GetString{
		public static String getCustomJoinMessage(Player player){
	    	return ChatColor.translateAlternateColorCodes('&', config.getString("server.custom_join_message").replaceAll("%player%", player.getName()));
	    }
	    public static String getCustomQuitMessage(Player player){
	    	return ChatColor.translateAlternateColorCodes('&', config.getString("server.custom_quit_message").replaceAll("%player%", player.getName()));
	    }
	    public static String getReloadPermission(){
	    	return config.getString("commands.reload.permission");
	    }
	    public static String getReloadPermissionMessage(){
	    	return config.getString("commands.reload.permission_message");
	    }
	    public static String getReloadMessage(){
	    	return config.getString("commands.reload.reload_message");
	    }
	    public static String getSpawnCommandMessage(){
	    	return config.getString("commands.spawn.spawn_message");
	    }
	    public static String getSetSpawnPermission(){
	    	return config.getString("commands.setspawn.permission");
	    }
	    public static String getSetSpawnPermissionMessage(){
	    	return config.getString("commands.setspawn.permission_message");
	    }
	    public static String getSetSpawnMessage(){
	    	return config.getString("commands.setspawn.setspawn_message");
	    }
	    public static String getOnlyPlayerMessage(){
	    	return config.getString("commands.only_player");
	    }
	    public static String getReloadUsageMessage(){
	    	return config.getString("usage_message.reload");
	    }
	    public static String getSetSpawnUsageMessage(){
	    	return config.getString("usage_message.setspawn");
	    }
	    public static String getSpawnWorldName(){
	    	return config.getString("server.spawn_world_name");
	    }
	    public static String getPlayerGamemode(){
	    	return config.getString("player.set_player_gamemode");
	    }
	    public static String getTablistHeader(Player player){
	    	return config.getString("player.tablist_header").replaceAll("%player%", player.getName());
	    }
	    public static String getTablistFooter(Player player){
	    	return config.getString("player.tablist_footer").replaceAll("%player%", player.getName());
	    }
	    public static String getTablistFormat(Player player){
	    	return config.getString("player.tablist_format").replaceAll("%player%", player.getName());
	    }
	    public static String getBlockBreakPermission(){
	    	return config.getString("world.block_break.permission");
	    }
	    public static String getBlockBreakPermissionMessage(){
	    	return config.getString("world.block_break.permission_message");
	    }
	    public static String getBlockPlacePermission(){
	    	return config.getString("world.block_place.permission");
	    }
	    public static String getBlockPlacePermissionMessage(){
	    	return config.getString("world.block_place.permission_message");
	    }
	    public static String getPvpMessage(){
	    	return config.getString("server.pvp_message");
	    }
	}
	
	public static class GetBoolean{
		public static boolean isDefaultJoinMessageDisabled(){
	        return config.getBoolean("server.disable_default_join_message");
	    }
	    public static boolean isDefaultQuitMessageDisabled(){
	        return config.getBoolean("server.disable_default_quit_message");
	    }
	    public static boolean isPvpDisabled(){
	    	return config.getBoolean("server.disable_pvp");
	    }
	    public static boolean isEntitySpawnDisabled(){
	    	return config.getBoolean("server.disable_entity_spawn");
	    }
	    public static boolean isWeatherChangeDisabled(){
	    	return config.getBoolean("server.disable_weather_change");
	    }
	    public static boolean isPlayerJoinSpawnLocEnabled(){
	    	return config.getBoolean("server.enable_join_player_spawn_loc");
	    }
	    public static boolean isFallDamageDisabled(){
	    	return config.getBoolean("player.disable_fall_damage");
	    }
	    public static boolean isFoodLossDisabled(){
	    	return config.getBoolean("player.disable_food_loss");
	    }
	    public static boolean isPlayerGamemodeEnabled(){
	    	return config.getBoolean("player.enable_player_gamemode");
	    }
	    public static boolean isBlockBreakEnabled(){
	    	return config.getBoolean("world.block_break.enable");
	    }
	    public static boolean isBlockPlaceEnabled(){
	    	return config.getBoolean("world.block_place.enable");
	    }
	}
	
	public static class GetInt{
		public static double getSpawnX(){
			return config.getDouble("server.spawn_x");
		}
		public static double getSpawnY(){
			return config.getDouble("server.spawn_y");
		}
		public static double getSpawnZ(){
			return config.getDouble("server.spawn_z");
		}
		public static float getSpawnYaw(){
			return config.getInt("server.spawn_yaw");
		}
		public static float getSpawnPitch(){
			return config.getInt("server.spawn_pitch");
		}
		public static int getTeleportOnFall(){
			return config.getInt("server.teleport_onfall");
		}
		public static int getPlayerHealthLevel(){
			return config.getInt("player.player_health_level");
		}
		public static int getPlayerFoodLevel(){
			return config.getInt("player.player_food_level");
		}
		public static int getPlayerLevel(){
			return config.getInt("player.player_level");
		}
		public static float getPlayerExp(){
			return config.getInt("player.player_exp");
		}
	}

	public static class SetString{
		public static void setSpawnWorldName(Player player){
			config.set("server.spawn_world_name", player.getWorld().getName());
		}
	}

	public static class SetInt{
		public static void setSpawnX(Player player){
			config.set("server.spawn_x", player.getLocation().getX());
		}
		public static void setSpawnY(Player player){
			config.set("server.spawn_y", player.getLocation().getY());
		}
		public static void setSpawnZ(Player player){
			config.set("server.spawn_z", player.getLocation().getZ());
		}
		public static void setSpawnYaw(Player player){
			config.set("server.spawn_yaw", player.getLocation().getYaw());
		}
		public static void setSpawnPitch(Player player){
			config.set("server.spawn_pitch", player.getLocation().getPitch());
		}
	}
}