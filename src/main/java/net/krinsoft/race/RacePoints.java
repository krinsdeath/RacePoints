package net.krinsoft.race;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author krinsdeath
 */
public class RacePoints extends JavaPlugin {
    // logger static
    private final static Logger LOGGER = Logger.getLogger("RacePoints");

    // managers

    @Override
    public void onEnable() {
        // build managers
        registerManagers();
        log(this + " is now enabled.");
    }

    @Override
    public void onDisable() {
        log(this + " is now disabled.");
    }

    public void log(String message) {
        message = "[" + this + "] " + message;
        LOGGER.info(message);
    }

    // managers
    public void registerManagers() {

    }

}
