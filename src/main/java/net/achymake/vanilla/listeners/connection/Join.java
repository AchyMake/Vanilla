package net.achymake.vanilla.listeners.connection;

import net.achymake.vanilla.Vanilla;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    public Join(Vanilla plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Vanilla.getPlayerConfig().create(player);
    }
}