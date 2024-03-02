package net.chudziudgi.kumpelmc;

import net.chudziudgi.kumpelmc.item.ClickItemController;
import net.chudziudgi.kumpelmc.item.GiveCustomItemCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class McPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ClickItemController(), this);
        this.getCommand("nadajprzedmioty").setExecutor(new GiveCustomItemCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
