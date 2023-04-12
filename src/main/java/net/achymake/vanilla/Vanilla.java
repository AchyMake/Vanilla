package net.achymake.vanilla;

import net.achymake.vanilla.api.EconomyProvider;
import net.achymake.vanilla.commands.Commands;
import net.achymake.vanilla.files.Files;
import net.achymake.vanilla.files.PlayerConfig;
import net.achymake.vanilla.files.WorldConfig;
import net.achymake.vanilla.listeners.Events;
import org.bukkit.plugin.java.JavaPlugin;

public final class Vanilla extends JavaPlugin {
    private static Vanilla instance;
    private static PlayerConfig playerConfig;
    private static WorldConfig worldConfig;
    @Override
    public void onEnable() {
        instance = this;
        playerConfig = new PlayerConfig(this);
        worldConfig = new WorldConfig(this);
        new EconomyProvider().setup(this);
        this.getServer().getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                Files.worldConfigSetup();
            }
        },100);
        Commands.start(this);
        Events.start(this);
    }

    @Override
    public void onDisable() {
        if (playerConfig != null){
            playerConfig.getVanished().clear();
        }
    }
    public static PlayerConfig getPlayerConfig() {
        return playerConfig;
    }
    public static WorldConfig getWorldConfig() {
        return worldConfig;
    }
    public static Vanilla getInstance() {
        return instance;
    }
}
