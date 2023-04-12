package net.achymake.vanilla.commands.main.sub;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.api.EconomyProvider;
import net.achymake.vanilla.commands.main.VanillaSubCommand;
import net.achymake.vanilla.files.Message;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class Homes extends VanillaSubCommand {
    @Override
    public String getName() {
        return "homes";
    }

    @Override
    public String getDescription() {
        return "homes settings";
    }

    @Override
    public String getSyntax() {
        return "/vanilla homes default/cost 1/750.0";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 3) {
            if (args[1].equalsIgnoreCase("max-homes")){
                Integer maxHomes = Integer.valueOf(args[2]);
                Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("homes.max-homes"), PersistentDataType.INTEGER,maxHomes);
                Message.sendMessage(player,"Max homes changed to "+ Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("homes.max-homes"),PersistentDataType.INTEGER));
            }
            if (args[1].equalsIgnoreCase("cost")){
                Double homeCost = Double.valueOf(args[2]);
                Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("homes.cost"), PersistentDataType.DOUBLE,homeCost);
                Message.sendMessage(player,"Homes cost now "+ EconomyProvider.getFormat(Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("homes.cost"),PersistentDataType.DOUBLE)));
            }
        }
    }
}