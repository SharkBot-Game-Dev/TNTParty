package org.shark.tntparty.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.shark.tntparty.Tntparty;

import java.util.Random;

public class MobAttack implements Listener {
    private final Tntparty plugin;
    private final Random random = new Random();

    public MobAttack(Tntparty plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobAttack(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (!(entityDamageByEntityEvent.getDamager() instanceof Player)) {
            return;
        }

        int probability = random.nextInt(16);

        if (probability == 0) {
            var entity = entityDamageByEntityEvent.getEntity();
            var world = entity.getWorld();
            var entityLocation = entity.getLocation();

            entity.remove();
            plugin.getSpawntnt().spawnTNT(world, entityLocation);
        }
    }
}
