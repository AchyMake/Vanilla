package net.achymake.vanilla.commands.spawn;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.files.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnCommand implements CommandExecutor, TabCompleter {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0) {
                if (Vanilla.getWorldConfig().isSpawnSet()){
                    Vanilla.getWorldConfig().getSpawn().getChunk().load();
                    Message.sendMessage(player,"Teleporting to Spawn");
                    Vanilla.getInstance().getServer().getScheduler().runTaskLater(Vanilla.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            if (player != null){
                                player.teleport(Vanilla.getWorldConfig().getSpawn());
                            }
                        }
                    },20);
                }else{
                    Message.sendMessage(player,"Spawn has not been set");
                }
            }
        }
        return true;
    }
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        return commands;
    }
}