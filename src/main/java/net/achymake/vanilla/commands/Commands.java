package net.achymake.vanilla.commands;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.commands.balance.BalanceCommand;
import net.achymake.vanilla.commands.eco.EcoCommand;
import net.achymake.vanilla.commands.home.DelhomeCommand;
import net.achymake.vanilla.commands.home.HomeCommand;
import net.achymake.vanilla.commands.home.SethomeCommand;
import net.achymake.vanilla.commands.homes.HomesCommand;
import net.achymake.vanilla.commands.main.VanillaCommand;
import net.achymake.vanilla.commands.spawn.SetSpawnCommand;
import net.achymake.vanilla.commands.spawn.SpawnCommand;

public class Commands {
    public static void start(Vanilla plugin){
        plugin.getCommand("balance").setExecutor(new BalanceCommand());
        plugin.getCommand("eco").setExecutor(new EcoCommand());
        plugin.getCommand("delhome").setExecutor(new DelhomeCommand());
        plugin.getCommand("home").setExecutor(new HomeCommand());
        plugin.getCommand("sethome").setExecutor(new SethomeCommand());
        plugin.getCommand("homes").setExecutor(new HomesCommand());
        plugin.getCommand("vanilla").setExecutor(new VanillaCommand());
        plugin.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        plugin.getCommand("spawn").setExecutor(new SpawnCommand());
    }
}
