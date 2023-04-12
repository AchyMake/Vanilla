package net.achymake.vanilla.commands.balance;

import net.achymake.vanilla.api.EconomyProvider;
import net.achymake.vanilla.files.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BalanceCommand implements CommandExecutor, TabCompleter {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0) {
                Message.sendMessage(player,"Balance: " + EconomyProvider.getEconomy(player));
            }
            if (args.length == 1) {
                if (player.hasPermission("vanilla.command.balance.others")){
                    Player target = player.getServer().getPlayerExact(args[0]);
                    if (target != null){
                        Message.sendMessage(player,target.getName() + " balance: " + EconomyProvider.getEconomy(target));
                    }else{
                        Message.sendMessage(player,args[0] + " is currently offline");
                    }
                }
            }
        }
        return true;
    }
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            if (sender.hasPermission("vanilla.command.balance.others")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
            }
        }
        return commands;
    }
}