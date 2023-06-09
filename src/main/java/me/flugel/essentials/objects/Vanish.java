package me.flugel.essentials.objects;

import me.flugel.essentials.Main;
import org.bukkit.entity.Player;

public class Vanish {
    private Player player;

    public Vanish(Player player) {
        this.player = player;
        Main.getInstance().getInvseeManager().getInvseeArrayList().add(this);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
