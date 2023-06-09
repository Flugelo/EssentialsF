package me.flugel.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Invsee implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("invsee")){
            if(p.hasPermission("essentialsf.invsee")){
                if(args.length == 0){
                    p.sendMessage("§eEssentialsF: §cUse o comando /inv <player> para abrir o inventario do jogador");
                    return false;
                }else if(args.length == 1){
                    Player target = Bukkit.getPlayerExact(args[0]);

                    if(target != null){
                        p.openInventory(target.getInventory());
                        return true;
                    }else p.sendMessage("§cEsse jogador não existe ou não está online no momento!");
                }
            }else p.sendMessage("§cSua cama foi destruida ou não foi setada o spawn.");
        }
        return false;
    }
}
