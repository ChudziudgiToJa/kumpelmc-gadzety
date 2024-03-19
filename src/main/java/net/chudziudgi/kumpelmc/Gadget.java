package net.chudziudgi.kumpelmc;

import net.chudziudgi.kumpelmc.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Consumer;
import org.bukkit.util.Vector;

import java.util.Arrays;

public enum Gadget {
    SPEED("&b&n✪&f&l Oranżada w proszku",
            "&e★ &7Ten przedmiot nadaje szybkość II",
            Material.SUGAR,
            player -> player.addPotionEffect(
                    new PotionEffect(PotionEffectType.SPEED, 20 * 2, 2)
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
                        .forEach(target -> target.setFireTicks(200));
            }
    ),
    PUSHUP("&b&n✪&f&l Rakieta",
            "&e★ &7Ten przedmiot wyrzuca cię w góre.",
            Material.FIREWORK_ROCKET,
            player ->
                    player.setVelocity(player.getLocation().getDirection().multiply(2.5).setY(1.5))
    ),
    BOOST("&b&n✪&f&l Boost",
            "&e★ &7Ten przedmiot nadaje BOOST do przodu.",
            Material.NAME_TAG,
            player -> player.setVelocity(player.getLocation().getDirection().multiply(2))
    ),
    FLY("&b&n✪&f&l Lewitacja",
            "&e★ &7Ten przedmiot nadaje wszystkim efekt lewitacji w obrębie 5 bloków.",
            Material.FEATHER,
            player -> {
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20 * 3, 3));
                Location playerLocation = player.getLocation();
                Bukkit.getServer().getOnlinePlayers()
                        .stream()
                        .filter(it -> it.getWorld() == player.getWorld())
                        .filter(it -> it.getLocation().distance(playerLocation) <= 5)
                        .filter(it -> it != player)
                        .forEach(target -> target.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 30 * 3, 2)));
            }
    ),
    JUMP("&b&n✪&f&l Wysoki skok",
            "&e★ &7Ten przedmiot nadaje efekt wysokiego skoku 4",
            Material.SLIME_BALL,
            player -> player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 5, 4))
    ),
    INSTANT_ARROW(
            "&b&n✪&f&l Natychmiastowa strzała",
            "&e★ &7Wystrzeliwuje strzałe natychmiast!",
            Material.TIPPED_ARROW,
            player -> {
                Vector direction = player.getLocation().getDirection();
                Arrow arrow = player.getWorld().spawn(player.getEyeLocation(), Arrow.class);

                arrow.setCritical(true);
                arrow.setVelocity(direction.multiply(4));

            }
    ),
    DYNAMITE(
            "&b&n✪&f&l Dynamit",
            "&e★ &7Wystrzeliwuje dynamit!",
            Material.RED_CANDLE,
            player -> {
                Vector direction = player.getLocation().getDirection();
                TNTPrimed tntPrimed = player.getWorld().spawn(player.getEyeLocation(), TNTPrimed.class);

                tntPrimed.setYield(5.0F);
                tntPrimed.setFuseTicks(15);
                tntPrimed.setVelocity(direction.multiply(3));
            }
    ),
    HEAL(
            "&b&n✪&f&l Turbo jagody",
            "&e★ &7Leczy cie na maxa!",
            Material.GLOW_BERRIES,
            player -> player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 10, 200))
    ),
    HASTE(
            "&b&n✪&f&l Ciastko kopacz",
            "&e★ &7Ten przedmiot nadaje efekt szybkiego kopania na 2min",
            Material.COOKIE,
            player -> player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 2, 20 * 120))
    ),
    DELPHIN(
            "&b&n✪&f&l Płetwa delfina",
            "&e★ &7Ten przedmiot nadaje efekt szybkiego pływania na 1min",
            Material.KELP,
            player -> player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 2, 20 * 60))
    ),
    BLIDNESS(
            "&b&n✪&f&l Ślepota",
            "&e★ &7Ten przedmiot nadaje wszystkim efekt Oślepienia w obrębie 8 bloków. na 5sek",
            Material.GUNPOWDER,
            player -> {
                Location playerLocation = player.getLocation();
                Bukkit.getServer().getOnlinePlayers()
                        .stream()
                        .filter(it -> it.getWorld() == player.getWorld())
                        .filter(it -> it.getLocation().distance(playerLocation) <= 8)
                        .filter(it -> it != player)
                        .forEach(target -> target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 2)));
            }
    ),
    TEAMPOLINE(
            "&b&n✪&f&l Trampolina",
            "&e★ &7Ten przedmiot podrzuca wszystkich graczy w góre w obrębie 5 bloków.",
            Material.LIME_DYE,
            player -> {
                Location playerLocation = player.getLocation();
                Bukkit.getServer().getOnlinePlayers()
                        .stream()
                        .filter(it -> it.getWorld() == player.getWorld())
                        .filter(it -> it.getLocation().distance(playerLocation) <= 5)
                        .filter(it -> it != player)
                        .forEach(target -> {
                            Vector targetLocation = target.getLocation().toVector();
                            Vector direction = targetLocation.subtract(playerLocation.toVector()).normalize();
                            target.setVelocity(direction.multiply(3).setY(3.5));
                        });
            }
    ),
    POKEBALL("&b&n✪&f&l Pokeball",
            "&e★ &7Jak wyląduje tworzy kulę.",
            Material.EGG,
            player -> {
                Egg egg = player.getWorld().spawn(player.getEyeLocation(), Egg.class);
                ItemStack itemStack = new ItemStack(Material.EGG);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.getPersistentDataContainer().set(new NamespacedKey("gadgets-plugin", "gadgets-namespace"), PersistentDataType.STRING, "POKEBALL");
                itemStack.setItemMeta(itemMeta);
                egg.setItem(itemStack);

                egg.setVelocity(player.getLocation().getDirection().multiply(3.0));
            }
    ),
    TANK(
            "&b&n✪&f&l Rakietnica",
            "&e★ &7Strzela jak szalona :00",
            Material.ECHO_SHARD,
            player -> {
                for (int i = 1; i <= 6; i++) {
                    Bukkit.getScheduler().runTaskLater(GadgetPlugin.getPlugin(GadgetPlugin.class), () -> {
                        Vector direction = player.getLocation().getDirection();
                        TNTPrimed tntPrimed = player.getWorld().spawn(player.getEyeLocation(), TNTPrimed.class);

                        tntPrimed.setYield(5.0F);
                        tntPrimed.setFuseTicks(15);
                        tntPrimed.setVelocity(direction.multiply(3.5));
                    }, 10 * i);
                }
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


        itemMeta.setDisplayName(ChatUtil.fixColor(this.name));
        itemMeta.setLore(Arrays.asList(
                ChatUtil.fixColor(this.description),
                ChatUtil.fixColor("&c⚠ Pamiętaj każdy gadżet posiada cooldown!"),
                ChatUtil.fixColor("&a✔ Kliknij aby użyć gadżetu.")
        ));

        itemMeta.getPersistentDataContainer().set(GADGETS_NAMESPACE, PersistentDataType.STRING, super.name());

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
