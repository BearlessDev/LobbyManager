# LobbyManager v2

### Description:
LobbyManager is a plugin that allows you to easily configure your server lobby just through a single configuration file that is easy to understand and modify.

## Commands:
- /lm-reload or /lobbymanager-reload:
    - Allows you to reload the configuration file after modifying it to avoid restarting the entire server.

- /lm-spawn, /lobbymanager-spawn or /spawn:
    - Allows you to go back to the spawn set on the config.yml file.

- /lm-setspawn, /lobbymanager-setspawn, /setspawn:
    - Allow you to set the Spawn Location directly in the Config.yml file via a Command.

## Permissions:
- Reload Command:
    - Default permission is: "lobbymanager.commands.reload" but you can change it on the config.yml file.

- Spawn Command:
    - This command don't require permission.

- SetSpawn Command:
    - Default permission is: "lobbymanager.commands.setspawn" but you can change it on the config.yml file.

## Configuration file:
Here the config file of the plugin:

```Yaml
#    _          _     _           __  __
#   | |    ___ | |__ | |__  _   _|  \/  | __ _ _ __   __ _  __ _  ___ _ __
#   | |   / _ \| '_ \| '_ \| | | | |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '__|
#   | |__| (_) | |_) | |_) | |_| | |  | | (_| | | | | (_| | (_| |  __/ |
#   |_____\___/|_.__/|_.__/ \__, |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_|
#                           |___/                          |___/           
# By Bearless_
# Version: 2

# Placeholders:
# %player% --> Dynamically represents the player's name in messages.

commands:
  reload:
    permission: "lobbymanager.commands.reload" # Permission required to use the /lm-reload command.
    permission_message: "&cYou don't have the required permission." # Message displayed if the player lacks permission.
    reload_message: "&aThe configuration file has been reloaded." # Message displayed after a successful reload.
    
  spawn:
    spawn_message: "&aYou have been teleported to the spawn." # Message after teleporting to spawn.
    
  setspawn:
    permission: "lobbymanager.commands.setspawn" # Permission required to set the spawn location.
    permission_message: "&cYou don't have the required permission." # Message displayed if the player lacks permission.
    setspawn_message: "&aThe spawn has been set." # Message displayed after setting the spawn.
    
  only_player: "Only Player can execute this command" # Message displayed when a player-only command is run by the console (color codes are not supported here).

usage_message: # Error messages for incorrect command usage.
  reload: "&c&lIncorrect Usage: &c/lm-reload &c&lor &c/lobbymanager-reload" # Message displayed when /lm-reload is misused.
  setspawn: "&c&lIncorrect Usage: &c/lm-setspawn &c&lor &c/lobbymanager-setspawn &c&lor &c/setspawn" # Message displayed when /lm-setspawn is misused.

server: # General server settings and behaviors.
  disable_default_join_message: true # Disables the default join message ("Bearless joined the game").
  disable_default_quit_message: true # Disables the default quit message ("Bearless left the game").

  custom_join_message: "%player% &ajoin the lobby!" # Custom join message to replace the default.
  custom_quit_message: "%player% &cquit the lobby!" # Custom quit message to replace the default.

  disable_pvp: true # Prevents players from engaging in PvP combat.
  disable_entity_spawn: true # Prevents both passive (e.g., cows) and hostile (e.g., zombies) entities from spawning.
  disable_weather_change: true # Prevents weather changes (e.g., rain or storms).

  pvp_message: "&cYou can't pvp here." # Message displayed when PvP is disabled.

  enable_join_player_spawn_loc: true # Teleports players to a predefined spawn location upon joining.

  spawn_world_name: "world" # The name of the world where the spawn location is set.
  spawn_x: 0.5 # X-coordinate of the spawn location.
  spawn_y: 65 # Y-coordinate (vertical position) of the spawn location.
  spawn_z: 0.5 # Z-coordinate of the spawn location.
  spawn_yaw: 180 # The direction the player faces (horizontal rotation) when spawning.
  spawn_pitch: 0 # Vertical tilt of the player's view when spawning.

  teleport_onfall: 5 # Teleports the player to spawn if they fall below this Y-coordinate.

player: # Player-specific settings and restrictions.
  disable_fall_damage: true # Prevents players from taking fall damage.
  disable_food_loss: true # Prevents the depletion of the player's hunger bar.

  player_health_level: 20 # Sets the player's health to this value upon joining.
  player_food_level: 20 # Sets the player's hunger level to this value upon joining.

  player_level: 2025 # Assigns a custom level to the player.
  player_exp: 0.5 # The amount of experience (between 0 and 1) the player will have when joining the server.

  enable_player_gamemode: true # Allows setting a specific gamemode upon joining.
  set_player_gamemode: "adventure" # The gamemode applied to the player (e.g., survival, adventure, etc.).

  tablist_header: "&aThis is a header\n &ewith a new line and your name: §a%player%" # The header text displayed on the player tablist. Use "\n" for new lines.
  tablist_footer: "&6This is a footer\n &2with a new line and your name: §a%player%" # The footer text displayed on the player tablist. Use "\n" for new lines.
  tablist_format: "%player%" # The format of player names displayed in the tablist.

world: # World-specific settings and permissions.
  block_break:
    enable: true # Allows players to break blocks if they have the required permission.
    permission: "lobbymanager.events.block.break" # Permission required to break blocks.
    permission_message: "&cYou don't have permission for &lBreaking Block" # Message displayed if the player lacks permission to break blocks.

  block_place:
    enable: true # Allows players to place blocks if they have the required permission.
    permission: "lobbymanager.events.block.place" # Permission required to place blocks.
    permission_message: "&cYou don't have permission for &lPlacing Block" # Message displayed if the player lacks permission to place blocks.
```

## Error Reporting
If you have any problems [open an issue](https://github.com/BearlessDev/LobbyManager/issues) and me or the community will help you to find your answer.