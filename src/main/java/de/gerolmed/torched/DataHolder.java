package de.gerolmed.torched;

import org.bukkit.ChatColor;

public class DataHolder {
    private int resetTime = 60*10;
    private String permaTorch = "&fPermanent Torch";
    private boolean enableCrafting = true,
            expensiveCrafting = false;

    public DataHolder() {
        try {
            setResetTime(ConfigHolder.Configs.CONFIG.getConfig().getInt("resetTime"));
            setPermaTorch(ConfigHolder.Configs.CONFIG.getConfig().getString("permaTorch"));
            setEnableCrafting(ConfigHolder.Configs.CONFIG.getConfig().getBoolean("enableCrafting"));
            setExpensiveCrafting(ConfigHolder.Configs.CONFIG.getConfig().getBoolean("expensiveCrafting"));
        } catch (Exception ex) {
            System.out.println("Somethings wrong with the config!");
        } finally {
            System.out.println("Successfully loaded all values!");
        }
    }

    public int getResetTime() {
        return resetTime;
    }

    public void setResetTime(int resetTime) {
        this.resetTime = resetTime;
    }

    public String getPermaTorch() {
        return ChatColor.translateAlternateColorCodes('&', permaTorch);
    }

    public void setPermaTorch(String permaTorch) {
        this.permaTorch = permaTorch;
    }

    public boolean isEnableCrafting() {
        return enableCrafting;
    }

    public void setEnableCrafting(boolean enableCrafting) {
        this.enableCrafting = enableCrafting;
    }

    public boolean isExpensiveCrafting() {
        return expensiveCrafting;
    }

    public void setExpensiveCrafting(boolean expensiveCrafting) {
        this.expensiveCrafting = expensiveCrafting;
    }
}
