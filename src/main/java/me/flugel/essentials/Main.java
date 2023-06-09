package me.flugel.essentials;

import me.flugel.essentials.commands.*;
import me.flugel.essentials.listeners.Elytra;
import me.flugel.essentials.listeners.*;
import me.flugel.essentials.listeners.corpse.DeathPlayer;
import me.flugel.essentials.listeners.corpse.respawn;
import me.flugel.essentials.listeners.waystone.InteractWayStone;
import me.flugel.essentials.listeners.waystone.RemoveWayStone;
import me.flugel.essentials.listeners.waystone.SalveAoSair;
import me.flugel.essentials.listeners.waystone.setLocationWayStone;
import me.flugel.essentials.managers.*;
import me.flugel.essentials.objects.MySQL;
import me.flugel.essentials.objects.WayStone.Cordenadas;
import me.flugel.essentials.objects.WayStone.RanameWayStone;
import me.flugel.essentials.objects.WayStone.WayStone;
import me.flugel.essentials.objects.WayStone.crafts.SuperEnderpearl;
import me.flugel.essentials.objects.WayStone.crafts.WayStoneCraft;
import me.flugel.essentials.schaduler.TpaTime;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main extends JavaPlugin {

    public List<String> elytraPlayer;
    public List<String> playerTeleporteWarp;

    private static Main instance;
    private MySQL mySQL;

    //Managers;
    private BanManager banManager;
    private TpaManager tpaManager;
    private WayStoneManager wayStoneManager;
    private InvseeManager invseeManager;
    private WarpsManager warpsManager;
    private PassivoManager passivoManager;
    private VipManager vipManager;
    private SolicitationWarpManager solicitationWarpManager;


    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        banManager = new BanManager();
        tpaManager = new TpaManager();
        wayStoneManager = new WayStoneManager();
        invseeManager = new InvseeManager();
        elytraPlayer = new ArrayList<>();
        warpsManager = new WarpsManager();
        passivoManager = new PassivoManager();
        vipManager = new VipManager();
        solicitationWarpManager = new SolicitationWarpManager();

        new TpaTime().runTaskTimerAsynchronously(this, 0, 20);

        startMySql();
        CarregarBans();
        onCache();
        onWarps();
        onVips();

        onCommands();
        onEvents();
    }

    private void onCommands() {
        getCommand("kick").setExecutor(new Kick());
        getCommand("ban").setExecutor(new Ban());
        getCommand("addprova").setExecutor(new Ban());
        getCommand("bc").setExecutor(new BroadCast());
        getCommand("fly").setExecutor(new Fly());
        getCommand("vanish").setExecutor(new vanish());
        getCommand("invsee").setExecutor(new Invsee());
        getCommand("elytra").setExecutor(new me.flugel.essentials.commands.Elytra());
        getCommand("unban").setExecutor(new UnBan());
        getCommand("enderchest").setExecutor(new EnderChest());
        getCommand("tpa").setExecutor(new Tpa());
        getCommand("tpaccept").setExecutor(new Tpa());
        getCommand("tpdeny").setExecutor(new Tpa());
        getCommand("home").setExecutor(new Home());
        getCommand("warp").setExecutor(new Warps());
        getCommand("removeallentites").setExecutor(new RemoveAllentitys());
        getCommand("checkallentites").setExecutor(new RemoveAllentitys());
        getCommand("passivo").setExecutor(new Passivo());
        getCommand("ativarvip").setExecutor(new AtivarVip());
        getCommand("viptime").setExecutor(new AtivarVip());
    }

    private void onEvents() {
        Bukkit.getPluginManager().registerEvents(new DeathPlayer(), this);
        Bukkit.getPluginManager().registerEvents(new me.flugel.essentials.listeners.Elytra(), this);
        Bukkit.getPluginManager().registerEvents(new JoinServer(), this);
        Bukkit.getPluginManager().registerEvents(new RemoveWayStone(), this);
        Bukkit.getPluginManager().registerEvents(new SalveAoSair(), this);
        Bukkit.getPluginManager().registerEvents(new setLocationWayStone(), this);
        Bukkit.getPluginManager().registerEvents(new InteractWayStone(), this);
        Bukkit.getPluginManager().registerEvents(new Motd(), this);
        Bukkit.getPluginManager().registerEvents(new respawn(), this);
        Bukkit.getPluginManager().registerEvents(new BreakSpawner(), this);
        Bukkit.getPluginManager().registerEvents(new PlacedSpawner(), this);
        Bukkit.getPluginManager().registerEvents(new PvpCheckPassivel(), this);
        Bukkit.getPluginManager().registerEvents(new RanameWayStone(), this);
        Bukkit.getPluginManager().registerEvents(new CheckVipTime(), this);

        Bukkit.addRecipe(SuperEnderpearl.getRecipe());
        Bukkit.addRecipe(WayStoneCraft.getRecipe());
    }


    private void startMySql() {
        String user = getConfig().getString("mysql.user");
        String passw = getConfig().getString("mysql.passw");
        int port = getConfig().getInt("mysql.port");
        String host = getConfig().getString("mysql.host");
        String bd = getConfig().getString("mysql.bd");

        this.mySQL = new MySQL(user, passw, host, port, bd);

    }

    private void onCache() {
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                mySQL.openConnection();
                Connection connection = mySQL.getConnection();


                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM waystone");

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    boolean aPublic = resultSet.getBoolean("public");
                    int amount = resultSet.getInt("amount");
                    String[] locations = resultSet.getString("locations").split("=");
                    ArrayList<Cordenadas> coordenadas = new ArrayList<>();
                    if(!Objects.equals(resultSet.getString("locations"), "")){
                        for (String location : locations) {
                            String[] peneira = location.split(">");
                            int x = Integer.valueOf(peneira[0]);
                            int y = Integer.valueOf(peneira[1]);
                            int z = Integer.valueOf(peneira[2]);
                            String world = peneira[3];
                            String nome = peneira[4];


                            Location loc = new Location(Bukkit.getWorld(world), x, y, z);
                            Cordenadas cordenadas = new Cordenadas(loc, nome);
                            coordenadas.add(cordenadas);
                        }
                        WayStone wayStone = new WayStone(name);
                        wayStone.setLocations(coordenadas);
                        wayStone.setPublico(aPublic);
                    }
                }
                Bukkit.getConsoleSender().sendMessage("§aEssentialsF: §aWayStone cacheadas!");
                mySQL.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
                mySQL.closeConnection();
            }

        });
    }

    private void CarregarBans() {
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                mySQL.openConnection();
                Connection connection = mySQL.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bans");

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String nick = resultSet.getString("nick");
                    String uuid = resultSet.getString("uuid");
                    String reason = resultSet.getString("reason");
                    String author = resultSet.getString("author");
                    int id = resultSet.getInt("id_ban");
                    new me.flugel.essentials.objects.Ban(nick, reason, author, uuid, id);
                }
                mySQL.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
                mySQL.closeConnection();
            }
        });
    }

    private void onWarps() {
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                mySQL.openConnection();
                Connection connection = mySQL.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM warps");

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String[] location = resultSet.getString("location").split(":");

                    double x = Double.valueOf(location[0]);
                    double y = Double.valueOf(location[1]);
                    double z = Double.valueOf(location[2]);
                    String world = location[3];

                    Location loc = new Location(Bukkit.getWorld(world), x, y, z);

                    new me.flugel.essentials.objects.Warps(name, loc);
                }
                mySQL.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
                mySQL.closeConnection();
            }
        });
    }

    private void onVips() {
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                mySQL.openConnection();
                Connection connection = mySQL.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM viptime WHERE active=?");

                preparedStatement.setBoolean(1, true);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String player_uuid = resultSet.getString("player_uuid");
                    String vip = resultSet.getString("vip");
                    int days = resultSet.getInt("days");
                    String buy_date = resultSet.getString("purchase_date");
                    String expiration_date = resultSet.getString("expiration_date");

                    new me.flugel.essentials.objects.AtivarVip(name,player_uuid, vip, days, buy_date ,expiration_date);

                }
                Bukkit.getConsoleSender().sendMessage("§aVips carregados com sucesso.");
                mySQL.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
                mySQL.closeConnection();
            }
        });
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public BanManager getBanManager() {
        return banManager;
    }

    public TpaManager getTpaManager() {
        return tpaManager;
    }

    public WayStoneManager getWayStoneManager() {
        return wayStoneManager;
    }

    public InvseeManager getInvseeManager() {
        return invseeManager;
    }


    public List<String> getElytraPlayer() {
        return elytraPlayer;
    }


    public WarpsManager getWarpsManager() {
        return warpsManager;
    }

    public List<String> getPlayerTeleporteWarp() {
        return playerTeleporteWarp;
    }

    public static Main getInstance() {
        return instance;
    }

    public VipManager getVipManager() {
        return vipManager;
    }

    public PassivoManager getPassivoManager() {
        return passivoManager;
    }

    public SolicitationWarpManager getSolicitationWarpManager() {
        return solicitationWarpManager;
    }
}
