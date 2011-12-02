package net.krinsoft.race.commands;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.krinsoft.race.PlayerSession;
import net.krinsoft.race.RacePoints;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

/**
 *
 * @author krinsdeath
 */
public class RaceCheckpointAddCommand extends RaceCommand {

    public RaceCheckpointAddCommand(RacePoints plugin) {
        super(plugin);
        this.setName("Race Checkpoint Add");
        this.setCommandUsage("/race checkpoint add");
        this.setArgRange(0, 0);
        this.addKey("racepoints checkpoint add");
        this.addKey("racepoints cp add");
        this.addKey("race checkpoint add");
        this.addKey("race cp add");
        this.addKey("rp checkpoint add");
        this.addKey("rp cp add");
        this.addKey("r checkpoint add");
        this.addKey("r cp add");
        this.setPermission("race.checkpoint.add", "Allows this user to add checkpoints to races.", PermissionDefault.OP);
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        PlayerSession session = plugin.getSession(sender);
        if (session.isValidSelection()) {
            manager.addCheckpoint(session.getSelectedRace(), session.getSelection());
            sender.sendMessage("Checkpoint added.");
            return;
        }
    }
}
