package net.krinsoft.race.regions;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 *
 * @author krinsdeath
 */
public interface Region {

    public Vector getMinimumPoint();

    public Vector getMaximumPoint();

    public boolean contains(Location loc);

}
