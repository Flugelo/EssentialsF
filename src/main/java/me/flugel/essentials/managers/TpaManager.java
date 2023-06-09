package me.flugel.essentials.managers;

import me.flugel.essentials.objects.Tpa;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TpaManager {
    private ArrayList<Tpa> tpaArrayList;

    public TpaManager() {
        this.tpaArrayList = new ArrayList<>();
    }

    public Tpa getByTarget(Player player) {
        for (Tpa tpa : this.tpaArrayList) {
            if (tpa.getTarget().equals(player))
                return tpa;
        }
        return null;
    }

    public Tpa getByPlayer(Player player) {
        for (Tpa tpa : this.tpaArrayList) {
            if (tpa.getPlayer().equals(player))
                return tpa;
        }
        return null;
    }

    public ArrayList<Tpa> getTpaArrayList() {
        return tpaArrayList;
    }
}
