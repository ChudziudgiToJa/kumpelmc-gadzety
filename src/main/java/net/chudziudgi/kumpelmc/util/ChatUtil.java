package net.chudziudgi.kumpelmc.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtil {

    public static String fixColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean sendMessage(Player p, String s) {
        p.sendMessage(ChatUtil.fixColor(s));
        return true;
    }

    public static boolean sendMessage(CommandSender p, String s) {
        p.sendMessage(ChatUtil.fixColor(s));
        return true;
    }

    public static boolean kick(Player p, String s) {
        p.kickPlayer(ChatUtil.fixColor(s));
        return true;
    }

    public static boolean broadcast(String m, Boolean debug) {
        Bukkit.getOnlinePlayers().forEach(p -> sendMessage(p, m));
        return true;
    }

    public static void sendActionbar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(fixColor(message)));
    }
    public static void sendTitle(Player player, String title, String subTitle, int fadeInTime, int stayTime, int fadeOutTime) {
        player.sendTitle(fixColor(title),fixColor(subTitle),fadeInTime,stayTime,fadeOutTime);
    }
}
