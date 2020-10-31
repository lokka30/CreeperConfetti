package me.cookiemonster.creeperconfetti.events;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class CreeperConfetti extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new CreeperExplodeListener(this), this);
        new Metrics(this, 9232);
    }

}
