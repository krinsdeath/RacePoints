package net.krinsoft.race.regions;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

/**
 *
 * @author krinsdeath
 */
public class CuboidRegion implements Region {

    private World world;
    private Vector minimum;
    private Vector maximum;

    public CuboidRegion(com.sk89q.worldedit.Vector pos1, com.sk89q.worldedit.Vector pos2, World world) {
        this.world = world;
        Vector left = new Vector(pos1.getBlockX(), pos1.getBlockY(), pos1.getBlockZ());
        Vector right = new Vector(pos2.getBlockX(), pos2.getBlockY(), pos2.getBlockZ());
        this.minimum = Vector.getMinimum(left, right);
        this.maximum = Vector.getMaximum(left, right);
    }

    public CuboidRegion(Vector left, Vector right, World world) {
        this.world = world;
        this.minimum = Vector.getMinimum(left, right);
        this.maximum = Vector.getMaximum(left, right);
    }

    public Vector getMinimumPoint() {
        return minimum;
    }

    public Vector getMaximumPoint() {
        return maximum;
    }

    public boolean contains(Location loc) {
        if (!loc.getWorld().equals(this.world)) { return false; }
        if (loc.getBlockX() >= minimum.getBlockX() && loc.getBlockX() <= maximum.getBlockX()) {
            if (loc.getBlockY() >= minimum.getBlockY() && loc.getBlockY() <= maximum.getBlockY()) {
                if (loc.getBlockZ() >= minimum.getBlockZ() && loc.getBlockZ() <= maximum.getBlockZ()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return minimum.getBlockX() + "," + minimum.getBlockY() + "," + minimum.getBlockZ() + ":" +
               maximum.getBlockX() + "," + maximum.getBlockY() + "," + maximum.getBlockZ() + ":" +
               this.world.getName();
    }
}
