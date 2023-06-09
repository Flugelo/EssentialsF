package me.flugel.essentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player))
            return false;


        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (p.hasPermission("essentialsf.fly")){
                if(p.getAllowFlight()){
                    p.setAllowFlight(false);
                    p.sendMessage("§eEssentailsF: §cFly desativado!");
                    return true;
                }else{
                    p.setAllowFlight(true);
                    p.sendMessage("§eEssentialsF: §aFly ativado!");
                    return true;
                }
            }else p.sendMessage("§cVocê não tem permissão para executar esse comando.");
        }
        return false;
    }
}
