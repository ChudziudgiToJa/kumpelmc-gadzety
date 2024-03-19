package net.chudziudgi.kumpelmc;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import javax.naming.Name;
import javax.xml.stream.events.Namespace;

public final class GadgetPlugin extends JavaPlugin {
    public GadgetInventory gadgetInventory = new GadgetInventory();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new GadgetUseController(), this);
        Bukkit.getPluginCommand("nadajprzedmioty").setExecutor(new GadgetGiveCommand(this.gadgetInventory));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
