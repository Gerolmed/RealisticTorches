package de.gerolmed.torched;

import de.gerolmed.torched.listener.BlockListener;
import de.gerolmed.torched.recipes.RecipeManager;
import de.gerolmed.torched.utils.BlockManager;
import de.gerolmed.torched.utils.TorchLocation;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    static {
        ConfigurationSerialization.registerClass(TorchLocation.class);
    }

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    private ConfigHolder configHolder;
    private DataHolder dataHolder;
    private BlockManager blockManager;


    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        System.out.println("=====["+ this.getDescription().getFullName()+"]=====");
        configHolder = new ConfigHolder(this);
        blockManager = new BlockManager(this);
        dataHolder = new DataHolder();
        new RecipeManager(this);

        new BlockListener(this);
    }

    @Override
    public void onDisable() {
        System.out.println("=====["+ this.getDescription().getFullName()+"]=====");
        blockManager.saveYml();
    }


    public ConfigHolder getConfigHolder() {
        return configHolder;
    }

    public BlockManager getBlockManager() {
        return blockManager;
    }

    public DataHolder getDataHolder() {
        return dataHolder;
    }
}
