package fr.bearless.lobbymanager.commands;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.configs.BaseConfig;
import fr.bearless.lobbymanager.configs.MessageConfig;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    private final BaseConfig baseConfig;
    private final MessageConfig messageConfig;

    public SpawnCommand() {
        baseConfig = LobbyManager.getBaseConfig();
        messageConfig = LobbyManager.getMessageConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(messageConfig.getOnlyPlayerCommand());
            return true;
        }
        Player player = (Player) sender;

        if(!player.hasPermission(baseConfig.getSpawnCommandPermission())) {
            player.sendMessage(messageConfig.getSpawnCommandPermissionMessage());
            return true;
        }

        if(args.length != 0) {
            player.sendMessage(messageConfig.getSpawnCommandUsage());
            return true;
        }

        Location spawnLoc = baseConfig.getSpawn();
        player.teleport(spawnLoc);
        player.sendMessage(messageConfig.getSpawnCommandSuccess());
        return true;
    }
}