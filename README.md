# LobbyManager v1.1

### Description:
LobbyManager is a plugin that allows you to easily configure your server lobby just through a single configuration file that is easy to understand and modify.

## Commands:
- /lm-reload or /lobbymanager-reload:
    - Allows you to reload the configuration file after modifying it to avoid restarting the entire server.
- /lm-spawn, /lobbymanager-spawn or /spawn:
  - Allows you to go back to the spawn set on the config.yml file.

## Permissions:
- Reload Command:
    - Default permission is: "lobbymanager.commands.reload" but you can change it on the config.yml file.
- Spawn Command:
  - This command don't require permission.

## Configuration file:
Here the config file of the plugin:

```Yaml
#    _          _     _           __  __
#   | |    ___ | |__ | |__  _   _|  \/  | __ _ _ __   __ _  __ _  ___ _ __
#   | |   / _ \| '_ \| '_ \| | | | |\/| |/ _` | '_ \ / _` |/ _` |/ _ \ '__|
#   | |__| (_) | |_) | |_) | |_| | |  | | (_| | | | | (_| | (_| |  __/ |
#   |_____\___/|_.__/|_.__/ \__, |_|  |_|\__,_|_| |_|\__,_|\__, |\___|_|
#                           |___/                          |___/           
# By Bearless
# Version: 1.1
#
#
# LobbyManager Placeholder:
#
# %player% --> return the name of the player.
# %prefix% --> return the prefix of the Luckperms Group the player is in.
#

commands: #List of all the Permission/Message of all the plugin command.
  reload:
    permission: "lobbymanager.commands.reload" #The permission to do /lm-reload.
    permission-message: "&cYou don't have the required permission." #The message will be sent to the player who perform the command without the right permission.
    reload-message: "&aThe configuration file have been reloaded." #The message send while the command successfully done.
  spawn:
    spawn_message: "§aYou have been teleported to the spawn." #The message send while the command successfully done.

usage-message:
  reload: "&c&lIncorrect Usage: &c/lm-reload &c&lor &c/lobbymanager-reload" #Error message while you type: "/lobbymanager-reload <somethings>".

server:
  disable_default_join_message: true #Disable the default join message "Bearless joined the game".
  disable_default_quit_message: true #Disable the default quit message "Bearless quit the game".

  custom_join_message: "%prefix% %player% &ajoin the lobby!" #Replace the default join message by a custom one.
  custom_quit_message: "%prefix% %player% &cquit the lobby!" #Replace the default quit message by a custom one.

  disable_pvp: true #Prevent players from pvp each other.
  disable_entity_spawn: true #Disable the spawn of entity (Passive and Aggressive) like (Pig, Creeper, Zombie, Cow etc...).
  disable_weather_change: true #Disable the change of the weather.

  enable_join_player_spawn_loc: true #enable or disable the custom spawn for player while joining the server.

  spawn_world_name: "world" #The name of the world where the spawn will be set.
  spawn_X: 0 #The Horizontal position of the player.
  spawn_Y: 65 #The Vertical position of the player.
  spawn_Z: 0 #The Depth position of the player.
  spawn_Yaw: 180 #The Horizontal tilt of the player.
  spawn_Pitch: 0 #The Vertical tilt of the player.

  teleport_onFall: 5 #This is the Vertical position where the player get teleported to the Spawn you set.

player:
  disable_fall_damage: true #Preventing players from taking fall damage.
  disable_food_loss: true #Preventing players from lost food.

  player_health_level: 20 #Set the health of the player while joining the server.
  player_food_level: 20 #Set the food of the player while joining the server.

  enable_player_gamemode: true #Enable or Disable the gamemode of the player while joining the server.
  set_player_gamemode: "adventure" #The gamemode will be set to the player while joining the server.

  tablist_header: "&aThis is a header\n &ewith a new lines" #Set the header of the tablist. TIP: type "\n" to get a new line.
  tablist_footer: "&6This is a footer\n &2with a new lines" #Set the footer of the tablist. TIP: type "\n" to get a new line.
  tablist_format: "%prefix% %player%"
```

## The Discord
If you need help you can join the community server, and we will help you.

Discord: <a href="https://discord.gg/Y2gaXtdrG9">https://discord.gg/Y2gaXtdrG9</a>