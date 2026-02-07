# LobbyManager

[![Version](https://img.shields.io/badge/Version-v3.0.0-blue.svg)](https://github.com/Bearless/LobbyManager)
[![Spigot API](https://img.shields.io/badge/API-Spigot%201.8.8-orange.svg)](https://www.spigotmc.org/)

**LobbyManager** is a comprehensive Spigot plugin designed to easily and efficiently manage your lobby server. It offers a wide range of customizable features to enhance the player experience from the moment they connect.

---

## ➤ Features

- **📍 Spawn Management:**
  - Define the lobby's spawn point with a simple command (`/setspawn`).
  - Teleport players to the spawn at any time (`/spawn`).
  - Automatically teleport to spawn on join.
  - Teleport to spawn if a player falls into the void.

- **👤 Player Management:**
  - Fully customizable join and quit messages.
  - Set the default game mode (Adventure, Survival, etc.).
  - Disable fall damage and hunger loss.
  - Manage PvP (enabled or disabled).
  - Customize experience level, health, and hunger on join.

- **🌍 World Management:**
  - Disable weather changes.
  - Disable the spawning of hostile or passive mobs.

- **🎨 Advanced Customization:**
  - **Tablist:** Fully customizable header and footer with support for colors and multiple lines.
  - **Chat:** Customizable chat format to integrate prefixes and suffixes.
  - **Messages:** All plugin messages can be modified in the `messages.yml` file.

- **🔐 Integration & Permissions:**
  - Integration with **LuckPerms** to display player prefixes in the chat and tablist.
  - A comprehensive permission system to control access to commands and actions (breaking/placing blocks).

---

## ➤ Installation

1. Download the latest version of the plugin.
2. Place the `.jar` file into your server's `plugins` folder.
3. Restart your server.
4. Configure the `config.yml` and `messages.yml` files to your liking.

---

## ➤ Commands and Permissions

| Command                                             | Description                                     | Permission                               |
|-----------------------------------------------------| ----------------------------------------------- | ---------------------------------------- |
| `/lobbymanager-spawn` `/lm-spawn` `/spawn`          | Teleports the player to the spawn.              | `lobbymanager.command.spawn`             |
| `/lobbymanager-setspawn` `/lm-setspawn` `/setspawn` | Sets the spawn to the player's current location.| `lobbymanager.command.setspawn`          |
| `/lobbymanager-reload <all/config/message>`          | Reloads the configuration files.                | `lobbymanager.command.reload`            |
| Break blocks                                        | Allows the player to break blocks.              | `lobbymanager.block.break`               |
| Place blocks                                        | Allows the player to place blocks.              | `lobbymanager.block.place`               |

---

## ➤ Configuration

The plugin comes with two main configuration files:

### `config.yml`
This file controls the plugin's core settings.

```yaml
# Spawn point configuration
spawn:
  world: world
  x: 0.0
  y: 100.0
  z: 0.0
  # ...

# Player settings on join
player:
  sendToSpawnAtJoin: true
  gamemode: ADVENTURE
  canPvP: false
  # ...

# World settings
world:
  canWeatherChange: false
  canMobSpawn: false
  # ...

# Permission nodes
permissions:
  block:
    break: "lobbymanager.block.break"
    place: "lobbymanager.block.place"
  # ...
```

### `messages.yml`
This file allows you to customize all messages visible to players. It supports color codes with the `&` character.

```yaml
# Command messages
commands:
  globalUsage: "&c&lUSAGE: &c/lobbymanager <spawn|setspawn|reload>"
  # ...

# Join/quit messages
player:
  customJoinMessage: "%prefix% %player% &ejoined the server."
  customQuitMessage: "%prefix% %player% &eleft the server."
  chatFormat: "%prefix%%player% &8» &r%message%"
  # ...

# Tablist configuration
tablist:
  header: "&3&lLobbyManager\n&7Welcome, &a%player%&7!"
  footer: "&7Discord: discord.gg/example"
  format: "%prefix%%player%"
# ...
```

---

## ➤ Placeholders

You can use the following placeholders in the `messages.yml` file to display dynamic information:

- `%player%`: Displays the player's name.
- `%prefix%`: Displays the player's prefix (requires LuckPerms).
- `%message%`: Displays the player's message in the chat format.
- `%config%`: Used in reload messages to specify the reloaded file.

---

## ➤ Support

For any issues or suggestions, feel free to open an *issue* on the project's GitHub page.
