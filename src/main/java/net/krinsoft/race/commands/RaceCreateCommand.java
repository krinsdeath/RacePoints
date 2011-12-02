package net.krinsoft.race.commands;

import java.util.List;
import net.krinsoft.race.RacePoints;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

/**
 *
 * @author krinsdeath
 */
public class RaceCreateCommand extends RaceCommand {

    public RaceCreateCommand(RacePoints plugin) {
        super(plugin);
        this.setName("Race Create");
        this.setCommandUsage("/r create [name] [description]");
        this.addCommandExample("/r create Tree Race to the top of the giant tree!");
        this.setArgRange(2, 10);
        this.addKey("racepoints create");
        this.addKey("race create");
        this.addKey("rp create");
        this.addKey("r create");
        this.setPermission("race.create", "Allows this user to create new races.", PermissionDefault.OP);
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        StringBuilder description = new StringBuilder();
        for (String word : args.subList(1, args.size())) {
            description.append(word).append(" ");
        }
        sender.sendMessage("Race name: " + args.get(0));
        sender.sendMessage("Race description: " + description.toString());
        sender.sendMessage("To be implemented.");
    }

}
