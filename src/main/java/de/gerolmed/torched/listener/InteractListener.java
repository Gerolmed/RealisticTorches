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

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.MONITOR)
    public void interact(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            ItemStack itemStack = event.getPlayer().getItemInHand();

            if(itemStack == null || (itemStack.getType() != Material.FLINT_AND_STEEL && itemStack.getType() != Material.FIREBALL))
                return;

            Block block = event.getClickedBlock();

            if( plugin.getBlockManager().isUnlit(block)) {
                plugin.getBlockManager().removeUnlit(block);

                byte direction = block.getData();
                block.setTypeIdAndData(Material.TORCH.ordinal(), direction, true);

                plugin.getBlockManager().addBlock(block, plugin.getDataHolder().getResetTime());
                event.setCancelled(true);
            }
        }
    }
}
