package de.gerolmed.torched.utils;

import de.gerolmed.torched.ConfigHolder;
import de.gerolmed.torched.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlockManager implements Runnable {
    private HashMap<Location, Integer> blockCache;
    private ArrayList<Location> persistants, unlits;

    private Main plugin;

    public BlockManager(Main plugin) {
        this.plugin = plugin;

        blockCache = new HashMap<>();
        persistants = new ArrayList<>();
        unlits = new ArrayList<>();

        loadFromYml();

        Bukkit.getScheduler().runTaskTimer(this.plugin, this, 20, 20);
    }

    public void addBlock(Block block, int time) {

        if(blockCache.containsKey(block.getLocation()))
            return;

        blockCache.put(block.getLocation(), time);
    }

    public boolean isTorch(Block block) {
        Location loc = block.getLocation();
        return blockCache.containsKey(loc);
    }

    public boolean isPersistent(Block block) {
        Location loc = block.getLocation();
        return persistants.contains(loc);
    }

    public boolean isUnlit(Block block) {
        Location loc = block.getLocation();
        return unlits.contains(loc);
    }

    public void removeBlock(Block block) {

        if(!blockCache.containsKey(block.getLocation()))
            return;

        blockCache.remove(block.getLocation());
    }


    public void addPersistent(Block block) {

        if(persistants.contains(block.getLocation()))
            return;

        persistants.add(block.getLocation());
    }

    public void removePersistent(Block block) {

        if(!persistants.contains(block.getLocation()))
            return;

        persistants.remove(block.getLocation());
    }

    public void addUnlit(Block block) {

        if(unlits.contains(block.getLocation()))
            return;

        unlits.add(block.getLocation());
    }

    public void removeUnlit(Block block) {

        if(!unlits.contains(block.getLocation()))
            return;

        unlits.remove(block.getLocation());
    }

    @SuppressWarnings("deprecation")
    public void run() {

        ArrayList<Location> locationsToRemove = new ArrayList<Location>();

        for(final Location location : blockCache.keySet()) {
            int time = blockCache.get(location);
            time --;
            blockCache.put(location, time);
            if(time <= 0) {
                locationsToRemove.add(location);
            }
        }

        for (Location location : locationsToRemove) {
            Material material = Material.REDSTONE_TORCH_ON;
            byte direction = location.getBlock().getData();
            if(plugin.getDataHolder().isMakeAir())
                location.getBlock().setType(Material.AIR);
            else {
                removeBlock(location.getBlock());
                location.getBlock().setTypeIdAndData(material.ordinal(), direction, true);
                addUnlit(location.getBlock());
            }
        }
    }

    @SuppressWarnings("Duplicates")
    public void loadFromYml() {
        try {
            ConfigurationSection configurationSection = ConfigHolder.Configs.TORCHES.getConfig().getConfigurationSection("list");

            for (String key : configurationSection.getValues(false).keySet()) {
                TorchLocation torchLocation = (TorchLocation) configurationSection.get(key + ".loc");
                Location location = torchLocation.toLocation();

                if(location == null)
                    continue;

                blockCache.put(location, configurationSection.getInt(key + ".time"));
            }

        } catch (Exception ex) {
            System.out.println("Torch data didn't load properly!");
        }
        try {
            ConfigurationSection configurationSection = ConfigHolder.Configs.TORCHES.getConfig().getConfigurationSection("permanent");

            for (String key : configurationSection.getValues(false).keySet()) {
                TorchLocation torchLocation = (TorchLocation) configurationSection.get(key + ".loc");
                Location location = torchLocation.toLocation();

                if(location == null)
                    continue;

                persistants.add(location);
            }

        } catch (Exception ex) {
            System.out.println("Permanent torch data didn't load properly!");
        }
        try {
            ConfigurationSection configurationSection = ConfigHolder.Configs.TORCHES.getConfig().getConfigurationSection("unlit");

            for (String key : configurationSection.getValues(false).keySet()) {
                TorchLocation torchLocation = (TorchLocation) configurationSection.get(key + ".loc");
                Location location = torchLocation.toLocation();

                if(location == null)
                    continue;

                unlits.add(location);
            }

        } catch (Exception ex) {
            System.out.println("Unlit torch data didn't load properly!");
        }
    }

    public void saveYml() {
        {
            int i = 0;
            ConfigHolder.Configs.TORCHES.getConfig().set("list", null);
            for(Map.Entry<Location, Integer> entry : blockCache.entrySet()) {
                ConfigHolder.Configs.TORCHES.getConfig().set("list."+i+".loc", new TorchLocation(entry.getKey()));
                ConfigHolder.Configs.TORCHES.getConfig().set("list."+i+".time", entry.getValue());
                i++;
            }
        }

        {
            int i = 0;
            ConfigHolder.Configs.TORCHES.getConfig().set("permanent", null);
            for(Location loc : persistants) {
                ConfigHolder.Configs.TORCHES.getConfig().set("permanent."+i+".loc", new TorchLocation(loc));
                i++;
            }
        }

        {
            int i = 0;
            ConfigHolder.Configs.TORCHES.getConfig().set("unlit", null);
            for(Location loc : unlits) {
                ConfigHolder.Configs.TORCHES.getConfig().set("unlit."+i+".loc", new TorchLocation(loc));
                i++;
            }
        }

        ConfigHolder.Configs.TORCHES.save();
    }
}
