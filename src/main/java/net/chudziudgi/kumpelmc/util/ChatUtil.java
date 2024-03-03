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

    public static void sendTitle(Player player, String title, String subTitle, int fadeInTime, int stayTime, int fadeOutTime) {
        player.sendTitle(fixColor(title),fixColor(subTitle),fadeInTime,stayTime,fadeOutTime);
    }
}
