package net.achymake.vanilla.commands.homes;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.commands.homes.sub.Delete;
import net.achymake.vanilla.commands.homes.sub.Teleport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomesCommand implements CommandExecutor, TabCompleter {
    public ArrayList<HomesSubCommand> homesSubCommands = new ArrayList<>();
    public HomesCommand(){
        homesSubCommands.add(new Delete());
        homesSubCommands.add(new Teleport());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
            }else{
                for (HomesSubCommand commands : homesSubCommands){
                    if (args[0].equals(commands.getName())){
                        commands.perform((Player) sender,args);
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
            if (sender.hasPermission("vanilla.command.homes.delete")){
                commands.add("delete");
            }
            if (sender.hasPermission("vanilla.command.homes.teleport")){
                commands.add("teleport");
            }
        }
        if (args.length == 2){
            if (sender.hasPermission("vanilla.command.homes.delete")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
            }
            if (sender.hasPermission("vanilla.command.homes.teleport")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
            }
        }
        if (args.length == 3){
            if (sender.hasPermission("vanilla.homes.delete")){
                if (sender.getServer().getPlayerExact(args[1]) != null){
                    for (String homes : Vanilla.getPlayerConfig().getHomes(sender.getServer().getPlayerExact(args[1]))){
                        commands.add(homes);
                    }
                }
            }
            if (sender.hasPermission("vanilla.homes.teleport")){
                if (sender.getServer().getPlayerExact(args[1]) != null){
                    for (String homes : Vanilla.getPlayerConfig().getHomes(sender.getServer().getPlayerExact(args[1]))){
                        commands.add(homes);
                    }
                }
            }
        }
        return commands;
    }
}