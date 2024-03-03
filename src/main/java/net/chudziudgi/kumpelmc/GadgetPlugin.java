package net.chudziudgi.kumpelmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GadgetPlugin extends JavaPlugin {
    public GadgetInventory gadgetInventory;
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new GadgetUseController(), this);
        Bukkit.getPluginCommand("nadajprzedmioty").setExecutor(new GadgetGiveCommand(this.gadgetInventory));

        GadgetInventory gadgetInventory = new GadgetInventory();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
