package me.flugel.essentials.listeners.corpse;

import me.flugel.essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class respawn implements Listener {

    @EventHandler
    public void aonascer(PlayerRespawnEvent e){
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*240, 1));
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*240, 1));
        }, 20 * 2);
    }
}
