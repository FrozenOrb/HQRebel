package net.frozenorb.hqrebel.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.zeroturnaround.javarebel.ClassEventListener;

public final class ListenerClassReloadListener implements ClassEventListener {

    @Override
    public void onClassEvent(int ignored, Class<?> type) {
        if (!Listener.class.isAssignableFrom(type)) {
            return;
        }

        Listener listener = findInstance(type);

        if (listener != null) {
            HandlerList.unregisterAll(listener);
        } else {
            try {
                listener = (Listener) type.newInstance();
            } catch (Exception ex) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Did you really just add a Listener that does stuff in the constructor?");
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "JRebel is pretty magic, but it's not that magic.");
                return;
            }
        }

        Bukkit.getPluginManager().registerEvents(listener, JavaPlugin.getProvidingPlugin(type));
    }

    @Override
    public int priority() {
        return 1;
    }

    private Listener findInstance(Class<?> type) {
        Listener existing = null;

        for (HandlerList handler : HandlerList.getHandlerLists()) {
            for (RegisteredListener registered : handler.getRegisteredListeners()) {
                Listener regListener = registered.getListener();

                if (regListener.getClass() == type) {
                    existing = regListener;
                }
            }
        }

        return existing;
    }

}