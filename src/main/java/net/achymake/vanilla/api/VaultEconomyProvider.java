package net.achymake.vanilla.api;

import net.achymake.vanilla.Vanilla;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;
import java.util.List;

public class VaultEconomyProvider implements Economy {
    private final Vanilla vanilla;

    public VaultEconomyProvider(Vanilla plugin) {
        this.vanilla = plugin;
    }
    public boolean isEnabled() {
        return this.vanilla.isEnabled();
    }
    public String getName() {
        return this.vanilla.getName();
    }
    public boolean hasBankSupport() {
        return false;
    }
    public int fractionalDigits() {
        return -1;
    }
    public String format(double amount) {
        return EconomyProvider.getFormat(amount);
    }
    public String currencyNamePlural() {
        return this.currencyNameSingular();
    }
    public String currencyNameSingular() {
        return Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("economy.currency"), PersistentDataType.STRING);
    }
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        if (Vanilla.getInstance().getServer().getOfflinePlayer(offlinePlayer.getUniqueId()).isOnline()){
            return Vanilla.getPlayerConfig().isCreated(Vanilla.getInstance().getServer().getPlayer(offlinePlayer.getUniqueId()));
        }else{
            return false;
        }
    }
    public boolean hasAccount(String playerName) {
        if (Vanilla.getInstance().getServer().getOfflinePlayer(playerName).isOnline()){
            return Vanilla.getPlayerConfig().isCreated(Vanilla.getInstance().getServer().getPlayer(playerName));
        }else{
            return false;
        }
    }
    public boolean hasAccount(String playerName, String worldName) {
        return this.hasAccount(playerName);
    }
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return this.hasAccount(player);
    }
    public double getBalance(OfflinePlayer offlinePlayer) {
        if (Vanilla.getInstance().getServer().getOfflinePlayer(offlinePlayer.getUniqueId()).isOnline()){
            return EconomyProvider.getEconomyRaw(Vanilla.getInstance().getServer().getPlayer(offlinePlayer.getUniqueId()));
        }else{
            return 0.0;
        }
    }
    public double getBalance(String playerName) {
        if (Vanilla.getInstance().getServer().getOfflinePlayer(playerName).isOnline()){
            return EconomyProvider.getEconomyRaw(Vanilla.getInstance().getServer().getPlayer(playerName));
        }else{
            return 0.0;
        }
    }
    public double getBalance(String playerName, String world) {
        return this.getBalance(playerName);
    }
    public double getBalance(OfflinePlayer player, String world) {
        return this.getBalance(player);
    }
    public boolean has(OfflinePlayer offlinePlayer, double amount) {
        if (offlinePlayer.isOnline()){
            return EconomyProvider.getEconomyRaw(Vanilla.getInstance().getServer().getPlayer(offlinePlayer.getUniqueId())) > amount;
        }else{
            return false;
        }
    }
    public boolean has(String playerName, double amount) {
        if (Vanilla.getInstance().getServer().getOfflinePlayer(playerName).isOnline()){
            return EconomyProvider.getEconomyRaw(Vanilla.getInstance().getServer().getPlayer(playerName)) > amount;
        }else{
            return false;
        }
    }
    public boolean has(String playerName, String worldName, double amount) {
        return this.has(playerName, amount);
    }

    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return this.has(player, amount);
    }
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {
        if (Vanilla.getInstance().getServer().getOfflinePlayer(offlinePlayer.getUniqueId()).isOnline()){
            Player player = Vanilla.getInstance().getServer().getPlayer(offlinePlayer.getUniqueId());
            EconomyProvider.removeEconomy(player,amount);
            return new EconomyResponse(amount, this.getBalance(player), EconomyResponse.ResponseType.SUCCESS, null);
        }else{
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player cannot be offline!");
        }
    }
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        if (Vanilla.getInstance().getServer().getPlayerExact(playerName) != null){
            Player player = Vanilla.getInstance().getServer().getPlayerExact(playerName);
            EconomyProvider.removeEconomy(player,amount);
            return new EconomyResponse(amount, this.getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, null);
        }else{
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player cannot be offline!");
        }
    }

    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return this.withdrawPlayer(playerName, amount);
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return this.withdrawPlayer(player, amount);
    }

    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
        if (Vanilla.getInstance().getServer().getOfflinePlayer(offlinePlayer.getUniqueId()).isOnline()){
            Player player = Vanilla.getInstance().getServer().getPlayer(offlinePlayer.getUniqueId());
            EconomyProvider.addEconomy(player,amount);
            return new EconomyResponse(amount, this.getBalance(player), EconomyResponse.ResponseType.SUCCESS, null);
        }else{
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player cannot be offline!");
        }
    }

    public EconomyResponse depositPlayer(String playerName, double amount) {
        if (Vanilla.getInstance().getServer().getPlayerExact(playerName) != null){
            Player player = Vanilla.getInstance().getServer().getPlayerExact(playerName);
            EconomyProvider.addEconomy(player,amount);
            return new EconomyResponse(amount, this.getBalance(player), EconomyResponse.ResponseType.SUCCESS, null);
        }else{
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Player cannot be offline!");
        }
    }

    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return this.depositPlayer(playerName, amount);
    }

    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return this.depositPlayer(player, amount);
    }
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        if (offlinePlayer.isOnline()){
            Vanilla.getPlayerConfig().create(Vanilla.getInstance().getServer().getPlayer(offlinePlayer.getUniqueId()));
        }
        return true;
    }
    public boolean createPlayerAccount(String playerName) {
        if (Vanilla.getInstance().getServer().getPlayerExact(playerName) != null){
            Vanilla.getPlayerConfig().create(Vanilla.getInstance().getServer().getPlayerExact(playerName));
        }
        return true;
    }
    public boolean createPlayerAccount(String playerName, String worldName) {
        return this.createPlayerAccount(playerName);
    }
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return this.createPlayerAccount(player);
    }
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse createBank(String name, String player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse deleteBank(String name) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankBalance(String name) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankHas(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankWithdraw(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse bankDeposit(String name, double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankOwner(String name, String playerName) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankMember(String name, String playerName) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Economy does not support bank accounts!");
    }
    public List<String> getBanks() {
        return Collections.emptyList();
    }
}