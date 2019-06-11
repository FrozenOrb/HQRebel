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