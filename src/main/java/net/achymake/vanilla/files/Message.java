package net.achymake.vanilla.files;

import net.achymake.vanilla.Vanilla;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Message {
    public static void sendMessage(CommandSender sender,String message){
        sender.sendMessage(color(message));
    }
    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&',message);
    }
    public static void sendLog(String message){
        Vanilla.getInstance().getServer().getConsoleSender().sendMessage("["+Vanilla.getInstance().getName()+"] "+message);
    }
}
