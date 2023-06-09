package me.flugel.essentials.managers;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.Warps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpsManager {
    private ArrayList<Warps> warps;

    public WarpsManager() {
        this.warps = new ArrayList<>();
    }

    public Warps getWarpsByName(String name) {
        for (Warps warp : this.warps) {
            if (warp.getNome().equalsIgnoreCase(name))
                return warp;
        }
        return null;
    }

    public void addWarp(Warps warps) {
        this.warps.add(warps);
    }


    public void removeWarps(Warps warps) {
        this.warps.remove(warps);
    }

    public ArrayList<Warps> getWarps() {
        return warps;
    }

}
