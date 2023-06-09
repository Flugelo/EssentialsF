package me.flugel.essentials.listeners.waystone;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.WayStone.WayStone;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SalveAoSair implements Listener {
    @EventHandler
    public void aoSair(PlayerQuitEvent e){
        WayStone byOwner = Main.getInstance().getWayStoneManager().getByOwner(e.getPlayer().getName());
        if(byOwner != null){
            byOwner.update();
        }
    }
}
