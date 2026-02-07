package fr.bearless.lobbymanager.utils;

import org.bukkit.entity.Player;

public class FormatText {
    private String newText;

    public FormatText(String text) {
        newText = text;
    }

    public FormatText withPlayer(Player player) {
        newText = newText.replaceAll("%player%", player.getDisplayName());
        return this;
    }

    public FormatText withPrefix(String prefix) {
        newText = newText.replaceAll("%prefix%", prefix);
        return this;
    }

    public FormatText withMessage(String message) {
        newText = newText.replaceAll("%message%", message);
        return this;
    }

    public FormatText withConfig(String configName) {
        newText = newText.replaceAll("%config%", configName);
        return this;
    }

    public String build() {
        return newText;
    }
}