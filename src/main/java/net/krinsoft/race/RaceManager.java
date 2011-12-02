package net.krinsoft.race;

import java.io.File;
import java.util.ArrayList;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author krinsdeath
 */
public class RaceManager {
    private RacePoints plugin;
    private FileConfiguration config;

    public RaceManager(RacePoints plugin) {
        this.plugin = plugin;
        config = YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder(), "races.yml"));
        config.setDefaults(YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder(), "races.yml")));
        config.options().copyDefaults(true);
    }

    public int createRace(String name, String description) {
        if (config.getString("races." + name + ".description") == null) {
            config.set("races." + name + ".description", description);
            config.set("races." + name + ".checkpoints", new ArrayList<String>());
            return 0;
        } else {
            return -1;
        }
    }

    public void deleteRace(String name) {

    }
}
