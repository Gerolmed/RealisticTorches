package de.gerolmed.torched;

import org.bukkit.ChatColor;

public class DataHolder {
    private int resetTime = 60*10;
    private String permaTorch = "&fPermanent Torch",
            permanentPermission = "torch.permanent",
            unlitTorch = "torch.unlit";
    private boolean enableCrafting = true,
            expensiveCrafting = false,
            enablePermanentPermission = false,
            enableCreativePermanent = false,
            makeAir = false;

    public DataHolder() {
        try {
            setResetTime(ConfigHolder.Configs.CONFIG.getConfig().getInt("resetTime"));
            setPermaTorch(ConfigHolder.Configs.CONFIG.getConfig().getString("permaTorch"));
            setPermanentPermission(ConfigHolder.Configs.CONFIG.getConfig().getString("permanentPermission"));
            setUnlitTorch(ConfigHolder.Configs.CONFIG.getConfig().getString("unlitTorch"));
            setEnableCrafting(ConfigHolder.Configs.CONFIG.getConfig().getBoolean("enableCrafting"));
            setExpensiveCrafting(ConfigHolder.Configs.CONFIG.getConfig().getBoolean("expensiveCrafting"));
            setEnableCreativePermanent(ConfigHolder.Configs.CONFIG.getConfig().getBoolean("enableCreativePermanent"));
            setEnablePermanentPermission(ConfigHolder.Configs.CONFIG.getConfig().getBoolean("enablePermanentPermission"));
            setMakeAir(ConfigHolder.Configs.CONFIG.getConfig().getBoolean("makeAir"));

        } catch (Exception ex) {
            System.out.println("Somethings wrong with the config! -> Be sure to get the latest config from: https://www.spigotmc.org/resources/realistic-torches-opensource.61239/");
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

    public String getPermanentPermission() {
        return permanentPermission;
    }

    public void setPermanentPermission(String permanentPermission) {
        this.permanentPermission = permanentPermission;
    }

    public boolean isEnablePermanentPermission() {
        return enablePermanentPermission;
    }

    public void setEnablePermanentPermission(boolean enablePermanentPermission) {
        this.enablePermanentPermission = enablePermanentPermission;
    }

    public boolean isEnableCreativePermanent() {
        return enableCreativePermanent;
    }

    public void setEnableCreativePermanent(boolean enableCreativePermanent) {
        this.enableCreativePermanent = enableCreativePermanent;
    }

    public boolean isMakeAir() {
        return makeAir;
    }

    public void setMakeAir(boolean makeAir) {
        this.makeAir = makeAir;
    }

    public String getUnlitTorch() {
        return ChatColor.translateAlternateColorCodes('&', unlitTorch);
    }

    public void setUnlitTorch(String unlitTorch) {
        this.unlitTorch = unlitTorch;
    }
}
