package net.achymake.vanilla.listeners;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.listeners.connection.Join;
import net.achymake.vanilla.listeners.entity.EntityBlockForm;
import net.achymake.vanilla.listeners.entity.EntityChangeBlock;
import net.achymake.vanilla.listeners.entity.EntityExplode;
import net.achymake.vanilla.listeners.entity.EntitySpawn;

public class Events {
    public static void start(Vanilla plugin){
        new Join(plugin);
        new EntityBlockForm(plugin);
        new EntityChangeBlock(plugin);
        new EntityExplode(plugin);
        new EntitySpawn(plugin);
    }
}
