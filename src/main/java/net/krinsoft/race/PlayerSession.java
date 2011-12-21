package net.krinsoft.race;

import net.krinsoft.race.regions.CuboidRegion;
import net.krinsoft.race.regions.IncompleteRegionException;
import net.krinsoft.race.regions.Region;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 *
 * @author krinsdeath
 */
public class PlayerSession {
    // current world and vector information
    private World world;
    private Vector leftClick = null;
    private Vector rightClick = null;

    // race information
    private String raceSelection = null;
    private boolean racing = false;
    private int checkpoint = 0;

    public PlayerSession(Player player) {
        this.world = player.getLocation().getWorld();
    }

    public Vector getLeftClickSelection() {
        return this.leftClick;
    }

    public Vector getRightClickSelection() {
        return this.rightClick;
    }

    public void resetSelection(World world) {
        this.leftClick = null;
        this.rightClick = null;
        this.world = world;
        this.raceSelection = null;
    }

    public Region getSelection() {
        if (this.leftClick != null && this.rightClick != null) {
            return new CuboidRegion(this.leftClick, this.rightClick, this.world);
        } else {
            return null;
        }
    }

    public boolean isValidSelection() {
        if (this.leftClick != null && this.rightClick != null && this.raceSelection != null) {
            return true;
        }
        return false;
    }

    public void setLeftClickVector(Vector left) {
        this.leftClick = left;
    }

    public void setRightClickVector(Vector right) {
        this.rightClick = right;
    }

    public String getSelectedRace() {
        return raceSelection;
    }

    public void setSelectedRace(String race) {
        this.raceSelection = race;
    }

    public int nextCheckpoint() {
        if (racing) {
            return checkpoint + 1;
        } else {
            return -1;
        }
    }

}
