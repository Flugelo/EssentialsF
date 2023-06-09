package me.flugel.essentials.objects.WayStone;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class WayStone {
    private String ownerPlayer;
    private ArrayList<Cordenadas> Cordenadas;
    private ArrayList<String> friends;
    boolean publico;

    public WayStone(String ownerPlayer) {
        this.ownerPlayer = ownerPlayer;
        this.Cordenadas = new ArrayList<>();
        this.publico = false;
        this.friends = new ArrayList<>();
        Main.getInstance().getWayStoneManager().getWayStoneArrayList().add(this);
    }
    public WayStone(String ownerPlayer, boolean cahce) {
        this.ownerPlayer = ownerPlayer;
        this.Cordenadas = new ArrayList<>();
        this.publico = false;
        this.friends = new ArrayList<>();
        Main.getInstance().getWayStoneManager().getWayStoneArrayList().add(this);
    }

    public String getOwnerPlayer() {
        return ownerPlayer;
    }

    public void setOwnerPlayer(String ownerPlayer) {
        this.ownerPlayer = ownerPlayer;
    }


    public void addWayStone(Cordenadas location){
        this.Cordenadas.add(location);
    }

    public ArrayList<Cordenadas> getLocations() {
        return Cordenadas;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setLocations(ArrayList<Cordenadas> locations) {
        this.Cordenadas = locations;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public void removeWayStone(Location location){
        for (me.flugel.essentials.objects.WayStone.Cordenadas cordenada : this.Cordenadas) {

        }
    }


    public void addFriend(String playerName){
        this.friends.add(playerName);
    }

    public void removeFriend(String playerName){
        this.friends.remove(playerName);
    }

    public void insert(){
        MySQL mySQL = Main.getInstance().getMySQL();

        mySQL.openConnection();
        Connection connection = mySQL.getConnection();
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            try {
                StringBuilder builder = new StringBuilder();
                for (Cordenadas cordenada : this.Cordenadas) {
                    int x = (int) cordenada.getLocation().getX();
                    int y = (int) cordenada.getLocation().getY();
                    int z = (int) cordenada.getLocation().getZ();

                    builder.append(x + ">" + y + ">" + z + ">" + cordenada.getLocation().getWorld().getName() + ">" + cordenada.getNome() + "=");
                }
                String locs = builder.toString();

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO waystone (name, public, amount, locations) VALUES (?, ?, ?, ?)");

                preparedStatement.setString(1, this.ownerPlayer);
                preparedStatement.setBoolean(2, this.publico);
                preparedStatement.setInt(3, 1);
                preparedStatement.setString(4, locs);

                preparedStatement.execute();


            } catch (Exception e) {
                mySQL.closeConnection();
                Bukkit.getConsoleSender().sendMessage("§cEssentialsF: Erro ao adicionar WayStone no banco de dados");
                e.printStackTrace();
            }
            mySQL.closeConnection();
        });
    }

    public void update(){
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(),() ->{

            MySQL mySQL = Main.getInstance().getMySQL();
            mySQL.openConnection();
            Connection connection = mySQL.getConnection();
            try {

                StringBuilder builder = new StringBuilder();
                for (Cordenadas cordenada : this.Cordenadas) {
                    int x = (int) cordenada.getLocation().getX();
                    int y = (int) cordenada.getLocation().getY();
                    int z = (int) cordenada.getLocation().getZ();

                    builder.append(x + ">" + y + ">" + z + ">" + cordenada.getLocation().getWorld().getName() + ">" + cordenada.getNome() + "=");
                }
                String locs = builder.toString();

                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE waystone SET public=?, " +
                        "amount=?, " +
                        "locations=? " +
                        "WHERE name=?");

                preparedStatement.setBoolean(1, this.publico);
                preparedStatement.setInt(2, this.Cordenadas.size());
                preparedStatement.setString(3, locs);
                preparedStatement.setString(4, this.getOwnerPlayer());
                preparedStatement.executeUpdate();

            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
