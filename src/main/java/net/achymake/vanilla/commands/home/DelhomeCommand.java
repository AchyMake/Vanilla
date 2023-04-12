package net.achymake.vanilla.commands.home;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.files.Message;
import net.achymake.vanilla.files.PlayerConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DelhomeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 1){
                PlayerConfig playerConfig = Vanilla.getPlayerConfig();
                Player player = (Player) sender;
                if (playerConfig.getHomes(player).contains(args[0])){
                    playerConfig.removeHome(player,args[0]);
                    Message.sendMessage(player, args[0]+" has been removed");
                }else{
                    Message.sendMessage(player,args[0]+"&c does not exist");
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        Player player = (Player) sender;
        if (args.length == 1) {
            for (String home : Vanilla.getPlayerConfig().getHomes(player)){
                commands.add(home);
            }
        }
        return commands;
    }
}