package me.flugel.essentials.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class Motd implements Listener {
    @EventHandler
    public void metd(ServerListPingEvent e){
        e.setMotd("    §f§l★             §6NeWorld - §eSURVIVAL           §f§l★" +
                "\n      §fSurvival 1.17.1 - Nova geração de mundo. Venha ver!");
    }
}
