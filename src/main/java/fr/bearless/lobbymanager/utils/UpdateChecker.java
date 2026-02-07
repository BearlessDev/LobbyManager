package fr.bearless.lobbymanager.utils;

import fr.bearless.lobbymanager.LobbyManager;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private final LobbyManager plugin;
    private final int resourceId;
    @Getter private String latestVersion;

    public UpdateChecker(LobbyManager plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void fetchVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try(InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
                Scanner scanner = new Scanner(inputStream)) {
                if(scanner.hasNext()) {
                    this.latestVersion = scanner.next();
                    consumer.accept(this.latestVersion);
                }
            } catch(IOException e) {
                this.plugin.getLogger().warning("Unable to check for newest update: " + e.getMessage());
            }
        });
    }

    public boolean isNewerVersion(String lastversion) {
        if(lastversion == null || lastversion.isEmpty()) {
            return false;
        }

        String currentVersion = this.plugin.getDescription().getVersion();
        if(currentVersion.equalsIgnoreCase(lastversion)) {
            return false;
        }

        String[] currentParts = currentVersion.split("\\.");
        String[] lastParts = lastversion.split("\\.");
        int length = Math.max(currentParts.length, lastParts.length);

        for(int i = 0; i < length; i++) {
            int v1 = i < currentParts.length ? Integer.parseInt(currentParts[i].replaceAll("[^0-9]", "")) : 0;
            int v2 = i < lastParts.length ? Integer.parseInt(lastParts[i].replaceAll("[^0-9]", "")) : 0;

            if(v1 < v2) {
                return true;
            }
            if(v1 > v2) {
                return false;
            }
        }
        return false;
    }
}