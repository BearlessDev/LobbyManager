package fr.bearless.lobbymanager.commands;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.managers.configs.ConfigManager;
import fr.bearless.lobbymanager.managers.configs.MessageManager;
import fr.bearless.lobbymanager.utils.ConfigReader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyManagerCommand implements CommandExecutor {
    private final ConfigReader config;
    private final ConfigReader messageConfig;
    private final MessageManager messageManager;
    private final ConfigManager configManager;

    public LobbyManagerCommand() {
        config = LobbyManager.getBaseConfig();
        messageConfig = LobbyManager.getMessagesConfig();
        messageManager = LobbyManager.getMessageManager();
        configManager = LobbyManager.getConfigManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("spawn")) {
            handleSpawn(sender, args);
            return true;
        }

        if(command.getName().equalsIgnoreCase("setspawn")) {
            handleSetSpawn(sender, args);
            return true;
        }

        if(command.getName().equalsIgnoreCase("lobbymanager")) {
            if(args.length == 0) {
                sender.sendMessage(messageManager.getCommandGlobalUsage());
                return true;
            }

            switch(args[0].toLowerCase()) {
                case "spawn":
                    handleSpawn(sender, args);
                    break;
                case "setspawn":
                    handleSetSpawn(sender, args);
                    break;
                case "reload":
                    handleReload(sender, args);
                    break;
                default:
                    sender.sendMessage(messageManager.getCommandGlobalUsage());
                    break;
            }
        }

        return true;
    }

    private void handleSpawn(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(messageManager.getCommandNotForConsole());
            return;
        }

        Player player = (Player) sender;

        if(!player.hasPermission(configManager.getSpawnCommandPermission())) {
            player.sendMessage(messageManager.getSpawnCommandPermissionMessage());
            return;
        }

        if(args.length > 1) {
            player.sendMessage(messageManager.getSpawnCommandUsage());
            return;
        }

        String spawnWorld = configManager.getSpawnWorld();
        double spawnX = configManager.getSpawnX();
        double spawnY = configManager.getSpawnY();
        double spawnZ = configManager.getSpawnZ();
        float spawnYaw = configManager.getSpawnYaw();
        float spawnPitch = configManager.getSpawnPitch();

        Location spawnLoc = new Location(Bukkit.getWorld(spawnWorld), spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);

        player.teleport(spawnLoc);
        player.sendMessage(messageManager.getSpawnCommandSuccess());
    }

    private void handleSetSpawn(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(messageManager.getCommandNotForConsole());
            return;
        }

        Player player = (Player) sender;

        if(!player.hasPermission(configManager.getSetSpawnCommandPermission())) {
            player.sendMessage(messageManager.getSetSpawnCommandPermissionMessage());
            return;
        }

        if(args.length > 1) {
            player.sendMessage(messageManager.getSetSpawnCommandUsage());
            return;
        }

        String world = player.getLocation().getWorld().getName();
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();

        configManager.setWorldSpawn(world, x, y, z, yaw, pitch);
        player.sendMessage(messageManager.getSetSpawnCommandSuccess());
    }

    private void handleReload(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(!player.hasPermission(configManager.getReloadCommandPermission())) {
                player.sendMessage(messageManager.getReloadCommandPermissionMessage());
                return;
            }
        }

        if(args.length != 2) {
            sender.sendMessage(messageManager.getReloadCommandUsage());
            return;
        }

        switch(args[1].toLowerCase()) {
            case "all":
                config.reloadConfig();
                messageConfig.reloadConfig();
                sender.sendMessage(messageManager.getReloadCommandReloadAll());
                break;
            case "config":
                config.reloadConfig();
                sender.sendMessage(messageManager.getReloadCommandReloadConfig("config.yml"));
                break;
            case "message":
                messageConfig.reloadConfig();
                sender.sendMessage(messageManager.getReloadCommandReloadConfig("messages.yml"));
                break;
            default:
                sender.sendMessage(messageManager.getReloadCommandUsage());
                break;
        }
    }
}