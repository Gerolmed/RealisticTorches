package de.gerolmed.torched.listener;

import de.gerolmed.torched.Main;
import de.gerolmed.torched.utils.BasicEvent;
import de.gerolmed.torched.utils.BlockManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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

        Player player = event.getPlayer();

        if(event.getBlock().getType() == Material.REDSTONE_TORCH_ON) {
            if(event.getItemInHand().hasItemMeta() &&
                    event.getItemInHand().getItemMeta().hasDisplayName() &&
                    event.getItemInHand().getItemMeta().getDisplayName().equals(plugin.getDataHolder().getUnlitTorch())) {
                plugin.getBlockManager().addUnlit(event.getBlock());
            }
        }

        if(event.getBlock().getType() == Material.TORCH) {
            if(event.getItemInHand().hasItemMeta() &&
                    event.getItemInHand().getItemMeta().hasDisplayName() &&
                        event.getItemInHand().getItemMeta().getDisplayName().equals(plugin.getDataHolder().getPermaTorch())) {
                plugin.getBlockManager().addPersistent(event.getBlock());
            }
            else if(plugin.getDataHolder().isEnablePermanentPermission() && player.hasPermission(plugin.getDataHolder().getPermanentPermission()))
                return;
            else if(plugin.getDataHolder().isEnableCreativePermanent() && player.getGameMode() == GameMode.CREATIVE)
                return;
            else
                plugin.getBlockManager().addBlock(event.getBlock(), plugin.getDataHolder().getResetTime());

        }
    }

    @EventHandler(priority =  EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent event) {

        Material type = event.getBlock().getType();
        BlockManager blockManager = plugin.getBlockManager();

        if(type == Material.TORCH) {
            if(blockManager.isPersistent(event.getBlock())) {
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);

                ItemStack item = new ItemStack(Material.TORCH);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(plugin.getDataHolder().getPermaTorch());
                item.setItemMeta(itemMeta);

                blockManager.removePersistent(event.getBlock());
                event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);

            } else
                blockManager.removeBlock(event.getBlock());
        }

        if(type == Material.REDSTONE_TORCH_ON || type == Material.REDSTONE_TORCH_OFF) {
            if(blockManager.isUnlit(event.getBlock())) {
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);

                ItemStack item = new ItemStack(Material.REDSTONE_TORCH_ON);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(plugin.getDataHolder().getUnlitTorch());
                item.setItemMeta(itemMeta);

                blockManager.removeUnlit(event.getBlock());
                event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
            }
        }
    }
}
