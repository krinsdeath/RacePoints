package net.krinsoft.race.commands;

import java.util.List;
import net.krinsoft.race.Race;
import net.krinsoft.race.RacePoints;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

/**
 *
 * @author krinsdeath
 */
public class RaceSelectCommand extends RaceCommand {

    public RaceSelectCommand(RacePoints plugin) {
        super(plugin);
        this.setName("Race Select");
        this.setCommandUsage("/race select [name]");
        this.addCommandExample("/race select Tree");
        this.setArgRange(1, 1);
        this.addKey("racepoints select");
        this.addKey("race select");
        this.addKey("rp select");
        this.addKey("r select");
        this.setPermission("race.select", "Allows this user to select and view races.", PermissionDefault.OP);
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        Race race = manager.getRace(args.get(0));
        if (race != null) {
            plugin.getSession(sender).setSelectedRace(race.getName());
            sender.sendMessage(ChatColor.GREEN + "The race '" + race.getName() + "' has been selected.");
        } else {
            sender.sendMessage(ChatColor.RED + "That race doesn't exist.");
        }
    }

}
