package me.flugel.essentials.schaduler;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.WayStone.WayStone;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class AltoSaveWayStone extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(),() ->{

            ArrayList<WayStone> wayStoneArrayList = Main.getInstance().getWayStoneManager().getWayStoneArrayList();

            for (WayStone wayStone : wayStoneArrayList) {
                wayStone.update();
            }

        });
    }
}
