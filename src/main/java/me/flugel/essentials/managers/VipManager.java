package me.flugel.essentials.managers;

import me.flugel.essentials.objects.AtivarVip;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VipManager {
    private ArrayList<AtivarVip> vips;

    public VipManager() {
        this.vips = new ArrayList<>();
    }

    public AtivarVip getByVip(String playerName){
        for (AtivarVip vip : this.vips) {
            if(vip.getPlayerName().equalsIgnoreCase(playerName)){
                return vip;
            }
        }
        return null;
    }

    public ArrayList<AtivarVip> getVips() {
        return vips;
    }
}
