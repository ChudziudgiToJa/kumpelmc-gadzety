package net.chudziudgi.kumpelmc;

import net.chudziudgi.kumpelmc.item.ClickItemController;
import net.chudziudgi.kumpelmc.item.CooldownManager;
import net.chudziudgi.kumpelmc.item.GiveCustomItemCommand;
import net.chudziudgi.kumpelmc.item.ItemMenu;
import net.chudziudgi.kumpelmc.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class McPlugin extends JavaPlugin {
    public CooldownManager cooldownManager;
    public ItemUtil itemUtil;
    public ItemMenu itemMenu;
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ClickItemController(itemUtil, cooldownManager), this);
        this.getCommand("nadajprzedmioty").setExecutor(new GiveCustomItemCommand(itemMenu));
        ItemUtil itemUtil = new ItemUtil();
        ItemMenu itemMenu = new ItemMenu(itemUtil);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
