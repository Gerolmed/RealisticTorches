package de.gerolmed.torched.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@SerializableAs("TorchLocation")
public class TorchLocation implements Cloneable, ConfigurationSerializable {

    private double x,y,z;
    float yaw,pitch;
    private UUID world;

    public TorchLocation(Location loc) {
        this.x = loc.getX();
        this.y = loc.getY();
        this.z = loc.getZ();
        this.yaw = loc.getYaw();
        this.pitch = loc.getPitch();
        this.world = loc.getWorld().getUID();
    }

    public TorchLocation(double x, double y, double z, double yaw, double pitch, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = (float) yaw;
        this.pitch = (float) pitch;
        this.world = UUID.fromString(world);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    public float getYaw() {
        return yaw;
    }
    public float getPitch() {
        return pitch;
    }
    public UUID getWorld() {
        return world;
    }

    public Location toLocation() {
        World w = null;
        try {
            w = Bukkit.getWorld(world);
        } catch (Exception e) {
            return null;
        }

        //System.out.println(x + " "+ y +" "+ z +" "+ yaw +" "+ pitch);
        return new Location(w, x, y, z, yaw, pitch);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> list = new LinkedHashMap<>();

        list.put("x", x);
        list.put("y", y);
        list.put("z", z);
        list.put("yaw", yaw);
        list.put("pitch", pitch);
        list.put("world", world.toString());

        return list;
    }

    public static TorchLocation deserialize(Map<String, Object> args) {
        return new TorchLocation(
                (double) args.get("x"),
                (double) args.get("y"),
                (double) args.get("z"),
                (double) args.get("yaw"),
                (double) args.get("pitch"),
                (String) args.get("world"));

    }
}
