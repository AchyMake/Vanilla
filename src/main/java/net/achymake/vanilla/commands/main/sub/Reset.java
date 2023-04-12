package net.achymake.vanilla.commands.main.sub;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.commands.main.VanillaSubCommand;
import net.achymake.vanilla.files.Message;
import org.bukkit.entity.Player;

public class Reset extends VanillaSubCommand {
    @Override
    public String getName() {
        return "reset";
    }

    @Override
    public String getDescription() {
        return "homes settings";
    }

    @Override
    public String getSyntax() {
        return "/vanilla reset player";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 2) {
            Player target = player.getServer().getPlayerExact(args[1]);
            if (target != null){
                Vanilla.getPlayerConfig().reset(player);
                Message.sendMessage(player,target.getName() + " has been reset");
            }
        }
    }
}