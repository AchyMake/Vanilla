package net.achymake.vanilla.commands.main;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.commands.main.sub.Economy;
import net.achymake.vanilla.commands.main.sub.Disable;
import net.achymake.vanilla.commands.main.sub.Homes;
import net.achymake.vanilla.commands.main.sub.Reset;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class VanillaCommand implements CommandExecutor, TabCompleter {
    public ArrayList<VanillaSubCommand> vanillaSubCommands = new ArrayList<>();
    public VanillaCommand(){
        vanillaSubCommands.add(new Economy());
        vanillaSubCommands.add(new Disable());
        vanillaSubCommands.add(new Homes());
        vanillaSubCommands.add(new Reset());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
            }else{
                for (VanillaSubCommand commands : vanillaSubCommands){
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
            commands.add("economy");
            commands.add("disable");
            commands.add("homes");
            commands.add("reset");
        }
        if (args.length == 2){
            if (args[0].equalsIgnoreCase("economy")){
                commands.add("currency");
                commands.add("format");
                commands.add("starting-balance");
            }
            if (args[0].equalsIgnoreCase("homes")){
                commands.add("max-homes");
                commands.add("cost");
            }
            if (args[0].equalsIgnoreCase("disable")){
                commands.add("block-form");
                commands.add("change-block");
                commands.add("explode");
                commands.add("spawn");
            }
            if (args[0].equalsIgnoreCase("reset")){
                for (Player players : sender.getServer().getOnlinePlayers()){
                    commands.add(players.getName());
                }
            }
        }
        if (args.length == 3){
            if (args[0].equalsIgnoreCase("economy")){
                if (args[1].equalsIgnoreCase("currency")){
                    commands.add(Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("economy.currency"), PersistentDataType.STRING));
                }
                if (args[1].equalsIgnoreCase("format")){
                    commands.add(Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("economy.format"), PersistentDataType.STRING));
                }
                if (args[1].equalsIgnoreCase("starting-balance")){
                    commands.add(String.valueOf(Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("economy.starting-balance"), PersistentDataType.DOUBLE)));
                }
            }
            if (args[0].equalsIgnoreCase("homes")){
                if (args[1].equalsIgnoreCase("max-homes")){
                    commands.add(String.valueOf(Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("homes.max-homes"), PersistentDataType.INTEGER)));
                }
                if (args[1].equalsIgnoreCase("cost")){
                    commands.add(String.valueOf(Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("homes.cost"), PersistentDataType.DOUBLE)));
                }
            }
            if (args[0].equalsIgnoreCase("disable")){
                for (EntityType entityTypes : EntityType.values()){
                    commands.add(entityTypes.toString().toLowerCase());
                }
            }
        }
        if (args.length == 4){
            if (args[0].equalsIgnoreCase("disable")){
                if (args[1].equalsIgnoreCase("block-form")){
                    commands.add(String.valueOf(Vanilla.getWorldConfig().isDisabled("block-form."+args[2])));
                }
                if (args[1].equalsIgnoreCase("change-block")){
                    commands.add(String.valueOf(Vanilla.getWorldConfig().isDisabled("change-block."+args[2])));
                }
                if (args[1].equalsIgnoreCase("explode")){
                    commands.add(String.valueOf(Vanilla.getWorldConfig().isDisabled("explode."+args[2])));
                }
                if (args[1].equalsIgnoreCase("spawn")){
                    commands.add(String.valueOf(Vanilla.getWorldConfig().isDisabled("spawn."+args[2])));
                }
            }
        }
        return commands;
    }
}