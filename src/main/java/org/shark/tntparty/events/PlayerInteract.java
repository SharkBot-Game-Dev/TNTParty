package org.shark.tntparty.events;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.shark.tntparty.Tntparty;

import java.util.Random;

public class PlayerInteract implements Listener {
    private final Tntparty plugin;
    private final Random random = new Random();

    public PlayerInteract(Tntparty plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent interactEvent) {
        if (interactEvent.getAction() == Action.LEFT_CLICK_BLOCK) {
            int probability = random.nextInt(16);

            if (probability == 0) {
                interactEvent.setCancelled(true);

                Block block = interactEvent.getClickedBlock();

                if (block == null) return;

                block.breakNaturally();

                var blockLocation = block.getLocation();

                var world = block.getWorld();

                plugin.getSpawntnt().spawnTNT(world, blockLocation);
            }
        }
    }
}
