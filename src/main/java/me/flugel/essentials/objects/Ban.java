package me.flugel.essentials.objects;

import me.flugel.essentials.Main;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Ban {
    private String name;
    private int ID;
    private String UUID;
    private String reason;
    private String autor;
    private String prova;

    public Ban(String name, String reason, String autor, String uuid) {
        this.name = name;
        this.reason = reason;
        this.autor = autor;
        this.UUID = uuid;
        Main.getInstance().getBanManager().getBanArrayList().add(this);
        insert();
    }

    public Ban(String name, String reason, String autor, String uuid, int id) {
        this.name = name;
        this.reason = reason;
        this.autor = autor;
        this.UUID = uuid;
        this.ID = id;
        Main.getInstance().getBanManager().getBanArrayList().add(this);
    }
    public Ban(String name, String reason, String autor) {
        this.name = name;
        this.reason = reason;
        this.autor = autor;
        Main.getInstance().getBanManager().getBanArrayList().add(this);
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getProva() {
        return prova;
    }

    public void setProva(String prova) {
        this.prova = prova;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUUID() {
        return UUID;
    }

    public String getAutor() {
        return autor;
    }

    public void insert() {
        MySQL mySQL = Main.getInstance().getMySQL();

        mySQL.openConnection();
        Connection connection = mySQL.getConnection();
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            try {


                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bans (nick, uuid, author, reason) VALUES (?, ?, ?, ?)");

                preparedStatement.setString(1, this.name);
                preparedStatement.setString(2, this.UUID);
                preparedStatement.setString(3, this.autor);
                preparedStatement.setString(4, this.reason);

                preparedStatement.execute();

                mySQL.closeConnection();
            } catch (Exception e) {
                mySQL.closeConnection();
                Bukkit.getConsoleSender().sendMessage("§cEssentialsF: Erro ao adicionar um jogador na lista de ban");
                e.printStackTrace();
            }
        });
    }
}
