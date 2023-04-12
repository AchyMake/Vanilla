package net.achymake.vanilla.commands.home;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.api.EconomyProvider;
import net.achymake.vanilla.files.Message;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class HomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (args.length == 0){
                Player player = (Player) sender;
                if (Vanilla.getPlayerConfig().getHomes(player).contains("home")){
                    Vanilla.getPlayerConfig().getHome(player,"home").getChunk().load();
                    Message.sendMessage(player, "Teleporting to home");
                    Vanilla.getInstance().getServer().getScheduler().runTaskLater(Vanilla.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            if (player != null){
                                player.teleport(Vanilla.getPlayerConfig().getHome(player,"home"));
                            }
                        }
                    },20);
                }else{
                    Message.sendMessage(player, "Home has not been set");
                }
            } else if (args.length == 1) {
                Player player = (Player) sender;
                if (args[0].equalsIgnoreCase("buy")){
                    Message.sendMessage(player,"Home cost "+EconomyProvider.getFormat(Vanilla.getWorldConfig().getHomeCost()));
                } else if (args[0].equalsIgnoreCase("bed")) {
                    if (player.getBedSpawnLocation() != null){
                        if (player.hasPermission("vanilla.command.home.bed")){
                            Location location = player.getBedSpawnLocation();
                            location.setPitch(player.getLocation().getPitch());
                            location.setYaw(player.getLocation().getYaw());
                            player.getBedSpawnLocation().getChunk().load();
                            Message.sendMessage(player, "Teleporting to "+args[0]);
                            Vanilla.getInstance().getServer().getScheduler().runTaskLater(Vanilla.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    if (player != null){
                                        player.teleport(location);
                                    }
                                }
                            },20);
                        }
                    }else{
                        Message.sendMessage(player, args[0]+" does not exist");
                    }
                }else if (Vanilla.getPlayerConfig().getHomes(player).contains(args[0])){
                    Vanilla.getPlayerConfig().getHome(player,args[0]).getChunk().load();
                    Message.sendMessage(player, "Teleporting to "+args[0]);
                    Vanilla.getInstance().getServer().getScheduler().runTaskLater(Vanilla.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            if (player != null){
                                player.teleport(Vanilla.getPlayerConfig().getHome(player,args[0]));
                            }
                        }
                    },20);
                }else{
                    Message.sendMessage(player, args[0]+" does not exist");
                }
            }else if (args.length == 2) {
                Player player = (Player) sender;
                String homeName = args[0];
                if (homeName.equalsIgnoreCase("buy")) {
                    int amount = Integer.parseInt(args[1]);
                    if (player.hasPermission("vanilla.command.home.buy")){
                        if (EconomyProvider.getEconomyRaw(player) >= Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("homes.cost"), PersistentDataType.DOUBLE) * amount) {
                            Vanilla.getPlayerConfig().addHomes(player,amount);
                            EconomyProvider.removeEconomy(player,Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("homes.cost"), PersistentDataType.DOUBLE) * amount);
                            Message.sendMessage(player, "You bought "+amount+" homes for "+EconomyProvider.getFormat(Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("homes.cost"), PersistentDataType.DOUBLE)));
                        } else {
                            Message.sendMessage(player, "You do not have "+EconomyProvider.getFormat(Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("homes.cost"), PersistentDataType.DOUBLE)));
                        }
                    }
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
            if (sender.hasPermission("vanilla.command.home.bed")){
                commands.add("bed");
            }
            if (sender.hasPermission("vanilla.command.home.buy")){
                commands.add("buy");
            }
        }
        if (args.length == 2) {
            if (sender.hasPermission("vanilla.command.home.buy")){
                if (args[0].equalsIgnoreCase("buy")){
                    commands.add("1");
                    commands.add("2");
                    commands.add("3");
                }
            }
        }
        return commands;
    }
}