package me.flugel.essentials.managers;

import me.flugel.essentials.objects.Vanish;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class InvseeManager {
    private ArrayList<Vanish> invseeArrayList;

    public InvseeManager(){
        this.invseeArrayList = new ArrayList<>();
    }

    public Vanish getByPlayer(Player player){
        for (Vanish invsee : this.invseeArrayList) {
            if(invsee.getPlayer().equals(player))
                return invsee;
        }
        return null;
    }

    public void remove(Vanish player){
        this.invseeArrayList.remove(player);

    }
    public ArrayList<Vanish> getInvseeArrayList() {
        return invseeArrayList;
    }

    public void setInvseeArrayList(ArrayList<Vanish> invseeArrayList) {
        this.invseeArrayList = invseeArrayList;
    }
}
