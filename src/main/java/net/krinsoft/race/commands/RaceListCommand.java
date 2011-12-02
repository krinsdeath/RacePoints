package net.krinsoft.race.commands;

import java.util.List;
import net.krinsoft.race.RacePoints;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

/**
 *
 * @author krinsdeath
 */
public class RaceListCommand extends RaceCommand {

    public RaceListCommand(RacePoints plugin) {
        super(plugin);
        this.setName("Race List");
        this.setCommandUsage("/race list");
        this.setArgRange(0, 0);
        this.addKey("racepoints list");
        this.addKey("race list");
        this.addKey("rp list");
        this.addKey("r list");
        this.setPermission("race.list", "Allows this user to list available races.", PermissionDefault.TRUE);
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        sender.sendMessage("To be implemented.");
    }

}
