package net.chudziudgi.kumpelmc.item;

import net.chudziudgi.kumpelmc.util.ChatUtil;
import net.chudziudgi.kumpelmc.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ClickItemController implements Listener {

    private final ItemUtil itemUtil;
    private final CooldownManager cooldownManager;

    public ClickItemController(ItemUtil itemUtil, CooldownManager cooldownManager) {
        this.itemUtil = itemUtil;
        this.cooldownManager = cooldownManager;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            if (cooldownManager.hasCooldown(player)){
                ChatUtil.sendMessage(player,"&cBłąd: Odczekaj chwile przed następnym użyciem gadżetu");
                return;
            }

            //SPEED
            if (player.getInventory().getItemInMainHand().getType() == Material.SUGAR && player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatUtil.fixColor(itemUtil.getSpeedItemName()))) {
                event.setCancelled(true);
                player.setCooldown(Material.SUGAR, 20 * 60);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 5));
                itemUtil.itemController(player);
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 1f, 1f);
                ChatUtil.sendTitle(player, "", "&aPrawidłowo użyto gadzetu!", 5, 10, 5);
                cooldownManager.addCooldown(player,60);
                return;
            }
            //ROCKET
            if (player.getInventory().getItemInMainHand().getType() == Material.FIREWORK_ROCKET && player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatUtil.fixColor(itemUtil.getPushUpItemName()))) {
                event.setCancelled(true);
                player.setCooldown(Material.FIREWORK_ROCKET, 20 * 60);
                player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1.0));
                itemUtil.itemController(player);
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 1f, 1f);
                ChatUtil.sendTitle(player, "", "&aPrawidłowo użyto gadzetu!", 5, 10, 5);
                cooldownManager.addCooldown(player,60);
                return;
            }

            //BOOST
            if (player.getInventory().getItemInMainHand().getType() == Material.NAME_TAG && player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatUtil.fixColor(itemUtil.getBoostItemName()))) {
                event.setCancelled(true);
                player.setCooldown(Material.NAME_TAG, 20 * 60);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 10));
                itemUtil.itemController(player);
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 1f, 1f);
                ChatUtil.sendTitle(player, "", "&aPrawidłowo użyto gadzetu!", 5, 10, 5);
                cooldownManager.addCooldown(player,60);
                return;
            }

            //fire
            if (player.getInventory().getItemInMainHand().getType() == Material.BLAZE_POWDER && player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatUtil.fixColor(itemUtil.getFlameItemName()))) {
                event.setCancelled(true);
                player.setCooldown(Material.BLAZE_POWDER, 20 * 60);
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    if (target != player && target.getWorld() == player.getWorld() && target.getLocation().distance(player.getLocation()) <= 5) {
                        target.setFireTicks(100);
                    }
                }
                itemUtil.itemController(player);
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 1f, 1f);
                ChatUtil.sendTitle(player, "", "&aPrawidłowo użyto gadzetu!", 5, 10, 5);
                cooldownManager.addCooldown(player,60);
                return;
            }
            // fly
            if (player.getInventory().getItemInMainHand().getType() == Material.FEATHER && player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatUtil.fixColor(itemUtil.getFlyItemName()))) {
                event.setCancelled(true);
                player.setCooldown(Material.FEATHER, 20 * 60);
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    if (target != player && target.getWorld() == player.getWorld() && target.getLocation().distance(player.getLocation()) <= 5) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 30 * 3, 3));
                    }
                }
                itemUtil.itemController(player);
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 1f, 1f);
                ChatUtil.sendTitle(player, "", "&aPrawidłowo użyto gadzetu!", 5, 10, 5);
                cooldownManager.addCooldown(player,60);
                return;
            }
            // JUMP
            if (player.getInventory().getItemInMainHand().getType() == Material.SLIME_BALL && player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatUtil.fixColor(itemUtil.getJumpItemName()))) {
                event.setCancelled(true);
                player.setCooldown(Material.SLIME_BALL, 20 * 60);
                for (Player target : Bukkit.getServer().getOnlinePlayers()) {
                    if (target != player && target.getWorld() == player.getWorld() && target.getLocation().distance(player.getLocation()) <= 5) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 30 * 3, 3));
                    }
                }
                itemUtil.itemController(player);
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 1f, 1f);
                ChatUtil.sendTitle(player, "", "&aPrawidłowo użyto gadzetu!", 5, 10, 5);
                cooldownManager.addCooldown(player,60);
                return;
            }
        }
    }
}
