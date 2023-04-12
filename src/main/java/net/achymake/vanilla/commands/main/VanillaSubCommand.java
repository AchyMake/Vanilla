package net.achymake.vanilla.commands.main;

import org.bukkit.entity.Player;

public abstract class VanillaSubCommand {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getSyntax();
    public abstract void perform(Player player, String args[]);
}
