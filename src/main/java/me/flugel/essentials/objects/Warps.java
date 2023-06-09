package me.flugel.essentials.objects;


import me.flugel.essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Warps {
    private String nome;
    private org.bukkit.Location location;

    public Warps(String nome, Location location) {
        this.nome = nome;
        this.location = location;
        Main.getInstance().getWarpsManager().addWarp(this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public void insert() {
        MySQL mySQL = Main.getInstance().getMySQL();

        mySQL.openConnection();
        Connection connection = mySQL.getConnection();
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            try {
                double x = location.getX();
                double y = location.getY();
                double z = location.getZ();
                World world = location.getWorld();

                String locationString = x + ":" + y + ":" + z + ":" + world.getName();

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO warps (name, location) VALUES (?, ?)");

                preparedStatement.setString(1, this.nome);
                preparedStatement.setString(2, locationString);

                preparedStatement.execute();

                mySQL.closeConnection();
            } catch (Exception e) {
                mySQL.closeConnection();
                Bukkit.getConsoleSender().sendMessage("§cEssentialsF: Erro ao adicionar uma warp na lista de WARPS");
                e.printStackTrace();
            }
        });
    }

    public void update() {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            try {
                MySQL mySQL = Main.getInstance().getMySQL();

                mySQL.openConnection();


                double x = location.getX();
                double y = location.getY();
                double z = location.getZ();
                World world = location.getWorld();

                String locationString = x + ":" + y + ":" + z + ":" + world.getName();

                Connection connection = mySQL.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE warps SET location=? WHERE name=?");

                preparedStatement.setString(1, locationString);
                preparedStatement.setString(2, this.nome);

                preparedStatement.executeUpdate();

                mySQL.closeConnection();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("§cEssentialsF: Houve um erro ao editar uma warp.");
                e.printStackTrace();
            }
        });

    }

    public void delet(){
        MySQL mySQL = Main.getInstance().getMySQL();
        mySQL.openConnection();
        Connection connection = mySQL.getConnection();
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () ->{
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM warps WHERE name=?");
                preparedStatement.setString(1, this.nome);

                preparedStatement.execute();

                mySQL.closeConnection();
            }catch (Exception e){
                Bukkit.getConsoleSender().sendMessage("§cHouve um erro ao deletar uma warp do banco de dados");
                e.printStackTrace();
            }

        });
    }

}
