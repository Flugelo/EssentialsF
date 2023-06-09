package me.flugel.essentials.objects.WayStone;

import org.bukkit.Location;

public class Cordenadas {
    private Location location;
    private String nome;


    public Cordenadas(Location location, String nome) {
        this.location = location;
        this.nome = nome;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
