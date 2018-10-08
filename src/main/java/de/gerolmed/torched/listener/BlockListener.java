package de.gerolmed.torched.listener;

import de.gerolmed.torched.Main;
import de.gerolmed.torched.utils.BasicEvent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockListener extends BasicEvent {

    public BlockListener(Main plugin) {
        super(plugin);
    }

    @EventHandler(priority =  EventPriority.MONITOR)
    public void onPlace(BlockPlaceEvent event) {

        if(event.getItemInHand().hasItemMeta() &&
                event.getItemInHand().getItemMeta().hasDisplayName() &&
                event.getItemInHand().getItemMeta().getDisplayName().equals(plugin.getDataHolder().getPermaTorch())) {
            return;
        }


        if(event.getBlock().getType() == Material.TORCH)
            plugin.getBlockManager().addBlock(event.getBlock(), plugin.getDataHolder().getResetTime());
    }

    @EventHandler(priority =  EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent event) {


        if(event.getBlock().getType() == Material.TORCH) {

            if(!plugin.getBlockManager().isTorch(event.getBlock())) {
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);

                ItemStack item = new ItemStack(Material.TORCH);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(plugin.getDataHolder().getPermaTorch());
                item.setItemMeta(itemMeta);

                event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
            }

            plugin.getBlockManager().removeBlock(event.getBlock());
        }
    }
}
