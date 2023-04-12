package net.achymake.vanilla.files;

import net.achymake.vanilla.Vanilla;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

public class Files {
    public static void setup(){
        worldConfigSetup();
    }

    public static void worldConfigSetup(){
        if (!Vanilla.getWorldConfig().getData().has(NamespacedKey.minecraft("homes.max-homes"), PersistentDataType.INTEGER)){
            Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("homes.max-homes"),PersistentDataType.INTEGER,1);
        }
        if (!Vanilla.getWorldConfig().getData().has(NamespacedKey.minecraft("homes.cost"),PersistentDataType.DOUBLE)){
            Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("homes.cost"),PersistentDataType.DOUBLE,750.0);
        }
        if (!Vanilla.getWorldConfig().getData().has(NamespacedKey.minecraft("economy.currency"),PersistentDataType.STRING)){
            Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("economy.currency"),PersistentDataType.STRING,"$");
        }
        if (!Vanilla.getWorldConfig().getData().has(NamespacedKey.minecraft("economy.format"),PersistentDataType.STRING)){
            Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("economy.format"),PersistentDataType.STRING,"#,##0.00");
        }
        if (!Vanilla.getWorldConfig().getData().has(NamespacedKey.minecraft("economy.starting-balance"),PersistentDataType.DOUBLE)){
            Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("economy.starting-balance"),PersistentDataType.DOUBLE,0.0);
        }
        if (!Vanilla.getWorldConfig().getData().has(NamespacedKey.minecraft("spawn.world"),PersistentDataType.STRING)){
            Location location = Vanilla.getInstance().getServer().getWorlds().get(0).getSpawnLocation();
            Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("spawn.world"),PersistentDataType.STRING,location.getWorld().getName());
            Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("spawn.x"),PersistentDataType.DOUBLE,location.getX());
            Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("spawn.y"),PersistentDataType.DOUBLE,location.getY());
            Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("spawn.z"),PersistentDataType.DOUBLE,location.getZ());
            Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("spawn.yaw"),PersistentDataType.FLOAT,location.getYaw());
            Vanilla.getWorldConfig().getData().set(NamespacedKey.minecraft("spawn.pitch"),PersistentDataType.FLOAT,location.getPitch());
        }
    }
}