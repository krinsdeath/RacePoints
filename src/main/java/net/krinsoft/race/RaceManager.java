package net.krinsoft.race;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.krinsoft.race.regions.Region;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author krinsdeath
 */
public class RaceManager {
    // plugin/config objects
    private RacePoints plugin;
    private FileConfiguration config;

    // race mappings
    private Map<String, Race> races = new HashMap<String, Race>();

    public RaceManager(RacePoints plugin) {
        this.plugin = plugin;
        config = YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder(), "races.yml"));
        config.setDefaults(YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder(), "races.yml")));
        config.options().copyDefaults(true);
    }

    public Race getRace(String name) {
        if (config.getString("races." + name + ".description") == null) {
            return null;
        }
        return new Race(plugin, name, null);
    }

    public void startRace(final String name, List<String> racers) {
        if (races.containsKey(name)) { return; }
        races.put(name, new Race(plugin, name, racers));
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Race race = races.get(name);
                for (String player : race.getRacers()) {
                    if (race.getCheckpoint(plugin.getSession(player).nextCheckpoint()).contains(plugin.getServer().getPlayer(player).getLocation())) {
                        race.advanceCheckpoint(player);
                    }
                }
            }
        }, 1, 3);
    }

    public int createRace(String name, String description) {
        if (config.getString("races." + name + ".description") == null) {
            config.set("races." + name + ".description", description);
            config.set("races." + name + ".checkpoints", new ArrayList<String>());
            saveRaces();
            return 1;
        } else {
            return -1;
        }
    }

    public int addCheckpoint(String name, Region selection) {
        List<String> checkpoints = config.getStringList("races." + name + ".checkpoints");
        if (checkpoints == null) { return 0; }
        checkpoints.add(selection.toString());
        config.set("races." + name + ".checkpoints", checkpoints);
        saveRaces();
        return 1;
    }

    public int addCheckpoint(String name, Region selection, int index) {
        List<String> checkpoints = config.getStringList("races." + name + ".checkpoints");
        if (checkpoints == null || index > checkpoints.size()) { return 0; }
        checkpoints.add(index, selection.toString());
        config.set("races." + name + ".checkpoints", checkpoints);
        saveRaces();
        return 1;
    }

    public void deleteRace(String name) {

    }

    public ConfigurationSection getRaceConfig(String race) {
        return config.getConfigurationSection("races.'" + race + "'");
    }

    private void saveRaces() {
        try {
            config.save(new File(plugin.getDataFolder(), "races.yml"));
            plugin.log("races.yml saved successfully.");
        } catch (IOException e) {
            plugin.log("An error occurred while saving races.yml");
        }
    }
}
