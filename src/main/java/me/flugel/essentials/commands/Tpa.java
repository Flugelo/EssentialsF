package me.flugel.essentials.commands;

import me.flugel.essentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpa implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("tpa")){
            if(p.hasPermission("essentialsf.tpa")){
                if(args.length == 0){
                    p.sendMessage("§cUse o comando /tpa <jogador> para mandar uma solicitação de teleporte.");
                    return false;
                }else if(args.length == 1){
                    Player targert = Bukkit.getPlayerExact(args[0]);

                    me.flugel.essentials.objects.Tpa byPlayer = Main.getInstance().getTpaManager().getByPlayer(p);

                    if(byPlayer != null){
                        p.sendMessage("§cVocê já contem uma solicitação de teleporte. Aguarde até que solicitação seja cancelada.");
                        return false;
                    }

                    if(targert != null){
                        new me.flugel.essentials.objects.Tpa(p, targert);
                        p.sendMessage("§aVocê enviou uma solicitação de teleporte para o jogador §e" + targert.getName() + ". §a60 segundos para o jogadro aceitar o teleporte.");
                        targert.sendMessage("");
                        targert.sendMessage("§aVocê recebeu uma solicitação de teleporte do jogador §e" + p.getName() + ".");
                        targert.sendMessage("§aPara §2§lACEITAR §ao teleporte, digite: §e/tpaccpet");
                        targert.sendMessage("§aPara §c§lRECUSAR §ao teleporte, digite §e/tpadeny");
                        targert.sendMessage("");
                        return false;
                    }else{
                        p.sendMessage("§cJogador §4" + args[0] + "§c não existe ou não está online no momento.");
                        return false;
                    }
                }

            }else p.sendMessage("§cSua cama foi destruida ou não foi setada o spawn.");
        }

        if(cmd.getName().equalsIgnoreCase("tpaccept")){
            me.flugel.essentials.objects.Tpa byTarget = Main.getInstance().getTpaManager().getByTarget(p);

            if(byTarget != null){
                p.sendMessage("§aVocê §2§lACEITOU §aa solicitação de teleporte do jogador §e" + byTarget.getPlayer().getName());
                byTarget.getPlayer().sendMessage("§aJogador §e" + p.getName() + " §2ACEITOU §asua solicitação de teleporte.");
                byTarget.getPlayer().teleport(p.getLocation());
                Main.getInstance().getTpaManager().getTpaArrayList().remove(byTarget);
            }else p.sendMessage("§cVocê não tem solicitações de teleporte.");
        }

        if(cmd.getName().equalsIgnoreCase("tpdeny")){
            me.flugel.essentials.objects.Tpa byTarget = Main.getInstance().getTpaManager().getByTarget(p);

            if(byTarget != null){
                p.sendMessage("§aVocê §c§lRECUSOU §aa solicitação de teleporte do jogador §e" + byTarget.getPlayer().getName());
                byTarget.getPlayer().sendMessage("§cJogador §e" + p.getName() + " §4§lRECUSOU §csua solicitação de teleporte.");
            }else p.sendMessage("§cVocê não tem solicitações de teleporte.");
        }
        return false;
    }
}
