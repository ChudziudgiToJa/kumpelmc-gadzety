package net.chudziudgi.kumpelmc;

import net.chudziudgi.kumpelmc.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.stream.IntStream;

public class GadgetInventory {
    public void createInventory(final Player player) {
        int inventorySize = 9 * 3;
        Inventory inventory = Bukkit.createInventory(null, inventorySize, ChatUtil.fixColor("&bgadzeciki"));

        IntStream.range(0, Gadget.values().length).forEach(index -> {
            Gadget value = Gadget.values()[index];
            inventory.setItem(index,value.buildItem());
        });

        player.openInventory(inventory);
    }
}
