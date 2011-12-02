package net.krinsoft.race;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author krinsdeath
 */
public class RacePoints extends JavaPlugin {
    private final static Logger LOGGER = Logger.getLogger("RacePoints");

    @Override
    public void onEnable() {
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

}