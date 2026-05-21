package org.shark.tntparty.lib;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;

public class SpawnTnt {
    public void spawnTNT(World world, Location blockLocation) {
        TNTPrimed tnt = (TNTPrimed) world.spawnEntity(blockLocation, EntityType.TNT);
        tnt.setFuseTicks(60);
    }
}
