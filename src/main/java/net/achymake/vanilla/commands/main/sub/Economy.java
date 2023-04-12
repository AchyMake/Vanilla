package net.achymake.vanilla.commands.main.sub;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.api.EconomyProvider;
import net.achymake.vanilla.commands.main.VanillaSubCommand;
import net.achymake.vanilla.files.Message;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class Economy extends VanillaSubCommand {
    @Override
    public String getName() {
        return "economy";
    }

    @Override
    public String getDescription() {
        return "homes settings";
    }

    @Override
    public String getSyntax() {
        return "/vanilla economy currency/format/starting-balance 1/750.0";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 3) {
            if (args[1].equalsIgnoreCase("currency")){
                String currency = args[2];
                Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("economy.currency"), PersistentDataType.STRING,currency);
                Message.sendMessage(player,"Currency changed to "+ Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("economy.currency"),PersistentDataType.INTEGER));
            }
            if (args[1].equalsIgnoreCase("format")){
                String format = args[2];
                Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("economy.format"), PersistentDataType.STRING,format);
                Message.sendMessage(player,"Economy format changed to "+ EconomyProvider.getFormat(Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("economy.format"),PersistentDataType.DOUBLE)));
            }
            if (args[1].equalsIgnoreCase("starting-balance")){
                Double startingBalance = Double.valueOf(args[2]);
                Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("economy.starting-balance"), PersistentDataType.DOUBLE,startingBalance);
                Message.sendMessage(player,"Starting balance changed to "+ EconomyProvider.getFormat(Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("economy.starting-balance"),PersistentDataType.DOUBLE)));
            }
        }
    }
}