package me.flugel.essentials.managers;

import me.flugel.essentials.objects.Passivo;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PassivoManager {
    private ArrayList<Passivo> passivos;

    public PassivoManager(){
        this.passivos = new ArrayList<>();
    }

    public Passivo isPassivo(String player){
        for (Passivo passivo : this.passivos) {
            if(passivo.getPlayer().equals(player))
                return passivo;
        }
        return null;
    }

    public ArrayList<Passivo> getPassivos() {
        return passivos;
    }
}
