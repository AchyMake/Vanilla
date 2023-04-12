package net.achymake.vanilla.commands.eco;

import org.bukkit.entity.Player;

public abstract class EcoSubCommand {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getSyntax();
    public abstract void perform(Player player, String args[]);
}
