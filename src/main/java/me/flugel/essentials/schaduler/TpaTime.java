package me.flugel.essentials.schaduler;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.Tpa;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class TpaTime extends BukkitRunnable {
    @Override
    public void run() {
        if(Main.getInstance().getTpaManager().getTpaArrayList().size() > 0){

            Iterator<Tpa> array = Main.getInstance().getTpaManager().getTpaArrayList().iterator();

            while (array.hasNext()){
                Tpa next = array.next();
                if(next.getTiming() > 0){
                    next.lowerTiming();
                }else{
                    next.getPlayer().sendMessage("§cSeu pedido de teleporte não foi aceito");
                    array.remove();
                }
            }
        }
    }
}
