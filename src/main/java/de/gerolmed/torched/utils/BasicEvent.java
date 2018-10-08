package de.gerolmed.torched.utils;

import de.gerolmed.torched.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class BasicEvent implements Listener {

    protected Main plugin;

    public BasicEvent(Main plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
