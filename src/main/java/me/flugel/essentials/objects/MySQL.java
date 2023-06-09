package me.flugel.essentials.objects;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
    private Connection connection;

    private final String user;
    private final String pass;
    private final String host;
    private final int port;
    private final String db;
    private int query;

    public MySQL(String user, String pass, String host, int port, String db) {
        this.user = user;
        this.pass = pass;
        this.host = host;
        this.port = port;
        this.db = db;
        this.query = 0;
        loadDB();
    }

    public void openConnection() {
        try {
            query++;
            if ((connection != null) && (!connection.isClosed()))
                return;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false", user, pass);

        } catch (ClassNotFoundException | SQLException e) {
            query--;
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(
                    "§cMoney: Houve um erro ao conectar no servidor MySQL");
        }

    }

    public void closeConnection() {
        query--;
        if (query <= 0) {
            try {
                if (connection != null && !connection.isClosed())
                    connection.close();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(
                        "AVISO: Houve um erro ao fechar a conexão!");
            }
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    private void loadDB() {
        openConnection();
        criarTabelas();
        closeConnection();
    }

    private void criarTabela(String tabela, String colunas) {
        try {
            if ((connection != null) && (!connection.isClosed())) {
                Statement stm = connection.createStatement();
                stm.executeUpdate("CREATE TABLE IF NOT EXISTS " + tabela + " (" + colunas + ");");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§cAVISO: Ocorreu um erro ao criar as tabelas na DB!");
        }
    }

    private void criarTabelas() {
        criarTabela("bans", "id_ban INT(11) NOT NULL AUTO_INCREMENT," +
                "nick VARCHAR(40) NOT NULL," +
                "uuid VARCHAR(40) NOT NULL," +
                "author VARCHAR(40) NOT NULL," +
                "reason TEXT," +
                "proof TEXT," +
                "Date TIMESTAMP NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()," +
                "PRIMARY KEY (id_ban) USING BTREE," +
                "UNIQUE INDEX id_ban (id_ban) USING BTREE");

        criarTabela("waystone", "`name` VARCHAR(40)," +
                "`public` TINYINT(1)," +
                "`amount` TINYINT(1)," +
                "`friends` TEXT," +
                "`locations` TEXT");

        criarTabela("warps", "`id` INT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(20) NOT NULL DEFAULT '0'," +
                "`location` TEXT NOT NULL," +
                "UNIQUE INDEX `id` (`id`)," +
                "PRIMARY KEY (`id`)");


        criarTabela("viptime", "`ID` INT(11) NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(50) NOT NULL," +
                "`player_uuid` TEXT NOT NULL," +
                "`vip` VARCHAR(15) NOT NULL," +
                "`days` INT(11) NOT NULL," +
                "`purchase_date` VARCHAR(10) NOT NULL," +
                "`expiration_date` VARCHAR(10) NOT NULL," +
                "`active` TINYINT(1) NOT NULL," +
                "PRIMARY KEY (`ID`) USING BTREE");
    }
}
