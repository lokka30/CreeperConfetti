package me.cookiemonster.creeperconfetti;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

public class CreeperExplodeListener implements Listener {

    private final CreeperConfetti instance;

    public CreeperExplodeListener(final CreeperConfetti instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        event.setCancelled(true);

        Location location = event.getLocation().add(new Vector(0, 1, 0));
        World world = location.getWorld();

        assert world != null;

        Firework firework = (Firework) world.spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        fireworkMeta.setPower(0);
        fireworkMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.AQUA, Color.WHITE)
                .flicker(true)
                .build());
        firework.setFireworkMeta(fireworkMeta);

        world.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);

        Bukkit.getScheduler().scheduleSyncDelayedTask(instance, firework::detonate, 1);
    }
}