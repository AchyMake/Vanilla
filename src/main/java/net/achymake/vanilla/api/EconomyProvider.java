package net.achymake.vanilla.api;

import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.files.Message;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.ServicePriority;

import java.text.DecimalFormat;

public class EconomyProvider {
    public static String getEconomy(Player player) {
        return getFormat(player.getPersistentDataContainer().get(NamespacedKey.minecraft("account"), PersistentDataType.DOUBLE));
    }
    public static Double getEconomyRaw(Player player) {
        return player.getPersistentDataContainer().get(NamespacedKey.minecraft("account"), PersistentDataType.DOUBLE);
    }
    public static void addEconomy(Player player, double amount) {
        double newAmount = amount + player.getPersistentDataContainer().get(NamespacedKey.minecraft("account"),PersistentDataType.DOUBLE);
        player.getPersistentDataContainer().set(NamespacedKey.minecraft("account"),PersistentDataType.DOUBLE,newAmount);
    }
    public static void removeEconomy(Player player, double amount) {
        double newAmount = player.getPersistentDataContainer().get(NamespacedKey.minecraft("account"),PersistentDataType.DOUBLE) - amount;
        player.getPersistentDataContainer().set(NamespacedKey.minecraft("account"),PersistentDataType.DOUBLE,newAmount);
    }
    public static void setEconomy(Player player, double amount) {
        player.getPersistentDataContainer().set(NamespacedKey.minecraft("account"),PersistentDataType.DOUBLE,amount);
    }
    public static void resetEconomy(Player player) {
        player.getPersistentDataContainer().set(NamespacedKey.minecraft("account"),PersistentDataType.DOUBLE,Vanilla.getWorldConfig().getEconomyStartingBalance());
    }
    public static String getFormat(Double value) {
        String format = Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("economy.format"),PersistentDataType.STRING);
        DecimalFormat balance = new DecimalFormat(format);
        String formatted = balance.format(value);
        return Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("economy.currency"),PersistentDataType.STRING) + formatted;
    }
    public void setup(Vanilla plugin) {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") != null) {
            plugin.getServer().getServicesManager().register(Economy.class, new VaultEconomyProvider(plugin), plugin, ServicePriority.Normal);
            Message.sendLog("hooked to Vault");
        } else {
            Message.sendLog("You have to install 'Vault'");
            plugin.getPluginLoader().disablePlugin(plugin);
        }
        if (plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PlaceholderProvider().register();
        }else{
            Message.sendLog("You have to install 'PlaceholderAPI'");
            plugin.getPluginLoader().disablePlugin(plugin);
        }
    }
}