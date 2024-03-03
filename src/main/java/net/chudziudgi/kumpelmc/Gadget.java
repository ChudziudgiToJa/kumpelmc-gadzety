package net.chudziudgi.kumpelmc;

import net.chudziudgi.kumpelmc.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Consumer;

import java.util.Arrays;

public enum Gadget {
    SPEED("&b&n✪&f&l Oranżada w proszku",
            "&e★ &7Ten przedmiot nadaje szybkość III",
            Material.SUGAR,
            player -> player.addPotionEffect(
                    new PotionEffect(PotionEffectType.SPEED, 20 * 10, 5)
            )
    ),
    FLAME("&b&n✪&f&l Zapalniczka",
            "&e★ &7Ten przedmiot podpala wszystkich w obrębie 5 bloków.",
            Material.BLAZE_POWDER,
            player -> {
                Location playerLocation = player.getLocation();
                Bukkit.getServer().getOnlinePlayers()
                        .stream()
                        .filter(it -> it.getWorld() == player.getWorld())
                        .filter(it -> it.getLocation().distance(playerLocation) <= 5)
                        .filter(it -> it != player)
                        .forEach(target -> target.setFireTicks(100));
            }
    ),
    PUSHUP("&b&n✪&f&l Rakieta",
            "&e★ &7Ten przedmiot wyrzuca cię w góre.",
            Material.FIREWORK_ROCKET,
            player ->
                    player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1.0))
    ),
    BOOST("&b&n✪&f&l Boost",
            "&e★ &7Ten przedmiot nadaje BOOST do przodu.",
            Material.NAME_TAG,
            player -> player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 10))
    ),
    FLY("&b&n✪&f&l Lewitacja",
            "&e★ &7Ten przedmiot nadaje wszystkim efekt lewitacji w obrębie 5 bloków.",
            Material.FEATHER,
            player -> {
                Location playerLocation = player.getLocation();
                Bukkit.getServer().getOnlinePlayers()
                        .stream()
                        .filter(it -> it.getWorld() == player.getWorld())
                        .filter(it -> it.getLocation().distance(playerLocation) <= 5)
                        .filter(it -> it != player)
                        .forEach(target -> player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 30 * 3, 3)));
            }
    ),

    JUMP("&b&n✪&f&l Wysoki skok",
            "&e★ &7Ten przedmiot nadaje efekt wysokiego skoku 4",
            Material.SLIME_BALL,
            player -> {
                Location playerLocation = player.getLocation();
                Bukkit.getServer().getOnlinePlayers()
                        .stream()
                        .filter(it -> it.getWorld() == player.getWorld())
                        .filter(it -> it.getLocation().distance(playerLocation) <= 5)
                        .filter(it -> it != player)
                        .forEach(target -> player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 30 * 3, 3)));
            }
    );

    public static final NamespacedKey GADGETS_NAMESPACE =
            new NamespacedKey("gadgets-plugin", "gadgets-namespace");

    private final String name;
    private final String description;
    private final Material material;
    private final Consumer<Player> gadgetEvent;

    Gadget(String name, String description, Material material, Consumer<Player> gadgetEvent) {
        this.name = name;
        this.description = description;
        this.material = material;
        this.gadgetEvent = gadgetEvent;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Material getMaterial() {
        return material;
    }

    public Consumer<Player> getGadgetEvent() {
        return gadgetEvent;
    }

    public ItemStack buildItem() {
        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName(ChatUtil.fixColor(this.name));
        itemMeta.setLore(Arrays.asList(
                this.description,
                "&c⚠ Pamiętaj każdy gadżet posiada cooldown!",
                "&a✔ Kliknij aby użyć gadżetu."
        ));

        itemMeta.getPersistentDataContainer()
                .set(GADGETS_NAMESPACE, PersistentDataType.STRING, super.name());

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
