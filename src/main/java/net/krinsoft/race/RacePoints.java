package net.krinsoft.race;

import com.pneumaticraft.commandhandler.CommandHandler;
import com.sk89q.worldedit.WorldEdit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import net.krinsoft.race.commands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author krinsdeath
 */
public class RacePoints extends JavaPlugin {
    // logger static
    private final static Logger LOGGER = Logger.getLogger("RacePoints");

    // managers
    private PermissionHandler permissions;
    private CommandHandler commands;
    private RaceManager manager;

    // worldedit handle
    private WorldEdit WEInstance;

    // session hashmap
    private Map<String, PlayerSession> players = new HashMap<String, PlayerSession>();

    @Override
    public void onEnable() {
        // get WorldEdit instance
        if (this.getConfig().getBoolean("plugin.worldedit")) {
            Plugin p = this.getServer().getPluginManager().getPlugin("WorldEdit");
            if (p != null) {
                WEInstance = (WorldEdit) p;
            }
        }
        // build managers and handlers
        registerManagers();
        registerCommands();
        // build listeners
        registerListeners();
        // let the log show that racepoints is enabled
        log(this + " is now enabled.");
    }

    @Override
    public void onDisable() {
        log(this + " is now disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> arguments = new ArrayList<String>(Arrays.asList(args));
        arguments.add(0, label);
        return commands.locateAndRunCommand(sender, arguments);
    }

    public void log(String message) {
        message = "[" + this + "] " + message;
        LOGGER.info(message);
    }

    public void warn(String message) {
        message = "[" + this + "] " + message;
        LOGGER.warning(message);
    }

    // managers
    public void registerManagers() {
        this.manager = new RaceManager(this);
    }

    // command handler
    public void registerCommands() {
        // create the permissions and command handler objects
        this.permissions = new PermissionHandler(this);
        this.commands = new CommandHandler(this, this.permissions);
        // register commands
        this.commands.registerCommand(new RaceCreateCommand(this));
        this.commands.registerCommand(new RaceDeleteCommand(this));
        this.commands.registerCommand(new RaceSelectCommand(this));
        this.commands.registerCommand(new RaceListCommand(this));
        //this.commands.registerCommand(new RaceCheckpointAddCommand(this));
        //this.commands.registerCommand(new RaceCheckpointDeleteCommand(this));
        //this.commands.registerCommand(new RaceCheckpointInsertCommand(this));
    }

    // listeners
    public void registerListeners() {

    }

    // get the race manager
    public RaceManager getRaceManager() {
        return this.manager;
    }

    // worldedit
    public WorldEdit getWorldEdit() {
        return WEInstance;
    }

    // session management
    public PlayerSession getSession(String player) {
        if (players.containsKey(player)) {
            return players.get(player);
        } else {
            players.put(player, new PlayerSession(getServer().getPlayer(player)));
            return players.get(player);
        }
    }

    public PlayerSession getSession(CommandSender player) {
        String name = player.getName();
        if (players.containsKey(name)) {
            return players.get(name);
        } else {
            players.put(name, new PlayerSession(getServer().getPlayer(name)));
            return players.get(name);
        }
    }

}
