package net.krinsoft.race;

import com.pneumaticraft.commandhandler.PermissionsInterface;
import java.util.List;
import org.bukkit.command.CommandSender;

/**
 *
 * @author krinsdeath
 */
public class PermissionHandler implements PermissionsInterface {
    private RacePoints plugin;
    private boolean opIsRequired = false;

    public PermissionHandler(RacePoints plugin) {
        this.plugin = plugin;
        this.opIsRequired = this.plugin.getConfig().getBoolean("plugin.opFallback");
    }

    public boolean hasPermission(CommandSender sender, String node, boolean isOpRequired) {
        boolean has = sender.hasPermission(node);
        boolean set = sender.isPermissionSet(node);
        if (!opCheck(sender, isOpRequired)) { return false; }
        if (has) {
            return true;
        } else if (set && !has) {
            return false;
        } else if (!set && !has && sender.hasPermission("race.*")) {
            return true;
        }
        return false;
    }

    public boolean hasAnyPermission(CommandSender sender, List<String> perms, boolean opRequired) {
        if (!opCheck(sender, opRequired)) { return false; }
        for (String node : perms) {
            if (sender.hasPermission(node)) { return true; }
        }
        return false;
    }

    public boolean hasAllPermission(CommandSender sender, List<String> perms, boolean opRequired) {
        if (!opCheck(sender, opRequired)) { return false; }
        for (String node : perms) {
            if (!sender.hasPermission(node)) { return false; }
        }
        return true;
    }

    private boolean opCheck(CommandSender sender, boolean isOpRequired) {
        if (isOpRequired && opIsRequired && !sender.isOp()) {
            return false;
        }
        return true;
    }

}
