package me.flugel.essentials.managers;

import me.flugel.essentials.objects.WayStone.Cordenadas;
import me.flugel.essentials.objects.WayStone.WayStone;
import org.bukkit.Location;

import java.util.ArrayList;

public class WayStoneManager {
    private ArrayList<WayStone> wayStoneArrayList;

    public WayStoneManager() {
        this.wayStoneArrayList = new ArrayList<>();
    }

    public WayStone getByAllWayStone(Location location) {
        for (WayStone wayStone : this.wayStoneArrayList) {
            for (Cordenadas wayStoneLocation : wayStone.getLocations()) {
                if(wayStoneLocation.getLocation().equals(location))
                    return wayStone;
            }

        }
        return null;
    }
    public Cordenadas getByCordenadas(Location location){
        for (WayStone wayStone : this.wayStoneArrayList) {
            for (Cordenadas wayStoneLocation : wayStone.getLocations()) {
                if(wayStoneLocation.getLocation().equals(location))
                    return wayStoneLocation;
            }

        }
        return null;
    }

    public WayStone getByOwner(String owner) {
        for (WayStone wayStone : this.wayStoneArrayList) {
            if (wayStone.getOwnerPlayer().equals(owner))
                return wayStone;

        }
        return null;
    }

    public void removeWayStone(Cordenadas wayStone){
        for (WayStone stone : this.wayStoneArrayList) {
            stone.getLocations().remove(wayStone);
            stone.update();
        }
    }

    public void addWayStone(WayStone wayStone){
        this.wayStoneArrayList.remove(wayStone);
    }

    public ArrayList<WayStone> getWayStoneArrayList() {
        return wayStoneArrayList;
    }
}
