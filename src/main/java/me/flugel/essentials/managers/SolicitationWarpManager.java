package me.flugel.essentials.managers;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.SolicitationWarp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SolicitationWarpManager {
    private ArrayList<SolicitationWarp> solicitation;

    public SolicitationWarpManager() {
        this.solicitation = new ArrayList<>();
    }

    public void onTeleport(SolicitationWarp solicitationJogador) {

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            for (SolicitationWarp solicitationWarp : this.solicitation) {
                if (solicitationWarp.getJogador().equals(solicitationJogador.getJogador())) {

                    Player playerExact = Bukkit.getPlayerExact(solicitationJogador.getJogador());
                    if (playerExact != null) {
                        playerExact.teleport(solicitationJogador.getWarps().getLocation());
                        playerExact.sendMessage("§aTeleportando...");
                    }

                }
            }
            this.solicitation.remove(solicitationJogador);
        }, 20 * 5);

    }

    public boolean checkSolicitation(Player player) {
        String name = player.getName();

        for (SolicitationWarp solicitationWarp : this.solicitation) {
            if (solicitationWarp.getJogador().equals(player.getName()))
                return false;
        }
        return true;
    }

    public ArrayList<SolicitationWarp> getSolicitation() {
        return solicitation;
    }
}
