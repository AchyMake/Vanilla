package net.achymake.vanilla.files;

import net.achymake.vanilla.Vanilla;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class WorldConfig {

    private final Vanilla vanilla;
    public WorldConfig(Vanilla vanilla){
        this.vanilla = vanilla;
    }
    public PersistentDataContainer getData(){
        return Vanilla.getInstance().getServer().getWorlds().get(0).getPersistentDataContainer();
    }
    public boolean isSpawnSet(){
        return getData().has(NamespacedKey.minecraft("spawn.world"),PersistentDataType.STRING);
    }
    public void setSpawn(Player player){
        getData().set(NamespacedKey.minecraft("spawn.world"),PersistentDataType.STRING,player.getWorld().getName());
        getData().set(NamespacedKey.minecraft("spawn.x"),PersistentDataType.DOUBLE,player.getLocation().getX());
        getData().set(NamespacedKey.minecraft("spawn.y"),PersistentDataType.DOUBLE,player.getLocation().getY());
        getData().set(NamespacedKey.minecraft("spawn.z"),PersistentDataType.DOUBLE,player.getLocation().getZ());
        getData().set(NamespacedKey.minecraft("spawn.yaw"),PersistentDataType.FLOAT,player.getLocation().getYaw());
        getData().set(NamespacedKey.minecraft("spawn.pitch"),PersistentDataType.FLOAT,player.getLocation().getPitch());
    }
    public Location getSpawn(){
        String worldName = getData().get(NamespacedKey.minecraft("spawn.world"),PersistentDataType.STRING);
        Double x = getData().get(NamespacedKey.minecraft("spawn.x"),PersistentDataType.DOUBLE);
        Double y = getData().get(NamespacedKey.minecraft("spawn.y"),PersistentDataType.DOUBLE);
        Double z = getData().get(NamespacedKey.minecraft("spawn.z"),PersistentDataType.DOUBLE);
        Float yaw = getData().get(NamespacedKey.minecraft("spawn.yaw"),PersistentDataType.FLOAT);
        Float pitch = getData().get(NamespacedKey.minecraft("spawn.pitch"),PersistentDataType.FLOAT);
        return new Location(Vanilla.getInstance().getServer().getWorld(worldName), x,y,z,yaw,pitch);
    }
    public boolean isDisabled(String type){
        return getData().has(NamespacedKey.minecraft(type), PersistentDataType.STRING);
    }
    public void setBoolean(String type,boolean value){
        if (value){
            getData().set(NamespacedKey.minecraft(type), PersistentDataType.STRING,"true");
        }else{
            getData().remove(NamespacedKey.minecraft(type));
        }
    }
}
