package net.achymake.vanilla.commands.homes.sub;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.commands.homes.HomesSubCommand;
import net.achymake.vanilla.files.Message;
import org.bukkit.entity.Player;

public class Teleport extends HomesSubCommand {
    @Override
    public String getName() {
        return "teleport";
    }

    @Override
    public String getDescription() {
        return "homes settings";
    }

    @Override
    public String getSyntax() {
        return "/homes teleport player home";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 3) {
            Player target = player.getServer().getPlayerExact(args[1]);
            String homeName = args[2];
            if (target != null){
                if (Vanilla.getPlayerConfig().getHomes(target).contains(homeName)){
                    Vanilla.getPlayerConfig().getHome(target,homeName).getChunk().load();
                    Message.sendMessage(player,"Teleporting to "+homeName+ " of "+target.getName());
                    Vanilla.getInstance().getServer().getScheduler().runTaskLater(Vanilla.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            if (player != null){
                                player.teleport(Vanilla.getPlayerConfig().getHome(target,homeName));
                            }
                        }
                    },20);
                }
            }
        }
    }
}