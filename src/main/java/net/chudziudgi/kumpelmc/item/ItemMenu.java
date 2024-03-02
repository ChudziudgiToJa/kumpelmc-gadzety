package net.chudziudgi.kumpelmc.item;

import net.chudziudgi.kumpelmc.util.ChatUtil;
import net.chudziudgi.kumpelmc.util.ItemBuilder;
import net.chudziudgi.kumpelmc.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemMenu {
    private final ItemUtil item;

    public ItemMenu(ItemUtil item) {
        this.item = item;
    }

    public void createInventory(final Player player) {
        final Inventory inventory = Bukkit.createInventory(null, 9*3, ChatUtil.fixColor("&bgadzeciki"));

        final ItemStack speed = new ItemBuilder(Material.SUGAR)
                .setName(item.getSpeedItemName())
                .setLore("",
                        " &e★ &7Ten przedmiot nadaje szybkość III",
                        " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                        " &a✔ Kliknij aby użyć gadżetu.",
                        ""
                )
                .build();

        final ItemStack fire = new ItemBuilder(Material.BLAZE_POWDER)
                .setName(item.getFlameItemName())
                .setLore("",
                        " &e★ &7Ten przedmiot podpala wszystkich w obrębie 5 bloków.",
                        " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                        " &a✔ Kliknij aby użyć gadżetu.",
                        ""
                )
                .build();

        final ItemStack firework = new ItemBuilder(Material.FIREWORK_ROCKET)
                .setName(item.getPushUpItemName())
                .setLore("",
                        " &e★ &7Ten przedmiot wyrzuca cię w góre.",
                        " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                        " &a✔ Kliknij aby użyć gadżetu.",
                        ""
                )
                .build();

        final ItemStack boost = new ItemBuilder(Material.NAME_TAG)
                .setName(item.getBoostItemName())
                .setLore("",
                        " &e★ &7Ten przedmiot nadaje BOOST do przodu.",
                        " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                        " &a✔ Kliknij aby użyć gadżetu.",
                        ""
                )
                .build();

        final ItemStack fly = new ItemBuilder(Material.FEATHER)
                .setName(item.getFlyItemName())
                .setLore("",
                        " &e★ &7Ten przedmiot nadaje wszystkim efekt lewitacji w obrębie 5 bloków.",
                        " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                        " &a✔ Kliknij aby użyć gadżetu.",
                        ""
                )
                .build();

        final ItemStack jump = new ItemBuilder(Material.SLIME_BALL)
                .setName(item.getJumpItemName())
                .setLore("",
                        " &e★ &7Ten przedmiot nadaje efekt wysokiego skoku 4",
                        " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                        " &a✔ Kliknij aby użyć gadżetu.",
                        ""
                )
                .build();
        inventory.setItem(0, firework);
        inventory.setItem(2, boost);
        inventory.setItem(3, speed);
        inventory.setItem(4, fly);
        inventory.setItem(5, fire);

        player.openInventory(inventory);
    }
}
