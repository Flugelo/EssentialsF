package me.flugel.essentials.objects;

import me.flugel.essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SolicitationWarp {
    private String jogador;
    private Warps warps;

    public SolicitationWarp(String jogador, Warps warps) {
        this.jogador = jogador;
        this.warps = warps;

        Main.getInstance().getSolicitationWarpManager().getSolicitation().add(this);
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public Warps getWarps() {
        return warps;
    }

    public void setWarps(Warps warps) {
        this.warps = warps;
    }


}
