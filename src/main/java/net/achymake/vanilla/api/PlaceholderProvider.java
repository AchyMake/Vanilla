package net.achymake.vanilla.api;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.achymake.vanilla.Vanilla;
import net.achymake.vanilla.files.Message;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderProvider extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "players";
    }
    @Override
    public String getAuthor() {
        return "AchyMake";
    }
    @Override
    public String getVersion() {
        return Vanilla.getInstance().getDescription().getVersion();
    }
    @Override
    public boolean canRegister() {
        return true;
    }
    @Override
    public boolean register() {
        return super.register();
    }
    @Override
    public boolean persist() {
        return true;
    }
    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null){
            return "";
        }
        if (params.equals("name")) {
            if (player.getDisplayName().equals(player.getName())){
                return Message.color(player.getName());
            }else{
                return Message.color(player.getDisplayName());
            }
        }
        if (params.equals("vanished")) {
            if (Vanilla.getPlayerConfig().isVanished(player)){
                return Message.color("&aTrue&f");
            } else {
                return Message.color("&cFalse&f");
            }
        }
        if (params.equals("online_players")) {
            return String.valueOf(player.getServer().getOnlinePlayers().size() - Vanilla.getPlayerConfig().getVanished().size());
        }
        if (params.equals("account")){
            return EconomyProvider.getEconomy(player);
        }
        return super.onPlaceholderRequest(player, params);
    }
}