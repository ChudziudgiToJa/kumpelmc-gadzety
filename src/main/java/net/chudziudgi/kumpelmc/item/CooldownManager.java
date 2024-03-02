package net.chudziudgi.kumpelmc.item;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private Map<UUID, Long> cooldowns = new HashMap<>();

    public boolean hasCooldown(Player player) {
        return cooldowns.containsKey(player.getUniqueId()) && System.currentTimeMillis() < cooldowns.get(player.getUniqueId());
    }

    public void addCooldown(Player player, long durationInSeconds) {
        long currentTime = System.currentTimeMillis();
        long cooldownTime = currentTime + durationInSeconds * 1000;
        cooldowns.put(player.getUniqueId(), cooldownTime);
    }
}