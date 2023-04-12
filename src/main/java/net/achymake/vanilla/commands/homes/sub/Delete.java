package net.achymake.vanilla.commands.homes.sub;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.commands.homes.HomesSubCommand;
import net.achymake.vanilla.files.Message;
import org.bukkit.entity.Player;

public class Delete extends HomesSubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "homes settings";
    }

    @Override
    public String getSyntax() {
        return "/homes delete player home";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 3) {
            Player target = player.getServer().getPlayerExact(args[1]);
            String homeName = args[2];
            if (target != null){
                if (Vanilla.getPlayerConfig().getHomes(target).contains(homeName)){
                    Vanilla.getPlayerConfig().removeHome(target,homeName);
                    Message.sendMessage(player,homeName+ " has been removed from "+target.getName());
                }
            }
        }
    }
}