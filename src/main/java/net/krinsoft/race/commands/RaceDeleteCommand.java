package net.krinsoft.race.commands;

import java.util.List;
import net.krinsoft.race.RacePoints;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

/**
 *
 * @author krinsdeath
 */
public class RaceDeleteCommand extends RaceCommand {

    public RaceDeleteCommand(RacePoints plugin) {
        super(plugin);
        this.setName("Race Delete");
        this.setCommandUsage("/r delete [name]");
        this.addCommandExample("/r delete Tree");
        this.setArgRange(2, 10);
        this.addKey("racepoints delete");
        this.addKey("race delete");
        this.addKey("rp delete");
        this.addKey("r delete");
        this.setPermission("race.delete", "Allows this user to delete races.", PermissionDefault.OP);
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        sender.sendMessage("To be implemented.");
    }

}
