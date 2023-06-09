package me.flugel.essentials.objects;

import me.flugel.essentials.Main;
import org.bukkit.entity.Player;

public class Tpa {
    private Player player;
    private Player target;
    private int timing;


    public Tpa(Player player, Player target) {
        this.player = player;
        this.target = target;
        this.timing = 60;
        Main.getInstance().getTpaManager().getTpaArrayList().add(this);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }

    public void lowerTiming(){
        if(this.timing > 0){
            this.timing--;
        }
    }
}
