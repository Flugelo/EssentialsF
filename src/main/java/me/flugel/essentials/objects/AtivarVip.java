package me.flugel.essentials.objects;

import me.flugel.essentials.Main;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AtivarVip {
    private String playerName;
    private String uuid;
    private String vip;
    private int dias;
    private String buy_date;
    private String expiration_date;

    public AtivarVip(String playerName, String uuid, String vip, int dias) {
        this.playerName = playerName;
        this.uuid = uuid;
        this.vip = vip;
        this.dias = dias;
        setDates(dias);
        Main.getInstance().getVipManager().getVips().add(this);
        this.inset();
    }

    public AtivarVip(String playerName, String uuid, String vip, int dias, String buy_date, String expiration_date) {
        this.playerName = playerName;
        this.uuid = uuid;
        this.vip = vip;
        this.dias = dias;
        this.buy_date = buy_date;
        this.expiration_date = expiration_date;
        Main.getInstance().getVipManager().getVips().add(this);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public void inset() {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            MySQL mySQL = Main.getInstance().getMySQL();
            mySQL.openConnection();
            Connection connection = mySQL.getConnection();
            System.out.println(buy_date);
            System.out.println(expiration_date);

            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO viptime (name, player_uuid, vip, days, purchase_date, expiration_date, active) VALUES (?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, this.playerName);
                preparedStatement.setString(2, this.uuid);
                preparedStatement.setString(3, this.vip);
                preparedStatement.setInt(4, this.dias);
                preparedStatement.setString(5, this.buy_date);
                preparedStatement.setString(6, this.expiration_date);
                preparedStatement.setBoolean(7, true);

                preparedStatement.execute();

                Bukkit.getConsoleSender().sendMessage("§aVip salvo com sucesso.");
                mySQL.closeConnection();

            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("§cHouve um erro ao adicionar o vip no banco de dados.");
                e.printStackTrace();
            }


        });
    }

    public void update(){
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            MySQL mySQL = Main.getInstance().getMySQL();
            mySQL.openConnection();
            Connection connection = mySQL.getConnection();
            System.out.println(buy_date);
            System.out.println(expiration_date);

            try {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE viptime SET active=? WHERE name=?");
                preparedStatement.setString(2, this.playerName);
                preparedStatement.setBoolean(1, false);

                preparedStatement.executeUpdate();

                Bukkit.getConsoleSender().sendMessage("§aVip do jogador §e" + this.playerName + " §aexpirou!");
                mySQL.closeConnection();;
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("§cHouve um erro ao adicionar o vip no banco de dados.");
                e.printStackTrace();
            }


        });
    }

    public String getUuid() {
        return uuid;
    }

    public String getBuy_date() {
        return buy_date;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    private void setDates(int dias) {
        long data_compra = System.currentTimeMillis();
        long data_vencimento = (System.currentTimeMillis() + (dias * 86400000L));

        Date date_buy = new Date(data_compra);
        Date date_exp = new Date(data_vencimento);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        this.expiration_date = formato.format(date_exp);
        this.buy_date = formato.format(date_buy);
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setBuy_date(String buy_date) {
        this.buy_date = buy_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }
}
