package de.gerolmed.torched;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigHolder {

    private JavaPlugin javaPlugin;

    public ConfigHolder(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        createConfigs();
    }

    private void createConfigs() {
        for(Configs configs : Configs.values())
            configs.init(this);
    }

    public FileConfiguration createConfig(String name) {
        File conf = new File(javaPlugin.getDataFolder(), name);

        if(!conf.exists()) {
            conf.getParentFile().mkdirs();
            javaPlugin.saveResource(name, false);
        }

        FileConfiguration fileConfiguration = new YamlConfiguration();

        try {
            fileConfiguration.load(conf);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InvalidConfigurationException ex) {
            ex.printStackTrace();
        }

        return fileConfiguration;

    }

    public void saveConfig(FileConfiguration fileConfiguration, String name) {
        try {
            fileConfiguration.save(new File(javaPlugin.getDataFolder(), name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum Configs {
        CONFIG("config.yml"), TORCHES("torches.yml");

        private String name;
        private FileConfiguration fileConfiguration;

        Configs(String name) {
            this.name = name;
        }

        public void init(ConfigHolder configHolder) {
            this.fileConfiguration = configHolder.createConfig(name);
        }

        public FileConfiguration getConfig() {
            return fileConfiguration;
        }

        public void save() {
            Main.getInstance().getConfigHolder().saveConfig(fileConfiguration, name);
        }
    }
}
