package net.achymake.vanilla.files;

import net.achymake.vanilla.Vanilla;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PlayerConfig {
    private final List<Player> vanished = new ArrayList<>();

    private final Vanilla vanilla;
    public PlayerConfig(Vanilla vanilla){
        this.vanilla = vanilla;
    }
    public PersistentDataContainer getData(Player player){
        return player.getPersistentDataContainer();
    }
    public void create(Player player){
        if (!getData(player).has(NamespacedKey.minecraft("account"),PersistentDataType.DOUBLE)){
            getData(player).set(NamespacedKey.minecraft("account"),PersistentDataType.DOUBLE,Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("economy.starting-balance"),PersistentDataType.DOUBLE));
        }
        if (!getData(player).has(NamespacedKey.minecraft("max-homes"),PersistentDataType.INTEGER)){
            getData(player).set(NamespacedKey.minecraft("max-homes"),PersistentDataType.INTEGER,Vanilla.getWorldConfig().getData().get(NamespacedKey.minecraft("homes.default"),PersistentDataType.INTEGER));
        }
        if (!getData(player).has(NamespacedKey.minecraft("homes"),PersistentDataType.STRING)){
            getData(player).set(NamespacedKey.minecraft("homes"),PersistentDataType.STRING,"");
        }
    }
    public List<String> getHomes(Player player) {
        List<String> homeListed = new ArrayList<>();
        String encodedHomes = getData(player).get(NamespacedKey.minecraft("homes"), PersistentDataType.STRING);
        if (!encodedHomes.isEmpty()) {
            byte[] rawData = Base64.getDecoder().decode(encodedHomes);
            try {
                ByteArrayInputStream io = new ByteArrayInputStream(rawData);
                BukkitObjectInputStream in = new BukkitObjectInputStream(io);
                int homeCount = in.readInt();
                for(int i = 0; i < homeCount; ++i) {
                    homeListed.add((String) in.readObject());
                }
            } catch (ClassNotFoundException | IOException e) {
                Message.sendLog(e.getMessage());
            }
        }
        return homeListed;
    }
    public void addHome(Player player, String home){
        getData(player).set(NamespacedKey.minecraft("home."+home+".world"),PersistentDataType.STRING,player.getWorld().getName());
        getData(player).set(NamespacedKey.minecraft("home."+home+".x"),PersistentDataType.DOUBLE,player.getLocation().getX());
        getData(player).set(NamespacedKey.minecraft("home."+home+".y"),PersistentDataType.DOUBLE,player.getLocation().getY());
        getData(player).set(NamespacedKey.minecraft("home."+home+".z"),PersistentDataType.DOUBLE,player.getLocation().getZ());
        getData(player).set(NamespacedKey.minecraft("home."+home+".yaw"),PersistentDataType.FLOAT,player.getLocation().getYaw());
        getData(player).set(NamespacedKey.minecraft("home."+home+".pitch"),PersistentDataType.FLOAT,player.getLocation().getPitch());
        List<String> homeListed = getHomes(player);
        homeListed.add(home);
        try {
            ByteArrayOutputStream io = new ByteArrayOutputStream();
            BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
            os.writeInt(homeListed.size());
            for (String listedHomes : homeListed){
                os.writeObject(listedHomes);
            }
            os.flush();
            byte[] rawData = io.toByteArray();
            String encodedData = Base64.getEncoder().encodeToString(rawData);
            player.getPersistentDataContainer().set(NamespacedKey.minecraft("homes"), PersistentDataType.STRING,encodedData);
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public void removeHome(Player player, String home){
        List<String> homeListed = getHomes(player);
        homeListed.remove(home);
        getData(player).remove(NamespacedKey.minecraft("home."+home+".world"));
        getData(player).remove(NamespacedKey.minecraft("home."+home+".x"));
        getData(player).remove(NamespacedKey.minecraft("home."+home+".y"));
        getData(player).remove(NamespacedKey.minecraft("home."+home+".z"));
        getData(player).remove(NamespacedKey.minecraft("home."+home+".yaw"));
        getData(player).remove(NamespacedKey.minecraft("home."+home+".pitch"));
        try {
            ByteArrayOutputStream io = new ByteArrayOutputStream();
            BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
            os.writeInt(homeListed.size());
            for (String listedHomes : homeListed){
                os.writeObject(listedHomes);
            }
            os.flush();
            byte[] rawData = io.toByteArray();
            String encodedData = Base64.getEncoder().encodeToString(rawData);
            player.getPersistentDataContainer().set(NamespacedKey.minecraft("homes"),PersistentDataType.STRING,encodedData);
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public Location getHome(Player player, String homeName){
        String worldName = getData(player).get(NamespacedKey.minecraft("home."+homeName+".world"),PersistentDataType.STRING);
        Double x = getData(player).get(NamespacedKey.minecraft("home."+homeName+".x"),PersistentDataType.DOUBLE);
        Double y = getData(player).get(NamespacedKey.minecraft("home."+homeName+".y"),PersistentDataType.DOUBLE);
        Double z = getData(player).get(NamespacedKey.minecraft("home."+homeName+".z"),PersistentDataType.DOUBLE);
        Float yaw = getData(player).get(NamespacedKey.minecraft("home."+homeName+".yaw"),PersistentDataType.FLOAT);
        Float pitch = getData(player).get(NamespacedKey.minecraft("home."+homeName+".pitch"),PersistentDataType.FLOAT);
        return new Location(Vanilla.getInstance().getServer().getWorld(worldName), x, y, z, yaw, pitch);
    }
    public Integer getMaxHomes(Player player){
        return getData(player).get(NamespacedKey.minecraft("max-homes"),PersistentDataType.INTEGER);
    }
    public void addHomes(Player player, Integer integer){
        getData(player).set(NamespacedKey.minecraft("max-homes"),PersistentDataType.INTEGER,getData(player).get(NamespacedKey.minecraft("max-homes"),PersistentDataType.INTEGER) + integer);
    }
    public void removeHomes(Player player, Integer integer){
        getData(player).set(NamespacedKey.minecraft("max-homes"),PersistentDataType.INTEGER,getData(player).get(NamespacedKey.minecraft("max-homes"),PersistentDataType.INTEGER) - integer);
    }
    public boolean isCreated(Player player){
        return getData(player).has(NamespacedKey.minecraft("vanilla"), PersistentDataType.STRING);
    }
    public Double getEconomy(Player player){
        return getData(player).get(NamespacedKey.minecraft("account"), PersistentDataType.DOUBLE);
    }
    public boolean isVanished(Player player) {
        return this.vanished.contains(player);
    }
    public List<Player> getVanished() {
        return this.vanished;
    }
    public Vanilla getPlugin() {
        return vanilla;
    }
}
