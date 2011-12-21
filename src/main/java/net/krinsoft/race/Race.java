package net.krinsoft.race;

import java.util.ArrayList;
import java.util.List;
import net.krinsoft.race.regions.CuboidRegion;
import net.krinsoft.race.regions.Region;
import org.bukkit.util.Vector;

/**
 *
 * @author krinsdeath
 */
public class Race {
    private String name;
    private List<Region> checkpoints = new ArrayList<Region>();
    private List<String> racers = new ArrayList<String>();

    public Race(RacePoints plugin, String name, List<String> racers) {
        this.name = name;
        this.racers = racers;
        try {
            int index = 0;
            List<String> points = plugin.getRaceManager().getRaceConfig(name).getStringList("checkpoints");
            for (String region : points) {
                index++;
                String world = region.split(":")[0];
                String loc1 = region.split(":")[1];
                String loc2 = region.split(":")[2];
                try {
                    Vector left = new Vector(Integer.parseInt(loc1.split(",")[0]), Integer.parseInt(loc1.split(",")[1]), Integer.parseInt(loc1.split(",")[2]));
                    Vector right = new Vector(Integer.parseInt(loc2.split(",")[0]), Integer.parseInt(loc2.split(",")[1]), Integer.parseInt(loc2.split(",")[2]));
                    checkpoints.add(new CuboidRegion(left, right, plugin.getServer().getWorld(world)));
                } catch (NumberFormatException e) {
                    plugin.warn("Invalid checkpoint region found at index " + index + " for race " + name + ".. skipping.");
                    continue;
                }
            }
        } catch (NullPointerException e) {
            plugin.warn("Null list found at checkpoints for race " + name);
        }
    }

    public Region getCheckpoint(int index) {
        if (index > checkpoints.size()) {
            return checkpoints.get(checkpoints.size()-1);
        }
        return checkpoints.get(index);
    }

    public void advanceCheckpoint(String player) {
        
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object a) {
        if (a == this) { return true; }
        if (a.getClass() != this.getClass()) { return false; }
        Race that = (Race) a;
        if (that.hashCode() == this.hashCode()) { return true; }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    public List<String> getRacers() {
        return racers;
    }

}
