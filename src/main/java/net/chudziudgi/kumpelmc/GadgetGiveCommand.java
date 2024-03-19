package net.chudziudgi.kumpelmc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GadgetGiveCommand implements CommandExecutor {
    private final GadgetInventory gadgetInventory;

    public GadgetGiveCommand(GadgetInventory gadgetInventory) {
        this.gadgetInventory = gadgetInventory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("kumpelmc.give.items")) {
            sender.sendMessage(ChatColor.RED + "Nie posiadasz permisji do użycia tej komendy");
            return false;
        }
        if (args.length == 2) {
            Player playerExact = Bukkit.getPlayerExact(args[0]);
            playerExact.getInventory().addItem(Gadget.valueOf(args[1]).buildItem());
            return true;
        }
        gadgetInventory.createInventory((Player) sender);
        return true;
    }
}