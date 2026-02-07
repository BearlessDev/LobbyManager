package fr.bearless.lobbymanager.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.bearless.lobbymanager.LobbyManager;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;

public class UpdateChecker {
    private final LobbyManager plugin;
    private final int resourceId;
    @Getter private String latestVersion;
    @Getter private boolean updateAvailable;

    public UpdateChecker(LobbyManager plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
        this.updateAvailable = false;
    }

    public void checkForUpdate(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL("https://api.spigotmc.org/simple/0.2/index.php?action=getResource&id=" + resourceId).openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.readLine();

                JsonParser parser = new JsonParser();
                JsonObject json = (JsonObject) parser.parse(response);

                if(json.has("current_version")) {
                    latestVersion = json.get("current_version").getAsString();
                    String currentVersion = plugin.getDescription().getVersion();

                    if(isUpdateNeeded(currentVersion, latestVersion)) {
                        updateAvailable = true;
                    }
                }
                reader.close();

                if(consumer != null) {
                    Bukkit.getScheduler().runTask(plugin, () -> consumer.accept(latestVersion));
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private boolean isUpdateNeeded(String currentVersion, String fetchedVersion) {
        if(currentVersion.equalsIgnoreCase(fetchedVersion)) return false;

        String[] currentVer = currentVersion.split("\\.");
        String[] fetchedVer = fetchedVersion.split("\\.");

        int length = Math.max(currentVer.length, fetchedVer.length);
        for(int i = 0; i < length; i++) {
            int v1 = i < currentVer.length ? Integer.parseInt(currentVer[i]) : 0;
            int v2 = i < fetchedVer.length ? Integer.parseInt(fetchedVer[i]) : 0;

            if(v2 > v1) {
                return true;
            } else if(v2 < v1) {
                return false;
            }
        }
        return false;
    }
}