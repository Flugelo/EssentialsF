package me.flugel.essentials.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("home")){
            Location bedSpawnLocation = p.getBedSpawnLocation();
            if (bedSpawnLocation != null) {
                p.teleport(p.getBedSpawnLocation());
                p.sendMessage("§aTeleportando...");
            }else p.sendMessage("§cSua cama foi destruida ou não foi setada o spawn.");

        }
        return false;
    }
}
