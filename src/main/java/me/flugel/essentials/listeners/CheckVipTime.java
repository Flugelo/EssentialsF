package me.flugel.essentials.listeners;

import me.flugel.essentials.Main;
import me.flugel.essentials.objects.AtivarVip;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckVipTime implements Listener {
    @EventHandler
    public void checkVip(PlayerJoinEvent e) throws ParseException {
        Player player = e.getPlayer();
        AtivarVip byVip = Main.getInstance().getVipManager().getByVip(player.getName());
        if(byVip != null){
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            Date date_exp = formato.parse(byVip.getExpiration_date());

            long time_exp = date_exp.getTime();
            long dateNow = System.currentTimeMillis();
            long MillisFaltante = time_exp - dateNow;

            System.out.println(time_exp);
            System.out.println(dateNow);
            if( MillisFaltante >= 0){
                player.sendMessage("");
                player.sendMessage("§eSeu vip acaba em: §6" + (MillisFaltante/86400000)+ " §edias");
                player.sendMessage("");
            }else{
                player.sendMessage("");
                player.sendMessage("§cSeu vip expirou.");
                player.sendMessage("");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " group set default");
                byVip.update();
            }
        }
    }
}
