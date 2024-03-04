package net.chudziudgi.kumpelmc;

import net.chudziudgi.kumpelmc.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import static net.chudziudgi.kumpelmc.Gadget.GADGETS_NAMESPACE;
import static org.bukkit.persistence.PersistentDataType.STRING;

public class GadgetUseController implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (!action.equals(Action.RIGHT_CLICK_AIR) && !action.equals(Action.RIGHT_CLICK_BLOCK))
            return;

        if (event.getClickedBlock() != null)
            return;

        EquipmentSlot hand = event.getHand(); // DETERMINE USED HAND: CAN BE MAIN OR OFFHAND
        assert hand != null;
        ItemStack item = player.getEquipment().getItem(hand);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        boolean hasGadgetNamespace = persistentDataContainer.has(GADGETS_NAMESPACE);

        if (!hasGadgetNamespace)
            return;

        event.setCancelled(true);

        Material type = item.getType();
        if (player.hasCooldown(type))
            return;

        int seconds = 20 * 10;
        player.setCooldown(type, seconds);

        // THAT REMOVES ONE ITEM FROM HAND
        item.subtract();

        String enumValue = persistentDataContainer.get(GADGETS_NAMESPACE, STRING);
        Gadget gadget = Gadget.valueOf(enumValue);

        // CONSUME GADGET EVENT
        gadget.getGadgetEvent().accept(player);

        player.playSound(player.getLocation(), Sound.ENTITY_CAT_HISS, 1f, 1f);
        ChatUtil.sendTitle(player, "", "&aPrawidłowo użyto gadzetu!", 5, 10, 5);
    }
}
