package me.flugel.essentials.commands;

import me.flugel.essentials.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Passivo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("passivo")){
            if(p.hasPermission("essentialsf.passivo")){
                if(p.getWorld().getName().equals("world")){
                    me.flugel.essentials.objects.Passivo passivo = Main.getInstance().getPassivoManager().isPassivo(p.getName());

                    if(passivo != null){
                        if(passivo.isActive()){
                            p.sendMessage("§aModo passivo §cDESATIVADO");
                            passivo.setActive(false);
                            return false;
                        }else{
                            p.sendMessage("§cModo passivo será ativo em 10 segundos...");
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    p.sendMessage("§aModo passivo ativo. §eLembrando, esse modo você não pode bater em ninguem e ninguem pode te bater." +
                                            " Esse modo e desativado quando você for para outras dimenssões (NETHER e THE END)");

                                    passivo.setActive(true);
                                }
                            }.runTaskLaterAsynchronously(Main.getInstance(), 20*10);

                        }
                    }else{
                        p.sendMessage("§cModo passivo será ativo em 10 segundos...");
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                p.sendMessage("§aModo passivo ativo. §eLembrando, esse modo você não pode bater em ninguem e ninguem pode te bater." +
                                        " Esse modo e desativado quando você for para outras dimenssões (NETHER e THE END)");

                                new me.flugel.essentials.objects.Passivo(p.getName(), true);
                            }
                        }.runTaskLaterAsynchronously(Main.getInstance(), 20*10);
                    }
                }else{
                    p.sendMessage("§cVocê não pode executar esse comando nesse mundo.");
                }
            }else p.sendMessage("§cSua cama foi destruida ou não foi setada o spawn.");
        }
        return false;
    }
}
