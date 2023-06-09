package me.flugel.essentials.listeners;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.Ban;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinServer implements Listener {

    @EventHandler
    public void aoEntrar(PlayerJoinEvent e) {
        Ban byBan = Main.getInstance().getBanManager().getByBan(e.getPlayer().getName());
        if (byBan != null) {
            String reason = byBan.getReason();
            String autor = byBan.getAutor();

            e.getPlayer().kickPlayer("\n" +
                    "§cVocê foi banido do servidor \n" +
                    "\n" +
                    "§ePelo motivo: §f" + reason + "\n" +
                    "§ePelo staff: §f" + autor + "\n" +
                    "\n" +
                    "§6Foi banido injustamente? entre em contato em nosso discord: \n" +
                    "§eDiscord.com");

            e.setJoinMessage(null);
        }


    }
}
