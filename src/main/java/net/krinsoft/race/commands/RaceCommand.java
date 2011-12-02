package net.krinsoft.race.commands;

import com.pneumaticraft.commandhandler.Command;
import net.krinsoft.race.RaceManager;
import net.krinsoft.race.RacePoints;

/**
 *
 * @author krinsdeath
 */
public abstract class RaceCommand extends Command {
    protected RacePoints plugin;
    protected RaceManager manager;

    public RaceCommand(RacePoints plugin) {
        super(plugin);
        this.plugin = (RacePoints) plugin;
        this.manager = this.plugin.getRaceManager();
    }

}
