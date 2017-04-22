package net.frozenorb.hqrebel.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.zeroturnaround.javarebel.ClassEventListener;

public final class StaffMessageClassReloadListener implements ClassEventListener {

    @Override
    public void onClassEvent(int eventType, Class<?> type) {
        ChatColor color = eventType == ClassEventListener.EVENT_LOADED ? ChatColor.GREEN : ChatColor.GOLD;
        String name = type.getSimpleName().isEmpty() ? type.getName() : type.getSimpleName();
        String message = ChatColor.GRAY + "HQRebel: " + color + name;

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("hqrebel.notify")) {
                player.sendMessage(message);
            }
        }
    }

    @Override
    public int priority() {
        return 1;
    }

}