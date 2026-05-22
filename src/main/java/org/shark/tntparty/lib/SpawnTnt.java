package org.shark.tntparty.lib;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;

import java.util.Random;

public class SpawnTnt {
    private final Random random = new Random();

    public void spawnTNT(World world, Location blockLocation) {
        int probability = random.nextInt(8);

        if (probability != 0) {
            TNTPrimed tnt = (TNTPrimed) world.spawnEntity(blockLocation, EntityType.TNT);
            tnt.setFuseTicks(60);
        } else {
            world.spawnEntity(blockLocation, EntityType.CREEPER);
        }
    }
}
