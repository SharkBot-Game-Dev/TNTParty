package org.shark.tntparty.events;

import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.shark.tntparty.Tntparty;

public class ProjectileHit implements Listener {
    private final Tntparty plugin;

    public ProjectileHit(Tntparty plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSnowballLaunch(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Snowball snowball) {

            var location = snowball.getLocation();
            var world = location.getWorld();

            plugin.getSpawntnt().spawnTNT(world, location);
        }
    }
}
