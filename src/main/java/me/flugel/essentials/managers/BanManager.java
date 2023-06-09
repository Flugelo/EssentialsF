package me.flugel.essentials.managers;


import me.flugel.essentials.Main;
import me.flugel.essentials.objects.Ban;
import me.flugel.essentials.objects.MySQL;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class BanManager {
    private ArrayList<Ban> banArrayList;

    public BanManager() {
        this.banArrayList = new ArrayList<>();
    }

    public Ban getByBan(String playerName) {
        for (Ban ban : this.banArrayList) {
            if (ban.getName().equalsIgnoreCase(playerName))
                return ban;
        }
        return null;
    }

    public ArrayList<Ban> getBanArrayList() {
        return banArrayList;
    }

    public void deletedForAll(String player){
        MySQL mySQL = Main.getInstance().getMySQL();
        mySQL.openConnection();
        Connection connection = mySQL.getConnection();
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () ->{
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM bans WHERE nick=?");
                preparedStatement.setString(1, player);

                preparedStatement.execute();

                mySQL.closeConnection();
            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }
}
