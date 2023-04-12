package net.achymake.vanilla.commands.home;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.files.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SethomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                if (Vanilla.getPlayerConfig().getHomes(player).isEmpty()){
                    Vanilla.getPlayerConfig().addHome(player,"home");
                    Message.sendMessage(player, "Home has been set");
                }else if (Vanilla.getPlayerConfig().getMaxHomes(player) >Vanilla.getPlayerConfig().getHomes(player).size()){
                    Vanilla.getPlayerConfig().addHome(player,"home");
                    Message.sendMessage(player, "Home has been set");
                }else{
                    Message.sendMessage(player, "You have reach your limit");
                }
            }
            if (args.length == 1){
                Player player = (Player) sender;
                if (args[0].equalsIgnoreCase("bed")){
                    Message.sendMessage(player, "You cannot set home for "+args[0]);
                }else if (args[0].equalsIgnoreCase("buy")){
                    Message.sendMessage(player, "You cannot set home for "+args[0]);
                }else{
                    if (Vanilla.getPlayerConfig().getHomes(player).contains(args[0])){
                        Vanilla.getPlayerConfig().addHome(player,args[0]);
                        Message.sendMessage(player, args[0]+" has been set");

                    }else if (Vanilla.getPlayerConfig().getMaxHomes(player) > Vanilla.getPlayerConfig().getHomes(player).size()){
                        Vanilla.getPlayerConfig().addHome(player,args[0]);
                        Message.sendMessage(player, args[0]+" has been set");
                    }else{
                        Message.sendMessage(player, "You have reach your limit");
                    }
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            for (String home : Vanilla.getPlayerConfig().getHomes((Player) sender)){
                commands.add(home);
            }
        }
        return commands;
    }
}