package net.achymake.vanilla.commands.eco.sub;

import net.achymake.vanilla.api.EconomyProvider;
import net.achymake.vanilla.commands.eco.EcoSubCommand;
import org.bukkit.entity.Player;

public class Remove extends EcoSubCommand {
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "eco settings";
    }

    @Override
    public String getSyntax() {
        return "/eco remove player amount";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 3) {
            Player target = player.getServer().getPlayerExact(args[1]);
            double amount = Double.parseDouble(args[2]);
            if (target != null){
                EconomyProvider.removeEconomy(target,amount);
            }
        }
    }
}