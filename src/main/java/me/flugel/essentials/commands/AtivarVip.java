package me.flugel.essentials.commands;

import me.flugel.essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AtivarVip implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {


        if (cmd.getName().equalsIgnoreCase("ativarvip")) {
            if (sender.hasPermission("essentialsf.ativarvip")) {
                if (args.length == 0) {
                    sender.sendMessage("§cUse /ativarvip <jogador> <grupo> <dias>");
                    return false;
                } else if (args.length == 1) {
                    sender.sendMessage("§cUse /ativarvip <jogador> <grupo> <dias>");
                    return false;
                } else if (args.length == 2) {
                    sender.sendMessage("§cUse /ativarvip <jogador> <grupo> <dias>");
                    return false;
                } else if (args.length == 3){
                    Player player = Bukkit.getPlayerExact(args[0]);
                    String group = args[1];
                    int dias = Integer.parseInt(args[2]);
                    if(player != null){
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " group set " + group);
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT,1,1);
                        }
                        Bukkit.broadcastMessage("");
                        Bukkit.broadcastMessage("§eJogador §6" + player.getName() + " §a§lATIVOU §eo §a§lVIP");
                        Bukkit.broadcastMessage("");

                        new me.flugel.essentials.objects.AtivarVip(player.getName(), player.getUniqueId().toString(), group, dias);
                    }else sender.sendMessage("§cJogador " + args[0] + " não existe.");
                }
            }
        }

        if(cmd.getName().equalsIgnoreCase("viptime")){
            if (args.length == 0){
                me.flugel.essentials.objects.AtivarVip byVip = Main.getInstance().getVipManager().getByVip(sender.getName());
                if(byVip != null){
                    sender.sendMessage("");
                    sender.sendMessage("§7Vip: §f" + byVip.getVip());
                    sender.sendMessage("§7Data comprado: §f" + byVip.getBuy_date());
                    sender.sendMessage("§7Data de Expiração: §f" + byVip.getExpiration_date());
                    sender.sendMessage("");
                    return true;
                }else sender.sendMessage("§cVocê não tem vip ativo. Aproveitando, compra lá :)");
            }else if (args.length == 1){
                if(sender.hasPermission("essentials.viptime")){
                    me.flugel.essentials.objects.AtivarVip byVip = Main.getInstance().getVipManager().getByVip(args[0]);
                    if(byVip != null){
                        sender.sendMessage("");
                        sender.sendMessage("§7Vip: §f" + byVip.getVip());
                        sender.sendMessage("§7Data comprado: §f" + byVip.getBuy_date());
                        sender.sendMessage("§7Data de Expiração: §f" + byVip.getExpiration_date());
                        sender.sendMessage("");
                    }else sender.sendMessage("§cEsse jogador não contem vip.");
                }
            }
        }
        return false;
    }
}
