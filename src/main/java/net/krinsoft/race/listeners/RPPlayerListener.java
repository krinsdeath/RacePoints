package net.krinsoft.race.listeners;

import net.krinsoft.race.PlayerSession;
import net.krinsoft.race.RacePoints;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author krinsdeath
 */
public class RPPlayerListener extends PlayerListener {
    private RacePoints plugin;
    private Material WAND = null;
    private boolean worldedit = false;
    
    public RPPlayerListener(RacePoints plugin) {
        this.plugin = plugin;
        this.worldedit = plugin.getConfig().getBoolean("plugin.worldedit");
        if (!this.worldedit) {
            WAND = Material.getMaterial(plugin.getConfig().getInt("plugin.wand"));
        }
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!worldedit && player.hasPermission("race.wand") && plugin.getSession(player).nextCheckpoint() == -1) {
            if (WAND != null && player.getItemInHand().getType() == WAND) {
                Action action = event.getAction();
                if (event.getClickedBlock() == null) { return; }
                if (action == Action.LEFT_CLICK_BLOCK) {
                    plugin.getSession(player).setLeftClickVector(event.getClickedBlock().getLocation().toVector());
                    player.sendMessage(ChatColor.GREEN + "Left click position saved.");
                }
                if (action == Action.RIGHT_CLICK_BLOCK) {
                    plugin.getSession(player).setRightClickVector(event.getClickedBlock().getLocation().toVector());
                    player.sendMessage(ChatColor.AQUA + "Right click position saved.");
                }
            }
        }
    }

    @Override
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        plugin.getSession(player).resetSelection(player.getWorld());
    }

}
