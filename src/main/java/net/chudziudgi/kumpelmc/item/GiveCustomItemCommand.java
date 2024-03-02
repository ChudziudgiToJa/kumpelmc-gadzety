package net.chudziudgi.kumpelmc.item;

import net.chudziudgi.kumpelmc.util.ChatUtil;
import net.chudziudgi.kumpelmc.util.ItemEnum;
import net.chudziudgi.kumpelmc.util.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCustomItemCommand implements CommandExecutor {
    private final ItemMenu itemMenu;

    public GiveCustomItemCommand(ItemMenu itemMenu) {
        this.itemMenu = itemMenu;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Ta komenda może być używana tylko przez graczy!");
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("kumpelmc.give.items")) {
            player.sendMessage(ChatColor.RED + "Nie posiadasz permisji do użycia tej komendy");
            return false;
        }
        itemMenu.createInventory(player);
        return true;
    }
}