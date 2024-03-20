package net.chudziudgi.kumpelmc;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import net.chudziudgi.kumpelmc.util.ChatUtil;
import net.chudziudgi.kumpelmc.util.SchematicUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import static net.chudziudgi.kumpelmc.Gadget.GADGETS_NAMESPACE;
import static org.bukkit.persistence.PersistentDataType.STRING;

public class GadgetUseController implements Listener {
    public final double flatDistance(@NotNull Location a, @NotNull Location b) {
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2)) + Math.pow(b.getZ() - a.getZ(), 2);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEggThrow(ProjectileHitEvent event) {
        Projectile entity = event.getEntity();
        EntityType type = entity.getType();

        if (type != EntityType.EGG) return;

        Egg egg = (Egg) entity;
        if (!egg.getItem().getItemMeta().getPersistentDataContainer().has(GADGETS_NAMESPACE)) return;

        Location location = event.getEntity().getLocation();

        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionManager = regionContainer.get(BukkitAdapter.adapt(egg.getWorld()));
        ApplicableRegionSet applicableRegions = regionManager.getApplicableRegions(BlockVector3.at(location.getX(), location.getY(), location.getZ()));

        if (applicableRegions.getRegions().stream().noneMatch(it -> it.getId().equals("pvp"))) {
            return;
        }

        if (event.getHitEntity() instanceof Player) {
            Player player = (Player) event.getHitEntity();
            location = player.getLocation();
        }

        try {
            SchematicUtil.pasteSchematic(location.toCenterLocation(), "kula.schem");
        } catch (Exception ignored) {
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onSnowBallThrow(ProjectileHitEvent event) {
        Projectile entity = event.getEntity();
        EntityType type = entity.getType();

        if (type != EntityType.SNOWBALL) return;

        Snowball snowball = (Snowball) entity;
        if (!snowball.getItem().getItemMeta().getPersistentDataContainer().has(GADGETS_NAMESPACE)) return;

        Location location = event.getEntity().getLocation();

        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionManager = regionContainer.get(BukkitAdapter.adapt(snowball.getWorld()));
        ApplicableRegionSet applicableRegions = regionManager.getApplicableRegions(BlockVector3.at(location.getX(), location.getY(), location.getZ()));

        if (applicableRegions.getRegions().stream().noneMatch(it -> it.getId().equals("pvp"))) {
            return;
        }

        if (event.getHitEntity() instanceof Player) {
            Player player = (Player) event.getHitEntity();
            location = player.getLocation();
        }

        try {
            SchematicUtil.pasteSchematic(location.toCenterLocation(), "cobweb.schem");
        } catch (Exception ignored) {
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (!action.equals(Action.RIGHT_CLICK_AIR) && !action.equals(Action.RIGHT_CLICK_BLOCK)) return;

        if (event.getClickedBlock() != null) return;

        EquipmentSlot hand = event.getHand(); // DETERMINE USED HAND: CAN BE MAIN OR OFFHAND
        assert hand != null;
        ItemStack item = player.getEquipment().getItem(hand);
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return;


        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        boolean hasGadgetNamespace = persistentDataContainer.has(GADGETS_NAMESPACE);

        if (!hasGadgetNamespace) return;

        event.setCancelled(true);

        Material type = item.getType();
        if (player.hasCooldown(type)) return;

        Location location = player.getLocation();

        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionManager = regionContainer.get(BukkitAdapter.adapt(player.getWorld()));
        ApplicableRegionSet applicableRegions = regionManager.getApplicableRegions(BlockVector3.at(location.getX(), location.getY(), location.getZ()));

        if (applicableRegions.getRegions().stream().noneMatch(it -> it.getId().equals("pvp"))) {
            ChatUtil.sendTitle(player, "", "&cNie możesz tego użyć na spawnie!", 5,5,5);
            return;
        }

        int seconds = 20 * 20;
        player.setCooldown(type, seconds);

        // THAT REMOVES ONE ITEM FROM HAND
        item.subtract();

        String enumValue = persistentDataContainer.get(GADGETS_NAMESPACE, STRING);
        Gadget gadget = Gadget.valueOf(enumValue);

        // CONSUME GADGET EVENT
        gadget.getGadgetEvent().accept(player);

        player.playSound(player.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 1f, 1f);
        ChatUtil.sendTitle(player, "", "&aUżyto gadzetu!", 5, 5, 5);
    }

    @EventHandler
    public void onPlayerPlaceBlock(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null || item.getType() == Material.AIR) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        boolean hasGadgetNamespace = persistentDataContainer.has(GADGETS_NAMESPACE);

        if (hasGadgetNamespace) event.setCancelled(true);
    }
    @EventHandler
    public void onPlayerPlaceInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null || item.getType() == Material.AIR) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        boolean hasGadgetNamespace = persistentDataContainer.has(GADGETS_NAMESPACE);

        if (hasGadgetNamespace) event.setCancelled(true);
    }
}
