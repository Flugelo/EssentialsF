package me.flugel.essentials.controllers;

import me.flugel.essentials.Main;
import me.flugel.essentials.managers.BanManager;
import me.flugel.essentials.objects.Ban;
import me.flugel.essentials.objects.MySQL;
import me.flugel.essentials.viwers.BanViwers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BanController {
    private static BanManager banManager = Main.getInstance().getBanManager();

    public static void banPlayer(String target, String author, String reason) {
        Ban byBan = banManager.getByBan(target);

        if (byBan == null) {
            String uuid = "no online";
            Player banido = Bukkit.getPlayerExact(target);
            if (banido != null)
                uuid = banido.getUniqueId().toString();

            Ban ban = new Ban(target, reason, author, uuid);

            BanViwers.sucessMensageBan(target, author, reason, ban.getID());


        } else BanViwers.playerBanExists(byBan, author);
    }

    public static void addproof(Player sender,String playerBaned, String link){
        Ban byBan = banManager.getByBan(playerBaned);

        if(byBan != null){

            Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () ->{
                try {
                    MySQL mySQL = Main.getInstance().getMySQL();

                    mySQL.openConnection();
                    Connection connection = mySQL.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bans SET proof=? WHERE nick=?");

                    preparedStatement.setString(1,link);
                    preparedStatement.setString(2, playerBaned);

                    preparedStatement.executeUpdate();

                    sender.sendMessage("§eEssentialsF: §aProva foi salvada com sucesso.");
                    mySQL.closeConnection();
                }catch (Exception e){
                    Bukkit.getConsoleSender().sendMessage("§cEssentialsF: Houve um erro ao salvar uma prova no banco de dados de banimentos.");
                    e.printStackTrace();
                }
            });
        }else sender.sendMessage("§eEssentialsF: §cJogador não foi encontrado na lista de banidos.");
    }
}
