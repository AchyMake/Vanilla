package net.achymake.vanilla.commands.eco;

import net.achymake.vanilla.commands.eco.sub.Add;
import net.achymake.vanilla.commands.eco.sub.Remove;
import net.achymake.vanilla.commands.eco.sub.Reset;
import net.achymake.vanilla.commands.eco.sub.Set;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EcoCommand implements CommandExecutor, TabCompleter {
    public ArrayList<EcoSubCommand> ecoSubCommands = new ArrayList<>();
    public EcoCommand(){
        ecoSubCommands.add(new Add());
        ecoSubCommands.add(new Remove());
        ecoSubCommands.add(new Reset());
        ecoSubCommands.add(new Set());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
            }else{
                for (EcoSubCommand commands : ecoSubCommands){
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
            if (sender.hasPermission("vanilla.command.eco.add")){
                commands.add("add");
            }
            if (sender.hasPermission("vanilla.command.eco.remove")){
                commands.add("remove");
            }
            if (sender.hasPermission("vanilla.command.eco.reset")){
                commands.add("reset");
            }
            if (sender.hasPermission("vanilla.command.eco.set")){
                commands.add("set");
            }
        }
        if (args.length == 2){
            if (sender.hasPermission("vanilla.command.eco.add")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
            }
            if (sender.hasPermission("vanilla.command.eco.remove")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
            }
            if (sender.hasPermission("vanilla.command.eco.reset")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
            }
            if (sender.hasPermission("vanilla.command.eco.set")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
            }
        }
        return commands;
    }
}