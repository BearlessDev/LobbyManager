package fr.bearless.lobbymanager.commands;

import fr.bearless.lobbymanager.LobbyManager;
import fr.bearless.lobbymanager.configs.BaseConfig;
import fr.bearless.lobbymanager.configs.MessageConfig;
import fr.bearless.lobbymanager.utils.FormatText;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReloadCommand implements TabExecutor {
    private final BaseConfig baseConfig;
    private final MessageConfig messageConfig;

    public ReloadCommand() {
        baseConfig = LobbyManager.getBaseConfig();
        messageConfig = LobbyManager.getMessageConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(baseConfig.getReloadCommandPermission())) {
            sender.sendMessage(messageConfig.getReloadCommandPermissionMessage());
            return true;
        }

        if(args.length != 1) {
            sender.sendMessage(messageConfig.getReloadCommandUsage());
            return true;
        }

        switch(args[0].toLowerCase()) {
            case "all":
                baseConfig.getBukkitConfiguration().load();
                messageConfig.getBukkitConfiguration().load();
                sender.sendMessage(messageConfig.getReloadAllCommand());
                break;
            case "config":
                baseConfig.getBukkitConfiguration().load();
                String configReload = new FormatText(messageConfig.getReloadConfigCommand()).withConfig("config.yml").build();
                sender.sendMessage(configReload);
                break;
            case "message":
                messageConfig.getBukkitConfiguration().load();
                String messageReload = new FormatText(messageConfig.getReloadConfigCommand()).withConfig("messages.yml").build();
                sender.sendMessage(messageReload);
                break;
            default:
                sender.sendMessage(messageConfig.getReloadCommandUsage());
                break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> reloadArgs = new ArrayList<>();
        reloadArgs.add("all");
        reloadArgs.add("config");
        reloadArgs.add("message");

        if(args.length != 1) return Collections.emptyList();

        return reloadArgs;
    }
}