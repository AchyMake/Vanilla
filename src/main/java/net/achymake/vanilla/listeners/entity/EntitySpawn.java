package net.achymake.vanilla.listeners.entity;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.files.WorldConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawn implements Listener {
    public EntitySpawn(Vanilla plugin) {
        Vanilla.getInstance().getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntitySpawnEvent(EntitySpawnEvent event) {
        if (!new WorldConfig(Vanilla.getInstance()).isDisabled("spawn."+event.getEntity().getType().toString().toLowerCase()))return;
        event.setCancelled(true);
    }
}