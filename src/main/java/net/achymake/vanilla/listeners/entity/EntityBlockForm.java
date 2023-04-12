package net.achymake.vanilla.listeners.entity;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.files.WorldConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.EntityBlockFormEvent;

public class EntityBlockForm implements Listener {
    public EntityBlockForm(Vanilla plugin) {
        Vanilla.getInstance().getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntitySpawnEvent(EntityBlockFormEvent event) {
        if (!new WorldConfig(Vanilla.getInstance()).isDisabled("block-form."+event.getEntity().getType()))return;
        event.setCancelled(true);
    }
}