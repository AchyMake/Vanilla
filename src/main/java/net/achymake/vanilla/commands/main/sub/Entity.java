package net.achymake.vanilla.commands.main.sub;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.commands.main.VanillaSubCommand;
import org.bukkit.entity.Player;

public class Entity extends VanillaSubCommand {
    @Override
    public String getName() {
        return "entity";
    }

    @Override
    public String getDescription() {
        return "homes settings";
    }

    @Override
    public String getSyntax() {
        return "/vanilla entity block-form/change-block/explode/spawn entity true/false";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 4) {
            Vanilla.getWorldConfig().setBoolean(args[1]+"."+args[2],Boolean.valueOf(args[3]));
        }
    }
}