package net.achymake.vanilla.commands.eco.sub;

import net.achymake.vanilla.api.EconomyProvider;
import net.achymake.vanilla.commands.eco.EcoSubCommand;
import org.bukkit.entity.Player;

public class Reset extends EcoSubCommand {
    @Override
    public String getName() {
        return "reset";
    }

    @Override
    public String getDescription() {
        return "eco settings";
    }

    @Override
    public String getSyntax() {
        return "/eco reset player";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 3) {
            Player target = player.getServer().getPlayerExact(args[1]);
            if (target != null){
                EconomyProvider.resetEconomy(target);
            }
        }
    }
}