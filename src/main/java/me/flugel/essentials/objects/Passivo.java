package me.flugel.essentials.objects;

import me.flugel.essentials.Main;
import org.bukkit.entity.Player;

public class Passivo {
    private String player;
    private boolean active;

    public Passivo(String player, boolean active) {
        this.player = player;
        this.active = active;
        Main.getInstance().getPassivoManager().getPassivos().add(this);
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
