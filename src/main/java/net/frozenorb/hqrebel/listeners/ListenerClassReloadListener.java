/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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