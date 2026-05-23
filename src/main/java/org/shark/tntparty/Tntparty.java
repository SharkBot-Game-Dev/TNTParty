package org.shark.tntparty;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.shark.tntparty.events.MobAttack;
import org.shark.tntparty.events.PlayerInteract;
import org.shark.tntparty.events.ProjectileHit;
import org.shark.tntparty.lib.SpawnTnt;

import java.util.Random;
import java.util.logging.Logger;

public final class Tntparty extends JavaPlugin {
    Logger logger;
    private final Random random = new Random();
    public final SpawnTnt spawntnt = new SpawnTnt();
    public PluginManager pluginManager;

    @Override
    public void onEnable() {
        logger = this.getLogger();
        logger.info("[TNTParty] 起動しました。");

        pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerInteract(this), this);
        pluginManager.registerEvents(new MobAttack(this), this);
        pluginManager.registerEvents(new ProjectileHit(this), this);

        long delayTicks = 0L;
        long periodTicks = 200L;
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {

                    Chunk chunk = player.getLocation().getChunk();

                    int randomX = random.nextInt(16);
                    int randomZ = random.nextInt(16);

                    int blockX = (chunk.getX() << 4) + randomX;
                    int blockZ = (chunk.getZ() << 4) + randomZ;

                    var world = player.getWorld();

                    int highestY = world.getHighestBlockYAt(blockX, blockZ);

                    Location tntLocation = new Location(world, blockX + 0.5, highestY + 1.0, blockZ + 0.5);

                    spawntnt.spawnTNT(world, tntLocation);
                }
            }
        }.runTaskTimer(this, delayTicks, periodTicks);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public SpawnTnt getSpawntnt() {
        return this.spawntnt;
    }
}
