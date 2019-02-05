package de.gerolmed.torched.listener;

import de.gerolmed.torched.Main;
import de.gerolmed.torched.utils.BasicEvent;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener extends BasicEvent {

    public InteractListener(Main plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void interact(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            plugin.getBlockManager().addBlock(block, plugin.getDataHolder().getResetTime());
        }
    }
}
