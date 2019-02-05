package de.gerolmed.torched.listener;

import de.gerolmed.torched.Main;
import de.gerolmed.torched.utils.BasicEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener extends BasicEvent {

    public InteractListener(Main plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void interact(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            ItemStack itemStack = event.getPlayer().getItemInHand();

            if(itemStack == null || (itemStack.getType() != Material.FLINT_AND_STEEL && itemStack.getType() != Material.FIREBALL))
                return;

            Block block = event.getClickedBlock();
            plugin.getBlockManager().addBlock(block, plugin.getDataHolder().getResetTime());
        }
    }
}
