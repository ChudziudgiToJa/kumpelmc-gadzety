package net.chudziudgi.kumpelmc.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {

    public String getSpeedItemName() {
        return "&b&n✪&f&l Oranżada w proszku";
    }

    public String getPushUpItemName() {
        return "&b&n✪&f&l Rakieta";
    }

    public String getBoostItemName() {
        return "&b&n✪&f&l Boost";
    }

    public String getFlyItemName() {
        return "&b&n✪&f&l Lewitacja";
    }

    public String getJumpItemName() {
        return "&b&n✪&f&l Wysoki skok";
    }
    public String getFlameItemName() {
        return "&b&n✪&f&l Zapalniczka";
    }

    public void itemController(Player player){
        ItemStack handItem = player.getInventory().getItemInMainHand();
        if (handItem.getAmount() == 1) {
            player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        } else {
            handItem.setAmount(handItem.getAmount() - 1);
        }
    }

    public void itemStack(Player p, ItemEnum itemEnum) {
        ItemStack item;
        switch (itemEnum) {
            case SPEED:
                item = new ItemBuilder(Material.SUGAR)
                        .setName(getSpeedItemName())
                        .setLore("",
                                " &e★ &7Ten przedmiot nadaje szybkość III",
                                " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                                " &a✔ Kliknij aby użyć gadżetu.",
                                ""
                        )
                        .build();
                p.getInventory().addItem(item);
                p.updateInventory();
                break;
            case FLAME:
                item = new ItemBuilder(Material.BLAZE_POWDER)
                        .setName(getFlameItemName())
                        .setLore("",
                                " &e★ &7Ten przedmiot podpala wszystkich w obrębie 5 bloków.",
                                " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                                " &a✔ Kliknij aby użyć gadżetu.",
                                ""
                        )
                        .build();
                p.getInventory().addItem(item);
                p.updateInventory();
                break;
            case PUSHUP:
                item = new ItemBuilder(Material.FIREWORK_ROCKET)
                        .setName(getPushUpItemName())
                        .setLore("",
                                " &e★ &7Ten przedmiot wyrzuca cię w góre.",
                                " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                                " &a✔ Kliknij aby użyć gadżetu.",
                                ""
                        )
                        .build();
                p.getInventory().addItem(item);
                p.updateInventory();
                break;
            case BOOST:
                item = new ItemBuilder(Material.NAME_TAG)
                        .setName(getBoostItemName())
                        .setLore("",
                                " &e★ &7Ten przedmiot nadaje BOOST do przodu.",
                                " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                                " &a✔ Kliknij aby użyć gadżetu.",
                                ""
                        )
                        .build();
                p.getInventory().addItem(item);
                p.updateInventory();
                break;
            case FLY:
                item = new ItemBuilder(Material.FEATHER)
                        .setName(getBoostItemName())
                        .setLore("",
                                " &e★ &7Ten przedmiot nadaje wszystkim efekt lewitacji w obrębie 5 bloków.",
                                " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                                " &a✔ Kliknij aby użyć gadżetu.",
                                ""
                        )
                        .build();
                p.getInventory().addItem(item);
                p.updateInventory();
                break;
            case JUMP:
                item = new ItemBuilder(Material.SLIME_BALL)
                        .setName(getBoostItemName())
                        .setLore("",
                                " &e★ &7Ten przedmiot nadaje efekt wysokiego skoku 4",
                                " &c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                                " &a✔ Kliknij aby użyć gadżetu.",
                                ""
                        )
                        .build();
                p.getInventory().addItem(item);
                p.updateInventory();
                break;
            default:
                break;
        }
    }
}
