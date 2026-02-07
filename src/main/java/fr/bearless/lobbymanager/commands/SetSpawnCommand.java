package fr.bearless.lobbymanager.commands;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.configs.BaseConfig;
import fr.bearless.lobbymanager.configs.MessageConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    private final BaseConfig baseConfig;
    private final MessageConfig messageConfig;

    public SetSpawnCommand() {
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

        if(!player.hasPermission(baseConfig.getSetSpawnCommandPermission())) {
            player.sendMessage(messageConfig.getSetSpawnCommandPermissionMessage());
            return true;
        }

        if(args.length != 0) {
            player.sendMessage(messageConfig.getSetSpawnCommandUsage());
            return true;
        }

        baseConfig.setSpawn(player.getLocation());
        player.sendMessage(messageConfig.getSetSpawnCommandSuccess());
        return true;
    }
}