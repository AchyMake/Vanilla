package net.achymake.vanilla.commands.main.sub;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.commands.main.VanillaSubCommand;
import net.achymake.vanilla.files.Message;
import org.bukkit.entity.Player;

public class Disable extends VanillaSubCommand {
    @Override
    public String getName() {
        return "disable";
    }

    @Override
    public String getDescription() {
        return "homes settings";
    }

    @Override
    public String getSyntax() {
        return "/vanilla disable block-form/change-block/explode/spawn entity true/false";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 4) {
            Vanilla.getWorldConfig().setBoolean(args[1]+"."+args[2].toUpperCase(),Boolean.valueOf(args[3]));
            Message.sendMessage(player,"Disable "+args[1]+" "+args[2]+" is set to "+args[3]);
        }
    }
}